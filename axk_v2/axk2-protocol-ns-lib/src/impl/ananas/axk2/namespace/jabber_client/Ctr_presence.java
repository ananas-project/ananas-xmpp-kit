package impl.ananas.axk2.namespace.jabber_client;

public class Ctr_presence extends Ctr_stanzaRoot {

	@Override
	public Object createTarget() {
		return new MyTarget();
	}

	private static class MyTarget extends Tar_presence {
	}

}
