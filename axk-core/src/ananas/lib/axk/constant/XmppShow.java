package ananas.lib.axk.constant;

public interface XmppShow {

	XmppShow away = Factory.newInstance("away");
	XmppShow chat = Factory.newInstance("chat");
	XmppShow dnd = Factory.newInstance("dnd");
	XmppShow xa = Factory.newInstance("xa");
	XmppShow empty = Factory.newInstance("");

	class Factory {

		public static XmppShow getInstance(String string) {
			return s_set.get(string);
		}

		private final static AbstractXmppConstSet<XmppShow> s_set;

		private static XmppShow newInstance(String string) {
			return new MyImpl(string);
		}

		static {
			XmppShow[] array = { away, chat, dnd, xa, empty };
			s_set = new AbstractXmppConstSet<XmppShow>(array, empty);
		}

		private static class MyImpl extends AbstractXmppConst implements
				XmppShow {

			public MyImpl(String text) {
				super(text);
			}
		}
	}

}
