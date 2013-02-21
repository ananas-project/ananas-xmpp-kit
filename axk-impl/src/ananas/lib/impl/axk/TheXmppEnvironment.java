package ananas.lib.impl.axk;

import ananas.lib.axk.XmppEnvironment;
import ananas.lib.blueprint.core.Blueprint;
import ananas.lib.blueprint.core.lang.BPEnvironment;
import ananas.lib.impl.axk.client.TheXmppClientNsInfo;

public class TheXmppEnvironment implements XmppEnvironment {

	private static XmppEnvironment s_inst;

	public static XmppEnvironment getInstance() {
		XmppEnvironment inst = s_inst;
		if (inst == null) {
			inst = new TheXmppEnvironment();
			s_inst = inst;
		}
		return inst;
	}

	private BPEnvironment mBpEnvi;

	private TheXmppEnvironment() {
		this.mBpEnvi = Blueprint.getInstance().defaultEnvironment();

		this.init();
	}

	private void init() {
		this.mBpEnvi.loadNamespace(
				"ananas.lib.blueprint.loader.eom.EomReflectInfo", true);
		this.mBpEnvi.loadNamespace(TheXmppClientNsInfo.class, true);
	}

	@Override
	public BPEnvironment getBPEnvironment() {
		return this.mBpEnvi;
	}

}
