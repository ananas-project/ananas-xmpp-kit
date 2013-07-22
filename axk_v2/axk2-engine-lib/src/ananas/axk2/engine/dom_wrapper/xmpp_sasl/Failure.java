package ananas.axk2.engine.dom_wrapper.xmpp_sasl;

import java.util.List;

import org.w3c.dom.Element;

import ananas.axk2.engine.dom_wrapper.DOMWrapperFactory;
import ananas.axk2.engine.dom_wrapper.DWDocument;
import ananas.axk2.engine.dom_wrapper.DWElement;

public class Failure extends SASLObject {

	private Empty _error_reason;

	protected Failure(Element element, DWDocument owner) {
		super(element, owner);
	}

	public static DOMWrapperFactory getFactory() {
		return new DOMWrapperFactory() {
			@Override
			public DWElement wrapElement(Element element, DWDocument owner) {
				return new Failure(element, owner);
			}
		};
	}

	public Empty getErrorReason() {
		Empty er = this._error_reason;
		if (er == null) {
			List<DWElement> chs = this.listChildElements();
			for (DWElement ch : chs) {
				if (ch instanceof Empty) {
					er = (Empty) ch;
					break;
				}
			}
			this._error_reason = er;
		}
		return er;
	}

}
