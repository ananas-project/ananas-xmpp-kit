package ananas.lib.axk.util;

import java.io.UnsupportedEncodingException;

import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppClient;
import ananas.lib.axk.api.IExCore;

final class XmppStanzaBuilderImpl implements XmppStanzaBuilder {

	private static XmppStanzaBuilderFactory s_factory;
	private XmppAccount mAccount;
	private XmppClient mClient;
	private final StringBuilder mSB = new StringBuilder(32);

	public XmppStanzaBuilderImpl(XmppAccount account, XmppClient client) {
		this.mAccount = account;
		this.mClient = client;
	}

	public static XmppStanzaBuilderFactory getFactory() {
		XmppStanzaBuilderFactory fact = s_factory;
		if (fact == null) {
			s_factory = fact = new MyFactory();
		}
		return fact;
	}

	static class MyFactory implements XmppStanzaBuilderFactory {

		@Override
		public XmppStanzaBuilder newInstance() {
			XmppAccount account = null;
			XmppClient client = null;
			return new XmppStanzaBuilderImpl(account, client);
		}

		@Override
		public XmppStanzaBuilder newInstance(XmppClient client) {
			IExCore core = (IExCore) client.getExAPI(IExCore.class);
			XmppAccount account = core.getAccount();
			return new XmppStanzaBuilderImpl(account, client);
		}

		@Override
		public XmppStanzaBuilder newInstance(XmppAccount account) {
			XmppClient client = null;
			return new XmppStanzaBuilderImpl(account, client);
		}
	}

	@Override
	public void append(String string) {
		this.mSB.append(string);
	}

	@Override
	public byte[] toByteArray() {
		try {
			return this.mSB.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	@Override
	public char[] toCharArray() {
		int len = this.mSB.length();
		char[] buff = new char[len];
		this.mSB.getChars(0, len, buff, 0);
		return buff;
	}

	@Override
	public void reset() {
		this.mSB.setLength(0);
	}

	@Override
	public void appendAttribute(String qName, String value) {
		this.mSB.append(' ');
		this.mSB.append(qName);
		this.mSB.append("='");
		this.append(value, true);
		this.mSB.append('\'');
	}

	@Override
	public void append(String string, boolean doEscape) {
		if (!doEscape) {
			this.mSB.append(string);
			return;
		}
		// TODO escape
		this.mSB.append(string);
	}

}
