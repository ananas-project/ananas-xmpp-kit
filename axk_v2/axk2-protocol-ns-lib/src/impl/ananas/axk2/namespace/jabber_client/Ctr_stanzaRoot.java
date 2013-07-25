package impl.ananas.axk2.namespace.jabber_client;

import ananas.axk2.namespace.jabber_client.JCStanzaRoot;
import ananas.blueprint4.core.lang.BPElement;
import ananas.blueprint4.core.lang.BPNode;

public class Ctr_stanzaRoot extends Ctr_object {

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {

		if (localName == null) {
			return false;
		} else if (localName.equals("xmllang")) {
			this.target_JCStanzaRoot().setXMLLang(value);
		} else if (localName.equals("id")) {
			this.target_JCStanzaRoot().setId(value);
		} else if (localName.equals("type")) {
			this.target_JCStanzaRoot().setType(value);
		} else if (localName.equals("to")) {
			this.target_JCStanzaRoot().setTo(value);
		} else if (localName.equals("from")) {
			this.target_JCStanzaRoot().setFrom(value);
		} else {
			return super.onSetAttribute(uri, localName, value);
		}
		return true;
	}

	@Override
	protected boolean onAppendChild(BPNode node) {
		if (node instanceof BPElement) {
			Object tar_ch = ((BPElement) node).getTarget(true);
			JCStanzaRoot tar_pt = this.target_JCStanzaRoot();
			tar_pt.addChild(tar_ch);
		} else {
			return super.onAppendChild(node);
		}
		return true;
	}

	private JCStanzaRoot target_JCStanzaRoot() {
		return (JCStanzaRoot) this.getTarget(true);
	}

}
