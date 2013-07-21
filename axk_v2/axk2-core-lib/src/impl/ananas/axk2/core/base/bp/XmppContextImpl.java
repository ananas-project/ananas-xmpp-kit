package impl.ananas.axk2.core.base.bp;

import ananas.axk2.core.XmppContext;
import ananas.blueprint4.core.BPEnvironment;
import ananas.blueprint4.core.Blueprint;

public class XmppContextImpl implements XmppContext {

	private final BPEnvironment _bpEnvi;

	public XmppContextImpl() {
		this._bpEnvi = Blueprint.Util.getDefault().getEnvironment();
	}

	@Override
	public BPEnvironment getBlueprintEnvironment() {
		return this._bpEnvi;
	}

}
