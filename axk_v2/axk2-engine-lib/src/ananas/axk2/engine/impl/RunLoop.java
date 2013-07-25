package ananas.axk2.engine.impl;

import ananas.axk2.engine.XIOTask;
import ananas.axk2.engine.api.XThreadRuntime;

public interface RunLoop {

	void push_back(XIOTask runn);

	void push_front(XIOTask runn);

	void clear();

	XIOTask pop(boolean wait);

	void run(int times, XThreadRuntime rt);

}
