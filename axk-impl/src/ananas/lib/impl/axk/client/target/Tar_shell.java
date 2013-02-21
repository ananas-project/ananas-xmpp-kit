package ananas.lib.impl.axk.client.target;

import ananas.lib.axk.XmppClientExAPI;
import ananas.lib.axk.api.IExShell;

public class Tar_shell extends Tar_abstractClient implements IExShell {

	@Override
	public XmppClientExAPI getExAPI(Class<? extends XmppClientExAPI> apiClass) {
		if (apiClass.equals(IExShell.class)) {
			IExShell api = this;
			return api;
		} else {
			return super.getExAPI(apiClass);
		}
	}

}
