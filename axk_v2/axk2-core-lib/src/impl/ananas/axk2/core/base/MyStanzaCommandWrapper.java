package impl.ananas.axk2.core.base;

import java.io.IOException;
import java.io.OutputStream;

import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.engine.XIOTask;
import ananas.axk2.engine.api.XEncoding;
import ananas.axk2.engine.api.XThreadRuntime;

public class MyStanzaCommandWrapper implements XIOTask {

	private final StanzaCommand _stanza;
	private int _priority = -1;

	public MyStanzaCommandWrapper(StanzaCommand stanza) {
		this._stanza = stanza;
	}

	@Override
	public int getPriority() {
		return this._priority;
	}

	@Override
	public void onStep(XmppCommandStatus step, Throwable err) {
		this._stanza.setStatus(step, err);
	}

	@Override
	public void run(XThreadRuntime runtime) throws IOException {
		String s = this._stanza.getString();
		OutputStream out = runtime.getCurrentSubConnection().getOnlineOutput();
		out.write(s.getBytes(XEncoding.theDefault));
		out.flush();
	}

}
