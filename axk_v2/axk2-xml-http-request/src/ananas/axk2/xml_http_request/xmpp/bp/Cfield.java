package ananas.axk2.xml_http_request.xmpp.bp;

public class Cfield extends CtrObject {

	public Tfield target_field() {
		return (Tfield) this.getTarget(true);
	}

	@Override
	protected boolean onSetAttribute(String uri, String localName, String value) {
		if ("name".equals(localName)) {
			this.target_field().setName( value ) ;
		} else if ("value".equals(localName)) {
			this.target_field().setValue ( value ) ;
		} else {
			return super.onSetAttribute(uri, localName, value);
		}
		return true;
	}
}
