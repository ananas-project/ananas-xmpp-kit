package ananas.axk2.terminal.commands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.api.ICommandAgent;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.terminal.Axk2TerminalCommand;
import ananas.blueprint4.terminal.ExecuteContext;
import ananas.blueprint4.terminal.Terminal;

public class Send extends Axk2TerminalCommand {

	@Override
	public void execute(ExecuteContext context) {

		final Terminal terminal = context.getTerminal();

		String theEnd;

		// begin
		theEnd = context.getFlags().getProperty("end");
		if (theEnd == null)
			theEnd = "[end]";

		final PrintStream out = terminal.getOutput();
		out.println("input stanza, end with '" + theEnd + "'");

		// input stanza
		String stanza = null;
		try {
			InputStream in = terminal.getInput();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			StringBuilder sb = new StringBuilder();
			for (;;) {
				String line = this.__readLine(in, baos).trim();
				if (line.equals(theEnd)) {
					break;
				} else {
					if (line.length() > 0)
						sb.append(line + "\r\n");
				}
			}
			stanza = sb.toString();

		} catch (IOException e) {

			e.printStackTrace();
		}

		// send
		out.println("send stanza : " + stanza);

		XmppConnection conn = this.getXmppConnection(context);
		ICommandAgent ca = (ICommandAgent) conn.getAPI(ICommandAgent.class);
		StanzaCommand cmd = ca.newStanzaCommand(conn);
		cmd.setString(stanza);
		conn.send(cmd);
	}

	/**
	 * @param baos
	 * @return if EOF, return null;
	 * @throws IOException
	 * */
	private String __readLine(InputStream in, ByteArrayOutputStream baos)
			throws IOException {

		baos.reset();
		for (int b = in.read(); b >= 0; b = in.read()) {
			switch (b) {
			case 0x0a:
			case 0x0d: {
				byte[] ba = baos.toByteArray();
				String line = new String(ba, "UTF-8");
				return line;
			}
			default:
				baos.write(b);
			}
		}
		return null;
	}

}
