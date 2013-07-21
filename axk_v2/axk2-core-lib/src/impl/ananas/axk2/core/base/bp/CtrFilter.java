package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.Filter;

public class CtrFilter extends CtrFilterBase {

	public Filter target_Filter() {
		return (Filter) this.getTarget(true);
	}

}
