package ananas.lib.impl.axk;

import ananas.lib.axk.XmppEnvironment;
import ananas.lib.axk.XmppEnvironmentLoader;

public class TheXmppEnvironmentLoader implements XmppEnvironmentLoader {

	@Override
	public XmppEnvironment load() {
		return TheXmppEnvironment.getInstance();
	}

}
