package test.ananas.lib.axk_with_bp2ui;

import java.io.IOException;

import test.ananas.lib.axk.helper.dom.MyTextGroup;
import ananas.lib.blueprint2.Blueprint2;
import ananas.lib.blueprint2.dom.IDocument;

public class StanzaTemplateSet {

	private MyTextGroup mTextGroup;

	public StanzaTemplateSet() {
	}

	public void load() {

		try {
			String uri = R.file.template_xml;
			IDocument doc = Blueprint2.getInstance().loadDocument(uri);

			MyTextGroup tg = (MyTextGroup) doc.findTargetById(R.id.root);
			this.mTextGroup = tg;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public String findRawString(String cmd) {
		return this.mTextGroup.getText(cmd);
	}
}
