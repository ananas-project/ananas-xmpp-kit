package impl.ananas.axk2.core.base;

import ananas.axk2.core.XmppCommandStatus;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.engine.XSendContext;

public class MyStanzaCommandWrapper implements XSendContext {

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
	public String getContent() {
		return this._stanza.getString();
	}

	@Override
	public void onStep(XmppCommandStatus step, Throwable err) {
		this._stanza.setStatus(step, err);
	}

}
