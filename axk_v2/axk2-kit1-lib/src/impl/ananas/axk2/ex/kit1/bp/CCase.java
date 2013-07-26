package impl.ananas.axk2.ex.kit1.bp;

import impl.ananas.axk2.ex.kit1.TCase;

public class CCase extends CtrObject {

	public TCase target_case() {
		return (TCase) this.getTarget(true);
	}

}
