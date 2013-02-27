package ananas.lib.axk.util;

import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppClient;

public interface XmppStanzaBuilder {

	class Factory {

		private static XmppStanzaBuilderFactory getFactory() {
			return XmppStanzaBuilderImpl.getFactory();
		}

		public static XmppStanzaBuilder newInstance() {
			return getFactory().newInstance();
		}

		public static XmppStanzaBuilder newInstance(XmppClient client) {
			return getFactory().newInstance(client);
		}

		public static XmppStanzaBuilder newInstance(XmppAccount account) {
			return getFactory().newInstance(account);
		}
	}

	void append(String string);

	void append(String string, boolean doEscape);

	byte[] toByteArray();

	char[] toCharArray();

	void reset();

	void appendAttribute(String qName, String value);
}
