package ananas.lib.axk;

public abstract class XmppSubscription {

	public final static XmppSubscription def = MyImpl._new("def");

	public static XmppSubscription getInstance(String s) {
		return def;
	}

	/**
	 * implements
	 * */

	private static class MyImpl {

		public static XmppSubscription _new(String string) {
			// TODO Auto-generated method stub
			return null;
		}
	}

}
