package ananas.axk2.engine.impl;

import org.w3c.dom.Element;

import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.api.XEngineRuntimeContext;
import ananas.axk2.engine.api.XStanzaProcessor;
import ananas.lib.util.logging.Logger;

public class StanzaProcessorForOnline implements XStanzaProcessor {

	final static Logger log = Logger.Agent.getLogger();

	// private XSuperConnection _super_conn;

	@Override
	public void onStanza(XEngineRuntimeContext erc, Element element) {
		// String s = XEngineUtil.nodeToString(element);
		// log.info(this + ".onStanza() : " + s);
		XEngine engine = erc.getSubConnection().getParent().getParent()
				.getEngine();
		engine.getContext().getListener().onStanza(engine, element);
	}

}
