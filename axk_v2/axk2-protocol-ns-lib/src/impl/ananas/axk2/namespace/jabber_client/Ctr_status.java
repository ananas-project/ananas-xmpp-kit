package impl.ananas.axk2.namespace.jabber_client;

public class Ctr_status extends Ctr_object {

	@Override
	public Object createTarget() {
		return new MyTarget();
	}

	private static class MyTarget extends Tar_status {
	}

}
