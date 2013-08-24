package ananas.axk2.core;

import ananas.lib.util.logging.Logger;

public interface XmppAPIHandler extends XmppAPI {

	/**
	 * @return XmppAPI.find_break|XmppAPI.find_continue
	 * */

	int onAPI(Class<?> apiClass, XmppAPI api);

	class Util {

		static final Logger log = Logger.Agent.getLogger();

		private static boolean isXmppAPI(final Class<?> apiClass) {
			return true;
		}

		public static int apiOfObject(Object obj, XmppAPIHandler h) {
			if (obj instanceof XmppAPI) {
				// log.debug("");
				Class<?> cls = obj.getClass();
				for (; cls != null; cls = cls.getSuperclass()) {
					Class<?>[] apis = cls.getInterfaces();
					// log.debug("    class:" + cls);
					for (Class<?> api : apis) {
						// log.debug("        api:" + api);
						if (isXmppAPI(api)) {
							// log.debug("bingo:" + api);
							h.onAPI(api, (XmppAPI) obj);
						}
					}
				}
			}
			return XmppAPI.find_continue;
		}

	}

}
