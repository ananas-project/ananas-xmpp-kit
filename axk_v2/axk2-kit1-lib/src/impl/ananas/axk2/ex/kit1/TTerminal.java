package impl.ananas.axk2.ex.kit1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.bp.target.IImport;
import ananas.axk2.ex.kit1.ITerminalAgent;
import ananas.blueprint4.core.BPEnvironment;
import ananas.blueprint4.core.lang.BPDocument;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.terminal.Terminal;
import ananas.blueprint4.terminal.TerminalConfig;
import ananas.blueprint4.terminal.TerminalFactory;
import ananas.blueprint4.terminal.loader.CommandLoader;
import ananas.lib.util.logging.Logger;

public class TTerminal extends TFilter implements ITerminalAgent {

	final static Logger log = Logger.Agent.getLogger();

	private final List<IImport> _import_list = new ArrayList<IImport>();
	private Terminal _terminal;

	@Override
	public void bind(XmppConnection connection) {
		super.bind(connection);
		this.__loadCommands();
	}

	private void __loadCommands() {

		for (IImport impo : this._import_list) {
			try {
				String url = impo.getURL();
				BPEnvironment bp = this.getConnection().getContext()
						.getBlueprintEnvironment();
				BPDocument doc = bp.loadBPDocument(url);
				BPElement root = doc.getRootElement();
				log.warn(this + ".addImport(no_impl) : " + root);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void unbind(XmppConnection connection) {
		super.unbind(connection);
	}

	public void addImport(IImport impo) {
		this._import_list.add(impo);
	}

	@Override
	public Terminal getTerminal() {
		Terminal term = this._terminal;
		if (term == null) {
			TerminalConfig config = null;
			term = TerminalFactory.Agent.newInstance().newTerminal(config);
			final List<IImport> list = this._import_list;
			if (list != null) {
				CommandLoader loader = term.getCommandLoaderFactory()
						.newLoader(term);
				for (IImport imp : list) {
					String url = imp.getURL();
					loader.load(url);
				}
			}

			XmppConnection conn = TTerminal.this.getConnection();
			String key = XmppConnection.class.getName();
			term.getGlobal().put(key, conn);

			this._terminal = term;
		}
		return term;
	}

}
