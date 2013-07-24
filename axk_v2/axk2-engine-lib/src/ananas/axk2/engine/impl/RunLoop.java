package ananas.axk2.engine.impl;

public interface RunLoop {

	void push_back(Task runn);

	void push_front(Task runn);

	void clear();

	Task pop(boolean wait);

	void run(int times);

}
