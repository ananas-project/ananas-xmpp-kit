package ananas.lib.impl.axk.client.conn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import ananas.lib.axk.XmppAccount;
import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.XmppEnvironment;
import ananas.lib.axk.element.stream.Xmpp_stream;
import ananas.lib.impl.axk.client.parser.DefaultXmppParserFactory;
import ananas.lib.impl.axk.client.parser.XmppParser;
import ananas.lib.impl.axk.client.parser.XmppParserCallback;
import ananas.lib.impl.axk.client.parser.XmppParserFactory;

public class XmppConnection implements Runnable {

	private final XmppAccount mAccount;
	private final XmppEnvironment mEnvi;
	private final XmppConnectionListener mListener;
	private Socket mSocket;
	private InputStream mIS;
	private OutputStream mOS;
	private String mLastError;
	private IXmppConnectionController mCurCtrl;

	public XmppConnection(XmppAccount account, XmppEnvironment envi,
			XmppConnectionListener listener) {
		this.mAccount = account;
		this.mEnvi = envi;
		this.mListener = listener;
		this.mCurCtrl = new TheInitConnCtrl(this);
	}

	public XmppConnection(XmppAccount account, XmppEnvironment envi,
			XmppConnectionListener listener, Socket socket) {

		this.mAccount = account;
		this.mEnvi = envi;
		this.mListener = listener;
		this.mSocket = socket;
	}

	@Override
	public void run() {

		try {
			Socket socket = this.getSocket();
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			this.mIS = is;
			this.mOS = os;

			String stream_stream = this.getStreamOpenTag(this.mAccount);
			os.write(stream_stream.getBytes());

			XmppParserFactory xpf = new DefaultXmppParserFactory();
			XmppParser parse = xpf.newParser(this.mEnvi);
			XmppParserCallback callback = new MyXmppParserCallback();
			parse.parse(is, callback);

		} catch (Exception e) {
			e.printStackTrace();
			this.mLastError = e.getMessage();
		}

		this.doClose();

	}

	private String getStreamOpenTag(XmppAccount account) {
		XmppAddress jid = account.getAddress();
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0'?>");
		sb.append("<stream:stream");
		sb.append(" from='" + jid.toStringPure() + "'");
		sb.append(" to='" + jid.getHost() + "'");
		sb.append(" version='1.0'");
		sb.append(" xml:lang='en'");
		sb.append(" xmlns='jabber:client'");
		sb.append(" xmlns:stream='http://etherx.jabber.org/streams'>");
		return sb.toString();
	}

	private void doClose() {

		InputStream is = this.mIS;
		OutputStream os = this.mOS;
		Socket sock = this.mSocket;

		this.mIS = null;
		this.mOS = null;
		// this.mSocket = null;

		try {
			if (is != null)
				is.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.mLastError = e.getMessage();
		}
		try {
			if (os != null)
				os.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.mLastError = e.getMessage();
		}
		try {
			if (sock != null)
				sock.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.mLastError = e.getMessage();
		}

	}

	private Socket getSocket() throws IOException {
		Socket socket = this.mSocket;
		if (socket != null) {
			return socket;
		}
		SocketFactory sf;
		if (this.mAccount.isUseSSL()) {
			sf = SSLSocketFactory.getDefault();
		} else {
			sf = SocketFactory.getDefault();
		}
		String host = this.mAccount.getHost();
		int port = this.mAccount.getPort();
		socket = sf.createSocket();
		socket.connect(new InetSocketAddress(host, port));
		this.mSocket = socket;
		return socket;
	}

	public XmppConnectionListener getListener() {
		return this.mListener;
	}

	class MyXmppParserCallback implements XmppParserCallback {

		@Override
		public void onReceive(Xmpp_stream stream, Object object) {
			// XmppConnection.this.mListener.onReceive(object);
			XmppConnection.this.mCurCtrl.onReceive(stream, object);
		}
	}

	public void setController(IXmppConnectionController ctrl) {
		if (ctrl == null)
			return;
		this.mCurCtrl = ctrl;
	}

	public String getLastError() {
		return this.mLastError;
	}

	public void syncSendBytes(byte[] buffer, int offset, int length) {
		try {
			this.mOS.write(buffer, offset, length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
