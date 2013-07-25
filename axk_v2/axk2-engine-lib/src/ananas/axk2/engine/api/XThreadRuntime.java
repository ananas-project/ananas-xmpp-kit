package ananas.axk2.engine.api;

import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppStatus;
import ananas.axk2.engine.XIOTask;
import ananas.axk2.engine.dom_wrapper.DOMWrapperImplementation;

public interface XThreadRuntime extends XLifeCycle {

	XSubConnection getCurrentSubConnection();

	XSuperConnection getParent();

	XmppStatus getPhase();

	void setPhase(XmppStatus newPhase);

	void setError(Throwable err);

	Throwable getError();

	DOMWrapperImplementation getDOMWrapperImplementation();

	/**
	 * get the time span while dropped
	 * */
	int genDropTime();

	/**
	 * set the time span while dropped
	 * */

	int setDropTime(int time);

	void setBind(XmppAddress addr);

	XmppAddress getBind();

	boolean addTask(XIOTask task);

}
