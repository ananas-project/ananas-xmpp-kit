package ananas.lib.axk;

public abstract class XmppUtil implements XmppClientFactory {

	public static XmppClientFactory getFactory(String className) {
		try {
			Class<?> cls = Class.forName(className);
			return (XmppClientFactory) cls.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static XmppClientFactory getFactory() {
		String cn = "ananas.lib.impl.axk.TheXmppClientFactory";
		return getFactory(cn);
	}

}
