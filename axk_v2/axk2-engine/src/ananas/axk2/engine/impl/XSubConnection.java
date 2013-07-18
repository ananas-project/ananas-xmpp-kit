package ananas.axk2.engine.impl;

public interface XSubConnection extends XLifeCycle {

	XThreadRuntime getParent();

	void run();

	boolean hasOnline();
}
