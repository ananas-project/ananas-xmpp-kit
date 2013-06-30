package ananas.lib.axk.connection;

public interface XConnectionFactory {

	XConnection newConnection(XConnectionContext context);

	public static class Util {

		public static XConnectionFactory getFactory() {
			String s = "ananas.lib.axk.connection.impl.TheConnectionFactory";
			return Util.getFactory(s);
		}

		public static XConnectionFactory getFactory(String className) {
			try {
				Class<?> cls = Class.forName(className);
				return (XConnectionFactory) cls.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

}
