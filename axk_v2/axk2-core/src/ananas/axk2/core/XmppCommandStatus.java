package ananas.axk2.core;

public interface XmppCommandStatus {

	XmppCommandStatus initial = F._new("initial");
	XmppCommandStatus waiting = F._new("waiting");
	XmppCommandStatus sending = F._new("sending");
	XmppCommandStatus error = F._new("error");
	XmppCommandStatus success = F._new("success");

	class F {

		private static XmppCommandStatus _new(String text) {
			return new MyImpl(text);
		}

		private static class MyImpl implements XmppCommandStatus {

			private final String _text;

			public MyImpl(String text) {
				this._text = text;
			}

			public String toString() {
				return _text;
			}
		}
	}
}
