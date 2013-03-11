package ananas.lib.impl.axk;

import ananas.lib.axk.XmppClientFactory;
import ananas.lib.axk.XmppEnvironment;
import ananas.lib.axk.security.AXKSecurityManager;
import ananas.lib.blueprint3.core.Blueprint;
import ananas.lib.blueprint3.core.lang.BPEnvironment;
import ananas.lib.impl.axk.client.TheXmppClientNsInfo;
import ananas.lib.impl.axk.security.AXKSecurityManagerImpl;

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
	private boolean mIsAlive = true;
	private XmppClientFactory mClientFactory;
	private AXKSecurityManager mSecurityMan;

	private TheXmppEnvironment() {
		this.mBpEnvi = Blueprint.getInstance().defaultEnvironment();

		this.init();
	}

	private void init() {
		this.mBpEnvi.loadNamespace(
				"ananas.lib.blueprint3.loader.eom.EomReflectInfo", true);
		this.mBpEnvi.loadNamespace(
				"ananas.lib.blueprint3.element.bpbase.TheNamespaceInfo", true);
		this.mBpEnvi.loadNamespace(TheXmppClientNsInfo.class, true);
	}

	@Override
	public BPEnvironment getBPEnvironment() {
		return this.mBpEnvi;
	}

	@Override
	public XmppClientFactory getClientFactory() {
		XmppClientFactory factory = this.mClientFactory;
		if (factory == null) {
			factory = new TheXmppClientFactory();
			this.mClientFactory = factory;
		}
		return factory;
	}

	@Override
	public void shutdown() {
		this.mIsAlive = false;
	}

	@Override
	public boolean isAlive() {
		return this.mIsAlive;
	}

	@Override
	public AXKSecurityManager getSecurityManager() {
		AXKSecurityManager man = this.mSecurityMan;
		if (man == null) {
			this.mSecurityMan = man = new AXKSecurityManagerImpl();
		}
		return man;
	}

}
