package test.ananas.lib.axk.helper.dom;

import ananas.lib.blueprint2.dom.INode;

public class MyTextGroupCtrl extends MyObject {

	public MyTextGroup target_text_group() {
		return (MyTextGroup) this.getTarget(true);
	}

	@Override
	protected boolean onAppendChild(INode child) {
		if (child instanceof MyTextCtrl) {
			MyTextCtrl ch = (MyTextCtrl) child;
			this.target_text_group().addText(ch.target_text());
			return true;
		} else {
			return super.onAppendChild(child);
		}
	}

}
