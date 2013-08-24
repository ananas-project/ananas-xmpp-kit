package ananas.axk2.xmpphost.agent;

import ananas.axk2.core.AbstractFilter;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.xml_http_request.IXMLHttpRequest;
import ananas.axk2.xml_http_request.IXMLHttpRequestAgent;
import ananas.axk2.xml_http_request.IXMLHttpRequestListener;
import ananas.axk2.xmpphost.IXmppHostListener;
import ananas.axk2.xmpphost.IXmppWanHost;

public class XHreqWanHostAgent extends AbstractFilter implements IXmppWanHost {

	private IXMLHttpRequestAgent _xhreq_agent;
	private XmppAccount _account;
	private XmppStatus _phase;
	private XmppStatus _status;

	private IXMLHttpRequestAgent __getXHreqAgent() {
		IXMLHttpRequestAgent agent = this._xhreq_agent;
		if (agent == null) {
			agent = (IXMLHttpRequestAgent) this.getConnection().getAPI(
					IXMLHttpRequestAgent.class);
			this._xhreq_agent = agent;
		}
		return agent;
	}

	@Override
	public void connect(IXmppHostListener listener) {
		IXMLHttpRequestAgent agent = this.__getXHreqAgent();
		IXMLHttpRequest req = agent.newRequest();
		String url = this.__getURL();
		req.open("POST", url);
		req.setHeader("host.do", "Connect");
		req.setListener(new MyListenerAdapter(listener));
		req.send(null);
	}

	private String __getURL() {
		return "http://user@host/path?q=0";
	}

	@Override
	public void disconnect(IXmppHostListener listener) {
		IXMLHttpRequestAgent agent = this.__getXHreqAgent();
		IXMLHttpRequest req = agent.newRequest();
		String url = this.__getURL();
		req.open("POST", url);
		req.setHeader("host.do", "Disconnect");
		req.setListener(new MyListenerAdapter(listener));
		req.send(null);
	}

	@Override
	public void setAccount(XmppAccount account, IXmppHostListener listener) {
		IXMLHttpRequestAgent agent = this.__getXHreqAgent();
		IXMLHttpRequest req = agent.newRequest();
		String url = this.__getURL();
		req.open("POST", url);
		req.setHeader("host.do", "SetAccount");
		req.setListener(new MyListenerAdapter(listener));
		req.send(null);
	}

	@Override
	public XmppAccount getAccount() {
		return this._account;
	}

	@Override
	public XmppStatus getPhase() {
		return this._phase;
	}

	@Override
	public XmppStatus getStatus() {
		return this._status;
	}

	@Override
	public void pull(IXmppHostListener listener) {
		IXMLHttpRequestAgent agent = this.__getXHreqAgent();
		IXMLHttpRequest req = agent.newRequest();
		String url = this.__getURL();
		req.open("POST", url);
		req.setHeader("host.do", "Pull");
		req.setListener(new MyListenerAdapter(listener));
		req.send(null);
	}

	class MyListenerAdapter implements IXMLHttpRequestListener {

		public MyListenerAdapter(IXmppHostListener listener) {
			// TODO Auto-generated constructor stub
		}
	}

}
