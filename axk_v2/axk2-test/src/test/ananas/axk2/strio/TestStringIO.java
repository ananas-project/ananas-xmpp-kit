package test.ananas.axk2.strio;

import test.ananas.axk2.util.testing.ITest;
import ananas.axk2.core.XmppConnection;
import ananas.axk2.core.api.ICommandAgent;
import ananas.axk2.core.command.StanzaCommand;
import ananas.axk2.stringio.IStringIOAgent;
import ananas.axk2.stringio.IStringIOService;

public class TestStringIO implements ITest {

	public static void main(String[] arg) {
		(new TestStringIO()).test();
	}

	public void test() {

		XConnConfig ac = new ActivityConfig();
		XConnConfig sc = new ServiceConfig();

		// XmppAccount account = xcb.getAccount();
		// XmppContext context = xcb.getContext();
		// XmppConnector connector = xcb.getConnector();

		XmppConnection activity = ac.openConnection();
		XmppConnection service = sc.openConnection();

		IStringIOAgent agent = (IStringIOAgent) activity
				.getAPI(IStringIOAgent.class);
		IStringIOService serv = (IStringIOService) service
				.getAPI(IStringIOService.class);
		agent.bindIO(serv.getIO());

		{
			ICommandAgent ca = (ICommandAgent) activity
					.getAPI(ICommandAgent.class);
			ca.newStanzaCommand(activity);
			StanzaCommand stcmd = ca.newStanzaCommand(activity);
			stcmd.setString("<iq xmlns='jabber:client' from='a' to='b' id='x123' type='get' ></iq>");
			activity.send(stcmd);
		}
	}
}
