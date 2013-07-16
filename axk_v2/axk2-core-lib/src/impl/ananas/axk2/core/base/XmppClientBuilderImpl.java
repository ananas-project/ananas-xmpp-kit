package impl.ananas.axk2.core.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ananas.axk2.core.DefaultAccount;
import ananas.axk2.core.DefaultAddress;
import ananas.axk2.core.MutableAccount;
import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.XmppConnector;
import ananas.axk2.core.XmppContext;
import ananas.axk2.core.util.XmppClientBuilder;
import ananas.lib.util.logging.Logger;

public class XmppClientBuilderImpl implements XmppClientBuilder {

	final static Logger log = Logger.Agent.getLogger();

	private XmppAccount _account;
	private final MutableAccount _mutableAccount;

	public XmppClientBuilderImpl() {
		this._mutableAccount = new MutableAccount();
	}

	@Override
	public XmppClientBuilder setJID(String jid) {
		this._mutableAccount._jid = new DefaultAddress(jid);
		return this;
	}

	@Override
	public XmppClientBuilder setPassword(String password) {
		this._mutableAccount._password = password;
		return this;
	}

	@Override
	public XmppClientBuilder setResource(String resource) {
		this._mutableAccount._resource = resource;
		return this;
	}

	@Override
	public XmppClientBuilder setHost(String host) {
		this._mutableAccount._host = host;
		return this;
	}

	@Override
	public XmppClientBuilder setPort(int port) {
		this._mutableAccount._port = port;
		return this;
	}

	@Override
	public XmppClientBuilder setUseSSL(boolean useSSL) {
		this._mutableAccount._useSSL = useSSL;
		return this;
	}

	class MyPropHelper {

		private Properties prop;

		public MyPropHelper(Properties init) {
			this.prop = init;
		}

		public String getString(String key) {
			return this.prop.getProperty(key);
		}

		public int getInt(String key) {
			String s = this.prop.getProperty(key);
			if (s == null) {
				s = "need_value_for_key:" + key;
			}
			return Integer.parseInt(s);
		}

		public boolean getBoolean(String key) {
			String s = this.prop.getProperty(key);
			if (s == null) {
				s = "need_value_for_key:" + key;
			}
			return Boolean.parseBoolean(s);
		}

		public XmppAddress getJID(String key) {
			String s = this.prop.getProperty(key);
			if (s == null) {
				s = "need_value_for_key:" + key;
			}
			return new DefaultAddress(s);
		}
	}

	@Override
	public XmppClientBuilder loadAccount(Properties prop) {
		MyPropHelper h = new MyPropHelper(prop);
		MutableAccount a = this._mutableAccount;

		a._host = h.getString(XmppClientBuilder.key_host);
		a._port = h.getInt(XmppClientBuilder.key_port);
		a._password = h.getString(XmppClientBuilder.key_password);
		a._resource = h.getString(XmppClientBuilder.key_resource);
		a._jid = h.getJID(XmppClientBuilder.key_jid);
		a._useSSL = h.getBoolean(XmppClientBuilder.key_use_ssl);
		a._ignoreTLSError = h
				.getBoolean(XmppClientBuilder.key_ignore_ssl_error);

		return this;
	}

	@Override
	public XmppClientBuilder loadAccount(InputStream in) {
		try {
			Properties prop = new Properties();
			prop.load(in);
			this.loadAccount(prop);
		} catch (IOException e) {
			log.error(e);
		}
		return this;
	}

	@Override
	public XmppClientBuilder loadConfigXML(InputStream in) {
		throw new RuntimeException("no impl");
		// return this;
	}

	@Override
	public XmppConnection createClient() {
		XmppContext context = this.getContext();
		XmppAccount account = this.getAccount();
		return this.getConnector().openConnection(context, account);
	}

	@Override
	public XmppConnector getConnector() {
		return new XmppConnectorImpl();
	}

	@Override
	public XmppAccount getAccount() {
		XmppAccount account = this._account;
		if (account == null) {
			account = new DefaultAccount(this._mutableAccount);
			this._account = account;
		}
		return account;
	}

	@Override
	public XmppContext getContext() {
		return new XmppContextImpl();
	}
}
