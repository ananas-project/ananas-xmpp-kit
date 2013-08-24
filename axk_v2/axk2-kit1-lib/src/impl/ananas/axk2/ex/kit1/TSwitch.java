package impl.ananas.axk2.ex.kit1;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import ananas.axk2.core.XmppAPIHandler;

public class TSwitch extends TFilter {

	private final Map<String, TCase> _case_table;

	public TSwitch() {
		_case_table = new Hashtable<String, TCase>();
	}

	public void addCase(TCase tc) {
		this._case_table.put(tc.getCaseNamespace() + "", tc);
	}

	@Override
	public int listAPI(XmppAPIHandler h) {
		Collection<TCase> cases = this._case_table.values();
		for (TCase aCase : cases) {
			aCase.listAPI(h);
		}
		return super.listAPI(h);
	}

}
