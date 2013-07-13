package impl.ananas.axk2.core;

import ananas.axk2.core.XmppEnvironment;
import ananas.blueprint4.core.BPEnvironment;
import ananas.blueprint4.core.Blueprint;

public class XmppEnvironmentImpl implements XmppEnvironment {

	private BPEnvironment _bpEnvi;

	@Override
	public BPEnvironment getBlueprintEnvironment() {
		if (this._bpEnvi == null) {
			this._bpEnvi = Blueprint.Util.getDefault().getEnvironment();
		}
		return _bpEnvi;
	}

}
