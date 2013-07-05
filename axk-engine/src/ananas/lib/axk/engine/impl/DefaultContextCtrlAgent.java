package ananas.lib.axk.engine.impl;

import org.w3c.dom.Element;

import ananas.lib.axk.engine.XContext;
import ananas.lib.axk.engine.XContextController;
import ananas.lib.axk.engine.XContextControllerAgent;
import ananas.lib.axk.engine.sasl.LoginController;

public class DefaultContextCtrlAgent implements XContextControllerAgent {

	private XContextController m_ctrl;

	public DefaultContextCtrlAgent() {
		this.m_ctrl = new LoginController();
	}

	@Override
	public void onStanzaElement(XContext context, Element element) {
		XContextController ctrl = this.m_ctrl;
		if (ctrl != null)
			ctrl.onStanzaElement(context, element);
	}

	@Override
	public XContextController getContextController() {
		return this.m_ctrl;
	}

	@Override
	public void setContextController(XContextController ctrl) {
		this.m_ctrl = ctrl;
	}

}
