package impl.ananas.axk2.ex.kit1;

import java.util.Hashtable;
import java.util.Map;

public class TSwitch extends TFilter {

	private final Map<String, TCase> _case_table;

	public TSwitch() {
		_case_table = new Hashtable<String, TCase>();
	}

	public void addCase(TCase tc) {
		this._case_table.put(tc.getCaseNamespace() + "", tc);
	}

}
