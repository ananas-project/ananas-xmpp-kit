package impl.ananas.axk2.ex.kit1.contact;

import ananas.axk2.ex.kit1.contact.AttributeSet;

public interface IContactNode extends AttributeSet {

	IContactNode getParent();

	String getLocalName();

	String getQName();
}
