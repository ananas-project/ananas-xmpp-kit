package test.ananas.lib.axk;

import java.io.InputStream;
import java.util.Properties;

import test.ananas.lib.axk_with_bp2ui.MainFrame;
import ananas.lib.axk.DefaultXmppAccount;
import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppClient;
import ananas.lib.axk.XmppClientFactory;
import ananas.lib.axk.XmppEnvironment;
import ananas.lib.axk.XmppEvent;
import ananas.lib.axk.XmppEventListener;
import ananas.lib.axk.XmppUtil;
import ananas.lib.axk.api.IExCore;
import ananas.lib.axk.security.AXKSecurityManager;
import ananas.lib.util.logging.AbstractLoggerFactory;
import ananas.lib.util.logging.Logger;

public class Main implements Runnable, XmppEventListener {

	private final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	public static void main(String[] arg) {
		javax.swing.SwingUtilities.invokeLater(new Main());
	}

	@Override
	public void run() {

		logger.trace(this + ".begin");

		XmppAccount account = this.loadAccount();

		XmppEnvironment envi = XmppUtil.getDefaultEnvironment();
		this.initSecurity(envi.getSecurityManager());
		XmppClientFactory factory = envi.getClientFactory();
		XmppClient client = factory.newClient(account);
		client.setXmppEventListener(this);

		// api
		IExCore apiCore = (IExCore) client.getExAPI(IExCore.class);

		XmppAccount account2 = apiCore.getAccount();
		logger.info("account = " + account2);

		// show ui
		MainFrame mf = new MainFrame(client);
		mf.show();

		logger.trace(this + ".end");

	}

	private XmppAccount loadAccount() {

		Properties prop = new Properties();
		try {
			String name = "account.properties";
			InputStream in = this.getClass().getResourceAsStream(name);
			if (in == null) {
				System.err.println("no file : " + name);
			}
			prop.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DefaultXmppAccount account = new DefaultXmppAccount();
		account.setAddress(prop.getProperty("jid"));
		account.host = prop.getProperty("host");
		account.port = Integer.parseInt(prop.getProperty("port"));
		account.password = prop.getProperty("password");
		account.resource = prop.getProperty("resource") + "-"
				+ System.currentTimeMillis();
		account.ignoreSSLError = false;
		account.useSSL = false;

		return account;
	}

	private void initSecurity(AXKSecurityManager securityManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEvent(XmppEvent event) {

	}
}
