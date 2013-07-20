package ananas.axk2.engine.test;

import java.util.Properties;

import ananas.axk2.core.DefaultAddress;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.engine.XEngine;
import ananas.axk2.engine.XEngineFactory;
import ananas.axk2.engine.util.DefaultEngineContext;
import ananas.lib.util.PropertiesLoader;
import ananas.lib.util.logging.Logger;

public class TestAxkEngine {

	final static Logger log = Logger.Agent.getLogger();

	public static void main(String[] arg) {
		TestAxkEngine test = new TestAxkEngine();
		test.run();
	}

	private void run() {
		log.trace(this + ".run(begin)");
		XEngineFactory factory = XEngineFactory.Agent.getDefault();
		DefaultEngineContext context = new DefaultEngineContext();
		context._account = new MyAccount();
		log.info("login to " + context._account);
		XEngine engine = factory.createEngine(context);
		engine.connect();
		log.trace(this + ".run(end)");
	}

	static class MyAccount implements XmppAccount {

		private final Properties _prop;

		public MyAccount() {

			PropertiesLoader propLoader = PropertiesLoader.Util.loaderFor(this,
					"account.conf");
			this._prop = propLoader.load();

		}

		@Override
		public XmppAddress jid() {
			String s = this._str("jid");
			return new DefaultAddress(s);
		}

		private String _str(String key) {
			return this._prop.getProperty(key);
		}

		@Override
		public String resource() {
			return this._str("resource");
		}

		@Override
		public String password() {
			return this._str("password");
		}

		@Override
		public String host() {
			return this._str("host");
		}

		@Override
		public int port() {
			String s = this._str("port");
			return Integer.parseInt(s);
		}

		@Override
		public boolean useSSL() {
			String s = this._str("use_ssl");
			return Boolean.parseBoolean(s);
		}

		@Override
		public boolean ignoreTLSError() {
			String s = this._str("ignore_tls_error");
			return Boolean.parseBoolean(s);
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("XmppAccount:");

			sb.append("\n    jid = " + this.jid());
			sb.append("\n    password = '*'x" + this.password().length());
			sb.append("\n    host = " + this.host());
			sb.append("\n    port = " + this.port());
			sb.append("\n    resource = " + this.resource());

			sb.append("\n    ssl = " + this.useSSL());
			sb.append("\n    ignore_err = " + this.ignoreTLSError());

			return sb.toString();
		}

	}
}
