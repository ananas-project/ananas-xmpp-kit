package ananas.axk2.engine;

import java.io.IOException;

import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.engine.api.XThreadRuntime;

public interface XIOTask {

	void onStep(XmppCommandStatus step, Throwable err);

	void run(XThreadRuntime runtime) throws IOException;

	int getPriority();
}
