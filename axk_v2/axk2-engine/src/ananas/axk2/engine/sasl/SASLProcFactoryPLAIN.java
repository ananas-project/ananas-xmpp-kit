package ananas.axk2.engine.sasl;

import ananas.axk2.core.XmppAccount;
import ananas.axk2.core.XmppAddress;
import ananas.axk2.engine.impl.SASLProcessor;
import ananas.axk2.engine.impl.SASLProcessorFactory;

public class SASLProcFactoryPLAIN implements SASLProcessorFactory {

	@Override
	public SASLProcessor newProcessor() {
		return new MyProc();
	}

	class MyProc implements SASLProcessor {

		@Override
		public byte[] auth(XmppAccount account) {
			// <auth xmlns='urn:ietf:params:xml:ns:xmpp-sasl'
			// mechanism='PLAIN'>AGh4dwAx</auth>
			try {
				// System.err.println("sasl plain with : " + account);
				XmppAddress addr = account.jid();
				String user, host;
				user = addr.user();
				host = addr.domain();
				StringBuilder sb = new StringBuilder();
				sb.append(user + "@" + host);
				sb.append("\0");
				sb.append(user);
				sb.append("\0");
				sb.append(account.password());
				byte[] ba1 = sb.toString().getBytes("UTF-8");
				return ba1;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public byte[] response(byte[] challenge) {
			// TODO Auto-generated method stub
			return null;
		}
	}

}
