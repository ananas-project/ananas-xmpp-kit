package ananas.axk2.engine.impl;

import ananas.axk2.core.XmppAccount;

public interface SASLProcessor {

	byte[] auth(XmppAccount account);

	byte[] response(byte[] challenge);

}
