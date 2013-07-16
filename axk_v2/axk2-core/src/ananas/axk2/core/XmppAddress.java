package ananas.axk2.core;

public interface XmppAddress {

	String user();

	String domain();

	String resource();

	XmppAddress toFull();

	XmppAddress toPure();

	boolean isFull();

	boolean isPure();

}
