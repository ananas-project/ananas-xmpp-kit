package ananas.lib.axk;

public interface XmppPhase {

	XmppPhase init = F._new("init");
	XmppPhase online = F._new("online");
	XmppPhase offline = F._new("offline");
	XmppPhase dropped = F._new("dropped");
	XmppPhase logining = F._new("logining");

	class F {

		private static XmppPhase _new(String string) {
			return new MyImpl(string);
		}

		private static class MyImpl implements XmppPhase {

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
