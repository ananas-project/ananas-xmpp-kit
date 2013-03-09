package ananas.lib.axk.constant;

public interface XmppSubscription {

	XmppSubscription none = Factory._new("none");
	XmppSubscription to = Factory._new("to");
	XmppSubscription from = Factory._new("from");
	XmppSubscription both = Factory._new("both");

	/**
	 * implements
	 * */

	class Factory {

		public static XmppSubscription getInstance(String s) {
			if (both.toString().equalsIgnoreCase(s)) {
				return both;
			} else if (to.toString().equalsIgnoreCase(s)) {
				return to;
			} else if (from.toString().equalsIgnoreCase(s)) {
				return from;
			} else {
				return none;
			}
		}

		private static XmppSubscription _new(String string) {
			return new MyImpl(string);
		}

		private static class MyImpl implements XmppSubscription {

			private final String mText;

			public MyImpl(String string) {
				this.mText = string;
			}

			public String toString() {
				return this.mText;
			}
		}
	}
}
