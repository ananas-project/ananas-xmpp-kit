package ananas.axk2.namespace.jabber_client;

import java.util.List;

public interface JCStanzaRoot extends JCObject {

	String getFrom();

	String getTo();

	String getId();

	String getType();

	String getXMLLang();

	void setFrom(String jid);

	void setTo(String jid);

	void setId(String id);

	void setType(String type);

	void setXMLLang(String lang);

	void addChild(Object child);

	List<Object> listChildren();

}
