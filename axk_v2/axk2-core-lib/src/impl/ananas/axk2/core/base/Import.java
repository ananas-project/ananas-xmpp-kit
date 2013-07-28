package impl.ananas.axk2.core.base;

import ananas.axk2.core.bp.target.IImport;

public class Import implements IImport {

	private String _url;

	public void setURL(String url) {
		this._url = url;
	}

	@Override
	public String getURL() {
		return _url;
	}

}
