package ananas.axk2.xep.xep_0030.disco.info.bp;

import ananas.axk2.xep.xep_0030.disco.info.Tar_feature;
import ananas.axk2.xep.xep_0030.disco.info.Tar_identity;
import ananas.axk2.xep.xep_0030.disco.info.Tar_query;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class Ctr_query extends CObject {

	public Tar_query target_query() {
		return (Tar_query) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(BPNode node) {

		if (node instanceof BPElement) {
			Object ch_tar = ((BPElement) node).getTarget(true);
			if (ch_tar instanceof Tar_feature) {
				Tar_feature feature = (Tar_feature) ch_tar;
				this.target_query().add_feature(feature);
				return true;
			} else if (ch_tar instanceof Tar_identity) {
				Tar_identity identity = (Tar_identity) ch_tar;
				this.target_query().add_identity(identity);
				return true;
			}
		}

		return super.onAppendChild(node);
	}

}
