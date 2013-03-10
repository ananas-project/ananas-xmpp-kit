package ananas.lib.axk.constant;

public interface XmppPresenceType {

	XmppPresenceType subscribe = Factory.newInstance("subscribe");
	XmppPresenceType subscribed = Factory.newInstance("subscribed");
	XmppPresenceType unsubscribed = Factory.newInstance("unsubscribed");
	XmppPresenceType unsubscribe = Factory.newInstance("unsubscribe");
	XmppPresenceType unavailable = Factory.newInstance("unavailable");
	XmppPresenceType error = Factory.newInstance("error");
	XmppPresenceType probe = Factory.newInstance("probe");

	/**
	 * implements
	 * */

	class Factory {

		public static XmppPresenceType getInstance(String string) {
			return s_set.get(string);
		}

		private final static AbstractXmppConstSet<XmppPresenceType> s_set;

		private static XmppPresenceType newInstance(String string) {
			return new MyImpl(string);
		}

		static {
			XmppPresenceType[] array = { subscribe, subscribed, unsubscribed,
					unsubscribe, unavailable, error, probe };
			s_set = new AbstractXmppConstSet<XmppPresenceType>(array,
					unavailable);
		}

		private static class MyImpl extends AbstractXmppConst implements
				XmppPresenceType {

			public MyImpl(String text) {
				super(text);
			}
		}
	}

}
