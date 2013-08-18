package ananas.axk2.xep.xep_0030.disco.info.bp;

import ananas.axk2.xep.xep_0030.disco.info.Tar_identity;

public class Ctr_identity extends CObject {

	public Tar_identity target_identity() {
		return (Tar_identity) this.getTarget(true);
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {

		if (localName == null) {
			return false;
		} else if (localName.equals("category")) {
			this.target_identity().set_category(value);
		} else if (localName.equals("name")) {
			this.target_identity().set_name(value);
		} else if (localName.equals("type")) {
			this.target_identity().set_type(value);
		} else {
			return super.onSetAttribute(uri, localName, value);
		}
		return true;
	}

}
