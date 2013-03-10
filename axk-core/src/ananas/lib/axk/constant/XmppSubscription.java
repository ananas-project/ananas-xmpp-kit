package ananas.lib.axk.constant;

public interface XmppSubscription {

	XmppSubscription none = Factory.newInstance("none");
	XmppSubscription to = Factory.newInstance("to");
	XmppSubscription from = Factory.newInstance("from");
	XmppSubscription both = Factory.newInstance("both");

	/**
	 * implements
	 * */

	class Factory {

		public static XmppSubscription getInstance(String string) {
			return s_set.get(string);
		}

		private final static AbstractXmppConstSet<XmppSubscription> s_set;

		private static XmppSubscription newInstance(String string) {
			return new MyImpl(string);
		}

		static {
			XmppSubscription[] array = { none, to, from, both };
			s_set = new AbstractXmppConstSet<XmppSubscription>(array, none);
		}

		private static class MyImpl extends AbstractXmppConst implements
				XmppSubscription {

			public MyImpl(String text) {
				super(text);
			}
		}
	}

}
