package impl.ananas.axk2.ex.kit1.bp;

import impl.ananas.axk2.ex.kit1.TTerminal;
import ananas.axk2.core.bp.target.IImport;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class CTerminal extends CtrFilter {

	public TTerminal target_Terminal() {
		return (TTerminal) this.getTarget(true);
	}

	@Override
	public boolean onAppendChild(BPNode child) {

		if (child instanceof BPElement) {
			Object ch_tar = ((BPElement) child).getTarget(true);
			if (ch_tar instanceof IImport) {
				IImport impo = (IImport) ch_tar;
				this.target_Terminal().addImport(impo);
			} else {
				return super.onAppendChild(child);
			}
		} else {
			return super.onAppendChild(child);
		}
		return true;
	}

}
