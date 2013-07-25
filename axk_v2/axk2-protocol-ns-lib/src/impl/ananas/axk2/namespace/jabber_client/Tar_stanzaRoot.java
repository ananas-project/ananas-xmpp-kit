package impl.ananas.axk2.namespace.jabber_client;

import java.util.ArrayList;
import java.util.List;

import ananas.axk2.namespace.jabber_client.JCStanzaRoot;

public class Tar_stanzaRoot implements JCStanzaRoot {

	private String _from;
	private String _xml_lang;
	private String _type;
	private String _id;
	private String _to;

	private final List<Object> _chs = new ArrayList<Object>(4);

	@Override
	public String getFrom() {
		return this._from;
	}

	@Override
	public String getTo() {
		return this._to;
	}

	@Override
	public String getId() {
		return this._id;
	}

	@Override
	public String getType() {
		return this._type;
	}

	@Override
	public String getXMLLang() {
		return this._xml_lang;
	}

	@Override
	public void setFrom(String jid) {
		this._from = jid;
	}

	@Override
	public void setTo(String jid) {
		this._to = jid;
	}

	@Override
	public void setId(String id) {
		this._id = id;
	}

	@Override
	public void setType(String type) {
		this._type = type;
	}

	@Override
	public void setXMLLang(String lang) {
		this._xml_lang = lang;
	}

	@Override
	public void addChild(Object child) {
		this._chs.add(child);
	}

	@Override
	public List<Object> listChildren() {
		return new ArrayList<Object>(this._chs);
	}

}
