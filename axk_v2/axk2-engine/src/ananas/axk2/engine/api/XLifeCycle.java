package ananas.axk2.engine.api;

/**
 * the life cycle stack:
 * 
 * [XSuperConnection / XThreadRuntime / XSubConnection]
 * 
 * */

public interface XLifeCycle {

	void open();

	void close();

}
