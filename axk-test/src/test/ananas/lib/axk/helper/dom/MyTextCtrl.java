package test.ananas.lib.axk.helper.dom;

import ananas.lib.blueprint2.dom.IAttr;
import ananas.lib.blueprint2.dom.INode;
import ananas.lib.blueprint2.dom.IText;

public class MyTextCtrl extends MyObject {

	private final StringBuffer mSB = new StringBuffer();

	public MyText target_text() {
		return (MyText) this.getTarget(true);
	}

	@Override
	public boolean setAttribute(IAttr attr) {
		String localName = attr.getBlueprintClass().getLocalName();
		if (localName == null) {
			return false;
		} else if (localName.equals("id")) {
			this.target_text().setId(attr.getValue());
			return true;
		} else {
			return super.setAttribute(attr);
		}
	}

	@Override
	protected boolean onAppendChild(INode child) {

		if (child instanceof IText) {
			IText txt = (IText) child;
			String text = txt.getData();
			this.mSB.append(text);
			return true;
		} else {
			return super.onAppendChild(child);
		}
	}

	@Override
	protected void onTagEnd() {

		this.target_text().setText(this.mSB.toString());

		super.onTagEnd();
	}

}
