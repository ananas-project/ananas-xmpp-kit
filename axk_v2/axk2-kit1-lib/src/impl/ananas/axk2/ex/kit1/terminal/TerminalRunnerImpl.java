package impl.ananas.axk2.ex.kit1.terminal;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.api.IClient;
import ananas.lib.util.logging.Logger;

public class TerminalRunnerImpl implements TerminalRunner {

	final static Logger log = Logger.Agent.getLogger();

	private final TerminalCallback _callback;

	public TerminalRunnerImpl(TerminalCallback callback) {
		this._callback = callback;
	}

	@Override
	public void run() {
		for (int timeout = 10; timeout > 0; timeout--) {
			System.out.println("start " + this + " in " + timeout + "sec");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Hello, this is " + this + ".");
		for (;;) {
			if (this._callback.isNeedExit()) {
				break;
			}
			System.out.println();
			System.out.print(">");
			String line = this.__readLine();
			if ("exit".equals(line)) {
				break;
			} else {
				System.out.println("bad command : " + line);
			}
		}
		this.__doExit();
		System.out.println("exit " + this + ".");
	}

	private void __doExit() {
		XmppConnection conn = this._callback.getConnection();
		IClient client = (IClient) conn.getAPI(IClient.class);
		client.disconnect();
	}

	private String __readLine() {
		try {
			InputStream in = System.in;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int b = in.read(); b >= 0; b = in.read()) {
				if (b == 0x0a || b == 0x0d) {
					break;
				} else {
					baos.write(b);
				}
			}
			return new String(baos.toByteArray(), "UTF-8");
		} catch (Exception e) {
			log.error(e);
			return e.getMessage();
		}

	}

	public String toString() {
		return "axk2-terminal";
	}
}
