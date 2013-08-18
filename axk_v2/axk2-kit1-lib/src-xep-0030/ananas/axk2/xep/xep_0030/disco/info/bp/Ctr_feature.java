package ananas.axk2.xep.xep_0030.disco.info.bp;

import ananas.axk2.xep.xep_0030.disco.info.Tar_feature;

public class Ctr_feature extends CObject {

	public Tar_feature target_feature() {
		return (Tar_feature) this.getTarget(true);
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {

		if (localName == null) {
			return false;
		} else if (localName.equals("var")) {
			this.target_feature().set_var(value);
		} else {
			return super.onSetAttribute(uri, localName, value);
		}
		return true;
	}

}
