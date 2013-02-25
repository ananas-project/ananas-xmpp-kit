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
import ananas.lib.impl.axk.client.parser.DefaultXmppParserFactory;
import ananas.lib.impl.axk.client.parser.XmppParser;
import ananas.lib.impl.axk.client.parser.XmppParserCallback;
import ananas.lib.impl.axk.client.parser.XmppParserFactory;

public class XmppConnection implements Runnable {

	private final XmppAccount mAccount;
	private final XmppEnvironment mEnvi;
	private Socket mSocket;
	private InputStream mIS;
	private OutputStream mOS;
	private XmppConnectionListener mListener;
	private String mLastError;

	public XmppConnection(XmppAccount account, XmppEnvironment envi) {
		this.mAccount = account;
		this.mEnvi = envi;
	}

	public XmppConnection(XmppAccount account, XmppEnvironment envi,
			Socket socket) {
		this.mAccount = account;
		this.mEnvi = envi;
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

	public void setListener(XmppConnectionListener listener) {
		this.mListener = listener;
	}

	class MyXmppParserCallback implements XmppParserCallback {
	}

	public String getLastError() {
		return this.mLastError;
	}
}
