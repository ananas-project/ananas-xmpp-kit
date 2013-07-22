package ananas.axk2.engine.api;

import ananas.axk2.core.XmppStatus;
import ananas.axk2.engine.dom_wrapper.DOMWrapperImplementation;

public interface XThreadRuntime extends XLifeCycle {

	XSubConnection getCurrentSubConnection();

	XSuperConnection getParent();

	XmppStatus getPhase();

	void setPhase(XmppStatus newPhase);

	DOMWrapperImplementation getDOMWrapperImplementation();

	/**
	 * get the time span while dropped
	 * */
	int genDropTime();

	/**
	 * set the time span while dropped
	 * */

	int setDropTime(int time);

}
