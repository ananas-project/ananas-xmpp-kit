package ananas.lib.axk;

public interface XmppAddress {

	String getUser();

	String getHost();

	String getResource();

	String toStringFull();

	String toStringPure();

}
