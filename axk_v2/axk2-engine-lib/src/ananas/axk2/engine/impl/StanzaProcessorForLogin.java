package ananas.axk2.engine.impl;

import java.io.IOException;
import java.util.List;

import org.w3c.dom.Element;

import ananas.axk2.core.DefaultAddress;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.engine.api.XEngineCore;
import ananas.axk2.engine.api.XEngineRuntimeContext;
import ananas.axk2.engine.api.XStanzaProcessor;
import ananas.axk2.engine.api.XStanzaProcessorManager;
import ananas.axk2.engine.dom_wrapper.DOMWrapperImplementation;
import ananas.axk2.engine.dom_wrapper.DWDocument;
import ananas.axk2.engine.dom_wrapper.DWElement;
import ananas.axk2.engine.dom_wrapper.jabber_client.Iq;
import ananas.axk2.engine.dom_wrapper.streams.Features;
import ananas.axk2.engine.dom_wrapper.xmpp_bind.Bind;
import ananas.axk2.engine.dom_wrapper.xmpp_bind.Jid;
import ananas.axk2.engine.dom_wrapper.xmpp_sasl.Failure;
import ananas.axk2.engine.dom_wrapper.xmpp_sasl.Mechanism;
import ananas.axk2.engine.dom_wrapper.xmpp_sasl.Mechanisms;
import ananas.axk2.engine.dom_wrapper.xmpp_sasl.Success;
import ananas.axk2.engine.dom_wrapper.xmpp_tls.Proceed;
import ananas.axk2.engine.dom_wrapper.xmpp_tls.Starttls;
import ananas.axk2.engine.util.Base64;
import ananas.axk2.engine.util.XEngineUtil;
import ananas.lib.util.logging.Logger;

public class StanzaProcessorForLogin implements XStanzaProcessor {

	final static Logger log = Logger.Agent.getLogger();

	@Override
	public void onStanza(XEngineRuntimeContext erc, Element element) {

		// wrap element
		DOMWrapperImplementation impl = erc.getSubConnection().getParent()
				.getDOMWrapperImplementation();
		DWDocument doc = impl.wrapDocument(element.getOwnerDocument());
		DWElement dwElement = doc.wrapElement(element);

		if (dwElement instanceof Features) {
			this.__onStanzaFeatures(erc, (Features) dwElement);

		} else if (dwElement instanceof Proceed) {
			this.__doTLS_run(erc);

		} else if (dwElement instanceof Failure) {
			this.__onStanzaSaslFailure(erc, (Failure) dwElement);

		} else if (dwElement instanceof Success) {
			this.__onStanzaSaslSuccess(erc);

		} else if (dwElement instanceof Iq) {
			this.__onStanzaIq(erc, (Iq) dwElement);

		} else {
			this.__onStanzaUnknow(erc, dwElement);
		}
	}

	private void __onStanzaIq(XEngineRuntimeContext erc, Iq iq) {
		Object kind = null;
		List<DWElement> chs = iq.listChildElements();
		for (DWElement ch : chs) {
			if (ch instanceof Bind) {
				this.__onStanzaBindOK(erc, iq, (Bind) ch);
				kind = ch;
				break;
			} else {
			}
		}
		if (kind == null) {
			this.__onStanzaUnknow(erc, iq);
		}
	}

	private void __onStanzaBindOK(XEngineRuntimeContext erc, Iq iq, Bind bind) {

		final Jid jid = (Jid) bind.findChildByClass(Jid.class);
		if (jid != null) {
			String jidText = jid.getChildText();
			log.info("bind to full jid : " + jidText);

			XmppAddress addr = new DefaultAddress(jidText);
			erc.getSubConnection().getParent().setBind(addr);
		}

		this.__doOnline_start(erc);

	}

	private void __doOnline_start(XEngineRuntimeContext erc) {

		XStanzaProcessorManager spm = erc.getSubConnection()
				.getStanzaProcessorManager();
		spm.setCurrentProcessor(spm.getProcessorForStatus(XmppStatus.online));

		this.__setPhase(erc, XmppStatus.online);
	}

	private void __setPhase(XEngineRuntimeContext erc, XmppStatus phase) {
		erc.getSubConnection().getParent().setPhase(phase);
	}

	private void __onStanzaSaslFailure(XEngineRuntimeContext erc,
			Failure failure) {

		log.error("error : " + failure.getErrorReason());

	}

