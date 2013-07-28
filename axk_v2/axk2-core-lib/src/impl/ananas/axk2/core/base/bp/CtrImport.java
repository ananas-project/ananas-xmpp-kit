package impl.ananas.axk2.core.base.bp;

import impl.ananas.axk2.core.base.Import;

public class CtrImport extends CtrObject {

	public Import target_import() {
		return (Import) this.getTarget(true);
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {
		if (localName == null) {
			return false;
		} else if (localName.equals("url")) {
			this.target_import().setURL(value);
		} else {
			return super.onSetAttribute(uri, localName, value);
		}
		return true;
	}

}
