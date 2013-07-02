package ananas.lib.axk.engine.impl;

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
		context.m_engineListener = new LoginListenerAgent(listener);
		context.m_engineFactory = new TheDefaultXEngineFactory();
		context.m_socketContext = new InitSocketContext(context);
		context.m_xmlReaderProvider=  new  MyXMLReaderProvider   ()  ;

		return context;
	}

}
