package impl.ananas.axk2.namespace.jabber_client;

import ananas.blueprint4.core.util.AbstractElement;

public class Ctr_object extends AbstractElement {

	@Override
	public Object createTarget() {
		return new MyTarget();
	}

	private static class MyTarget extends Tar_object {
	}

}
