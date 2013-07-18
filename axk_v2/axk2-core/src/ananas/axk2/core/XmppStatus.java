package ananas.axk2.core;

public interface XmppStatus {

	XmppStatus initial = _F.get("initial");

	XmppStatus connect = _F.get("connect");
	XmppStatus tls = _F.get("tls");
	XmppStatus sasl = _F.get("sasl");
	XmppStatus bind = _F.get("bind");
	XmppStatus unpresence = _F.get("unpresence");

	XmppStatus online = _F.get("online");

	XmppStatus offline = _F.get("offline");
	XmppStatus dropped = _F.get("dropped");
	XmppStatus error = _F.get("error");
	XmppStatus closed = _F.get("closed");

	class _F {

		private static XmppStatus get(String name) {
			return new Impl(name);
		}

		private static class Impl implements XmppStatus {

			private final String _name;

			private Impl(String name) {
				_name = name;
			}

			public String toString() {
				return _name;
			}
		}
	}

}
