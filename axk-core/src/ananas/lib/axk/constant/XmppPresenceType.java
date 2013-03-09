package ananas.lib.axk.constant;

import java.util.HashMap;
import java.util.Map;

public interface XmppPresenceType {

	XmppPresenceType subscribe = Factory._new("subscribe");
	XmppPresenceType subscribed = Factory._new("subscribed");
	XmppPresenceType unsubscribed = Factory._new("unsubscribed");
	XmppPresenceType unsubscribe = Factory._new("unsubscribe");
	XmppPresenceType unavailable = Factory._new("unavailable");
	XmppPresenceType error = Factory._new("error");
	XmppPresenceType probe = Factory._new("probe");

	/**
	 * implements
	 * */

	class Factory {

		private static Map<String, XmppPresenceType> s_map;

		public static XmppPresenceType getInstance(String s) {
			Map<String, XmppPresenceType> map = s_map;
			if (map == null) {
				XmppPresenceType[] array = { subscribe, subscribed,
						unsubscribed, unsubscribe, unavailable, error, probe };
				map = new HashMap<String, XmppPresenceType>();
				for (XmppPresenceType item : array) {
					map.put(item.toString(), item);
				}
			}
			XmppPresenceType rlt = map.get(s);
			if (rlt == null) {
				rlt = unavailable;
			}
			return rlt;
		}

		private static XmppPresenceType _new(String string) {
			return new MyImpl(string);
		}

		private static class MyImpl implements XmppPresenceType {

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
