package ananas.lib.axk.test;

import java.io.IOException;
import java.net.URI;

import org.xml.sax.SAXException;

import ananas.lib.axk.connection.XAccount;
import ananas.lib.axk.connection.XConnection;
import ananas.lib.axk.connection.XConnectionContext;
import ananas.lib.axk.connection.XConnectionFactory;
import ananas.lib.axk.connection.XDomListener;
import ananas.lib.axk.connection.XSocketSource;
import ananas.lib.axk.connection.util.DefaultConnectionContext;
import ananas.lib.axk.connection.util.DefaultDomListener;
import ananas.lib.axk.connection.util.DefaultSocketSource;

public class TestAxkConn {

	public static void main(String[] arg) {
		(new TestAxkConn()).run();
	}

	private void run() {

		XConnectionContext context = this.getContext();

		XConnectionFactory factory = XConnectionFactory.Util.getFactory();
		XConnection conn = factory.newConnection(context);

		XDomListener listener = new DefaultDomListener();
		XSocketSource source = new DefaultSocketSource(context);

		conn.setDomListener(listener);
		try {
			conn.parse(source);
		} catch (IOException | SAXException e) {
			e.printStackTrace();
		}

	}

	private XConnectionContext getContext() {
		DefaultConnectionContext context = new DefaultConnectionContext();

		XAccount account = new MyAccount();
		context.setAccount(account);

		return context;
	}

	class MyAccount implements XAccount {

		private URI m_jid;
		private String m_host;
		private int m_port;
		private boolean m_isUseSSL;

		public MyAccount() {

			// jid=xukun@puyatech.com password=****** host=puyatech.com
			// port=5222 resource=axk_client useSSL=false ignoreSSLError=false]

			this.m_jid = URI.create("jid://xukun@puyatech.com/");
			this.m_host = "puyatech.com";
			this.m_port = 5222;
			this.m_isUseSSL = false;
		}

		@Override
		public URI getJID() {
			return this.m_jid;
		}

		@Override
		public String getHost() {
			return this.m_host;
		}

		@Override
		public int getPort() {
			return this.m_port;
		}

		@Override
		public boolean isUseSSL() {
			return this.m_isUseSSL;
		}
	}
}
