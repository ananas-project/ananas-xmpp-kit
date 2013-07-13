package ananas.axk2.core;

import ananas.lib.util.SingletonLoader;

public interface XmppEnvironment extends XmppContext {

	class Factory {

		public static XmppEnvironment getDefault() {
			return (XmppEnvironment) SingletonLoader
					.load(XmppEnvironment.class);
		}

	}

}
