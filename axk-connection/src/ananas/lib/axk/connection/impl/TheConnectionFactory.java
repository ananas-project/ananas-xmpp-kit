package ananas.lib.axk.connection.impl;

import ananas.lib.axk.connection.XConnection;
import ananas.lib.axk.connection.XConnectionContext;
import ananas.lib.axk.connection.XConnectionFactory;

public class TheConnectionFactory implements XConnectionFactory {

	@Override
	public XConnection newConnection(XConnectionContext context) {
		return new MyConnection(context);
	}

}
