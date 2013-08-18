package impl.ananas.axk2.core.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ananas.axk2.core.XmppAPI;
import ananas.axk2.core.XmppAPIHandler;
import ananas.axk2.core.XmppCommand;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppEvent;
import ananas.axk2.core.XmppFilter;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.core.event.StanzaEvent;
import ananas.lib.util.logging.Logger;

public class Switch extends Filter {

	private final Logger log = Logger.Agent.getLogger();

	private final Map<String, Case> _map;
	private Map<String, XmppFilter> _filter_map;
	private XmppFilter[] _filter_list;

	public Switch() {
		this._map = new Hashtable<String, Case>();
	}

	public void addCase(Case c) {
		String ns = c.getNamespace();
		_map.put(ns, c);
		this._filter_map = null;
		this._filter_list = null;
	}

	private XmppFilter[] __list_filter() {
		XmppFilter[] array = this._filter_list;
		if (array == null) {
			List<XmppFilter> list = new ArrayList<XmppFilter>();
			Collection<Case> values = this._map.values();
			for (Case value : values) {
				list.add(value.getFilter());
			}
			array = list.toArray(new XmppFilter[list.size()]);
			this._filter_list = array;
		}
		return array;
	}

	private Map<String, XmppFilter> __map_filter() {
		Map<String, XmppFilter> map = this._filter_map;
		if (map == null) {
			map = new Hashtable<String, XmppFilter>();
			Collection<Case> values = this._map.values();
			for (Case value : values) {
				String k = value.getNamespace();
				XmppFilter v = value.getFilter();
				map.put(k, v);
			}
			this._filter_map = map;
		}
		return map;
	}

	@Override
	public void bind(XmppConnection connection) {
		super.bind(connection);
		XmppFilter[] list = this.__list_filter();
		for (XmppFilter filter : list) {
			filter.bind(connection);
		}
	}

	@Override
	public void unbind(XmppConnection connection) {
		XmppFilter[] list = this.__list_filter();
		for (XmppFilter filter : list) {
			filter.unbind(connection);
		}
		super.unbind(connection);
	}

	@Override
	public XmppCommand filter(XmppCommand command) {
		if (command instanceof StanzaCommand) {
			Element element = ((StanzaCommand) command).getElement();
			Map<String, XmppFilter> map = this.__map_filter();
			List<String> nss = this.__list_ns(element);
			if (nss.size() < 1) {
				nss.add("");
			}
			for (String ns : nss) {
				XmppFilter filter = map.get(ns);
				if (filter == null) {
					filter = map.get("");
				}
				if (filter == null) {
					log.warn("cannot find command filter for namespace : " + ns);
				} else {
					filter.filter(command);
				}
			}
		} else {
			XmppFilter[] fs = this.__list_filter();
			for (XmppFilter f : fs) {
				f.filter(command);
			}
		}
		return super.filter(command);
	}

	@Override
	public XmppEvent filter(XmppEvent event) {
		if (event instanceof StanzaEvent) {
			Element element = ((StanzaEvent) event).getElement();
			Map<String, XmppFilter> map = this.__map_filter();
			List<String> nss = this.__list_ns(element);
			if (nss.size() < 1) {
				nss.add("");
			}
			for (String ns : nss) {
				XmppFilter filter = map.get(ns);
				if (filter == null) {
					filter = map.get("");
				}
				if (filter == null) {
					log.warn("cannot find event filter for namespace : " + ns);
				} else {
					filter.filter(event);
				}
			}
		} else {
			XmppFilter[] fs = this.__list_filter();
			for (XmppFilter f : fs) {
				f.filter(event);
			}
		}
		return super.filter(event);
	}

	private List<String> __list_ns(Element element) {
		ArrayList<String> list = new ArrayList<String>();
		NodeList chs = element.getChildNodes();
		for (int i = chs.getLength() - 1; i >= 0; i--) {
			Node ch = chs.item(i);
			String ns = ch.getNamespaceURI();
			if (ns != null) {
				if (!list.contains(ns)) {
					list.add(ns);
				}
			}
		}
		return list;
	}

	@Override
	public int findAPI(Class<?> apiClass, XmppAPIHandler h) {
		XmppFilter[] list = this.__list_filter();
		for (XmppFilter item : list) {
			int res = item.findAPI(apiClass, h);
			if (res == XmppAPI.find_break) {
				return res;
			}
		}
		return super.findAPI(apiClass, h);
	}

}
