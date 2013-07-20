package ananas.axk2.engine.api;

import ananas.axk2.engine.XEngineContext;

public interface XSuperConnection extends XLifeCycle {

	XEngineContext getContext();

	XThreadRuntime getCurrentTR();

}
