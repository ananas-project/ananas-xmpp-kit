package test.ananas.lib.axk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ananas.lib.axk.DefaultXmppAccount;
import ananas.lib.axk.DefaultXmppAddress;
import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppClient;
import ananas.lib.axk.XmppClientFactory;
import ananas.lib.axk.XmppEnvironment;
import ananas.lib.axk.XmppEvent;
import ananas.lib.axk.XmppEventListener;
import ananas.lib.axk.XmppUtil;
import ananas.lib.axk.api.IExConnection;
import ananas.lib.axk.api.IExCore;
import ananas.lib.axk.security.AXKSecurityManager;
import ananas.lib.util.logging.AbstractLoggerFactory;
import ananas.lib.util.logging.Logger;

public class Main_4_dom implements Runnable, XmppEventListener {

	private final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	public static void main(String[] arg) {
		javax.swing.SwingUtilities.invokeLater(new Main_4_dom());
	}

	@Override
	public void run() {

		logger.trace(this + ".begin");

		Properties prop = this.getProperties();
		List<XmppAccount> accountList = this.getAccountList(prop);
		int index = Integer.parseInt(prop.getProperty("select"));
		final XmppAccount account = accountList.get(index);

		XmppEnvironment envi = XmppUtil.getDefaultEnvironment();
		this.initSecurity(envi.getSecurityManager());
		XmppClientFactory factory = envi.getClientFactory();
		XmppClient client = factory.newClient(account);
		client.setXmppEventListener(this);

		// api
		IExCore apiCore = (IExCore) client.getExAPI(IExCore.class);

		final XmppAccount account2 = apiCore.getAccount();
		logger.info("account = " + account2);

		// show ui
		// MainFrame mf = new MainFrame(client);
		// mf.show();
		IExConnection apiConn = (IExConnection) client
				.getExAPI(IExConnection.class);
		apiConn.connect();

		logger.trace(this + ".end");

	}

	private Properties getProperties() {
		Properties prop = new Properties();
		try {
			URL url = this.getClass().getProtectionDomain().getCodeSource()
					.getLocation();
			String path = url.getPath();
			File file = new File(path);
			file = file.getParentFile();
			file = new File(file, "account.properties");
			logger.trace("load " + file);
			FileInputStream in = new FileInputStream(file);
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return prop;
	}

	private List<XmppAccount> getAccountList(Properties prop) {

		List<XmppAccount> accountList = new ArrayList<XmppAccount>();
		{
			// 0: other , normal

			DefaultXmppAccount account = new DefaultXmppAccount();
			account.address = new DefaultXmppAddress(prop.getProperty("jid"));
			account.password = prop.getProperty("password");
			account.host = prop.getProperty("host");
			account.resource = prop.getProperty("resource");
			account.port = Integer.parseInt(prop.getProperty("port"));
			account.useSSL = (account.port == 5223);
			accountList.add(account);
		}
		{
			// 1: axktest@jabber.org ,normal
			DefaultXmppAccount account = new DefaultXmppAccount();
			account.address = new DefaultXmppAddress("axktest@jabber.org");
			account.password = "12345678";
			account.host = "jabber.org";
			account.resource = this.getClass().getName();
			account.useSSL = false;
			account.port = account.useSSL ? 5223 : 5222;
			accountList.add(account);
		}

		return accountList;
	}

	private void initSecurity(AXKSecurityManager securityManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEvent(XmppEvent event) {
		// TODO Auto-generated method stub

	}
}