	private void __onStanzaSaslSuccess(XEngineRuntimeContext erc) {
		try {
			XEngineRuntimeContext ercNext = new ErcSASL(erc);
			XEngineCore core = XEngineCore.Factory.newInstance();
			core.run(ercNext);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void __doTLS_run(XEngineRuntimeContext erc) {
		try {
			XEngineRuntimeContext ercNext = new ErcTLS(erc);
			XEngineCore core = XEngineCore.Factory.newInstance();
			core.run(ercNext);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void __onStanzaFeatures(XEngineRuntimeContext erc, Features feat) {
		Object kind = null;
		List<DWElement> chs = feat.listChildElements();
		for (DWElement ch : chs) {
			if (ch instanceof Starttls) {
				this.__doTLS_start(erc);
				kind = ch;
				break;
			} else if (ch instanceof Bind) {
				this.__doBind_start(erc);
				kind = ch;
				break;
			} else if (ch instanceof Mechanisms) {
				this.__doSASL_start(erc, (Mechanisms) ch);
				kind = ch;
				break;
			} else {
			}
		}
		if (kind == null) {
			this.__onStanzaUnknow(erc, feat);
		}
	}

	private void __doBind_start(XEngineRuntimeContext erc) {
		this.__setPhase(erc, XmppStatus.bind);
		String res = erc.getSubConnection().getParent().getParent()
				.getContext().getAccount().resource();
		StringBuilder sb = new StringBuilder();
		sb.append("<iq id='bind-001' type='set'>");
		if (res == null) {
			sb.append("<bind xmlns='urn:ietf:params:xml:ns:xmpp-bind'/>");
		} else {
			sb.append("<bind xmlns='urn:ietf:params:xml:ns:xmpp-bind'>");
			sb.append("<resource>");
			sb.append(res);
			sb.append("</resource>");
			sb.append("</bind>");
		}
		sb.append("</iq>");
		this.__sendStanza(erc, sb.toString());
	}

	private void __doSASL_start(XEngineRuntimeContext erc, Mechanisms mechs) {
		this.__setPhase(erc, XmppStatus.sasl);
		// select a supported mechanism
		final SASLProcessorManager sasl_pm = erc.getSubConnection()
				.getSASLProcessorManager();
		String name = null;
		SASLProcessor proc = null;
		List<DWElement> chs = mechs.listChildElements();
		for (DWElement ch : chs) {
			if (ch instanceof Mechanism) {
				final Mechanism mech = (Mechanism) ch;
				name = mech.getChildText();
				log.trace("try sasl mechanism : " + name);
				proc = sasl_pm.newProcessor(name);
				if (proc != null) {
					break;
				}
			}
		}
		if (proc == null) {
			log.warn("cannot find supported mechanism ! SASL failed :-(");
			return;
		}
		sasl_pm.setCurrent(proc);
		// make auth
		XmppAccount account = erc.getSubConnection().getParent().getParent()
				.getContext().getAccount();
		byte[] data = proc.auth(account);
		final StringBuilder sb = new StringBuilder();
		if (data == null) {
			sb.append("<auth xmlns='urn:ietf:params:xml:ns:xmpp-sasl' mechanism='");
			sb.append(name);
			sb.append("'/>");
		} else {
			String base64str = Base64.encode(data);
			sb.append("<auth xmlns='urn:ietf:params:xml:ns:xmpp-sasl' mechanism='");
			sb.append(name);
			sb.append("'>");
			sb.append(base64str);
			sb.append("</auth>");
		}
		this.__sendStanza(erc, sb.toString());
	}

	private void __doTLS_start(XEngineRuntimeContext erc) {
		this.__setPhase(erc, XmppStatus.tls);
		String s = "<starttls xmlns='urn:ietf:params:xml:ns:xmpp-tls'/>";
		this.__sendStanza(erc, s);
	}

	private void __sendStanza(XEngineRuntimeContext erc, String string) {
		try {
			erc.getSubConnection().send_sync(string);
		} catch (IOException e) {
			log.error(e);
		}
	}

	private void __onStanzaUnknow(XEngineRuntimeContext erc, DWElement dwElement) {
		String s = XEngineUtil.nodeToString(dwElement.getElement());
		log.warn(this + ".onStanzaUnknow : " + dwElement);
		log.warn("    XML=" + s);
		// log.warn("unknow object : " + wEle);
		dwElement.printTree(System.out, "    tree:");
	}
}
