package impl.ananas.axk2.core.base.bp;

import ananas.axk2.core.XmppFilter;
import ananas.lib.util.logging.Logger;

public class CtrFilterLoader extends CtrObject {

	private static final Logger log = Logger.Agent.getLogger();

	private String _class_name;

	@Override
	public Object createTarget() {
		try {
			Class<?> cls = Class.forName(this._class_name);
			XmppFilter filter = (XmppFilter) cls.newInstance();
			return filter;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {
		if ("class".equals(localName)) {
			this._class_name = value;
			return true;
		} else {
			return super.onSetAttribute(uri, localName, value);
		}
	}

}
