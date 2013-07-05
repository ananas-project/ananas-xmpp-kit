package ananas.lib.axk.engine.impl;

import javax.net.ssl.SSLSocketFactory;

import ananas.lib.axk.engine.XAccount;
import ananas.lib.axk.engine.XContext;
import ananas.lib.axk.engine.XContextFactory;
import ananas.lib.axk.engine.XEngineListener;
import ananas.lib.axk.engine.util.DefaultXContext;

public class TheContextFactory implements XContextFactory {

	@Override
	public XContext createContext(XAccount account, XEngineListener listener) {

		DefaultXContext context = new DefaultXContext();

		context.m_account = account;
		context.m_ctrlAgent = new DefaultContextCtrlAgent();
		context.m_engineListener = listener;
		context.m_engineFactory = new TheDefaultXEngineFactory();
		context.m_socketContext = new InitSocketContext(context);
		context.m_xmlReaderProvider = new MyXMLReaderProvider();
		context.m_sslSocketFactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		try {
			context.m_domImpl = org.w3c.dom.bootstrap.DOMImplementationRegistry
					.newInstance().getDOMImplementation("");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | ClassCastException e) {
			e.printStackTrace();
		}

		return context;
	}

}
