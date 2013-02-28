package ananas.lib.axk.element.stream;

import java.util.List;

public class Xmpp_stream_logger {

	public void log(Xmpp_stream stream, Object object) {

		System.out.println(stream + ".Rx  :  " + object);

		if (object instanceof Xmpp_features) {
			this.log4(stream, (Xmpp_features) object);
		}

	}

	private void log4(Xmpp_stream stream, Xmpp_features feat) {
		List<Object> list = feat.listItems();
		for (Object item : list) {
			System.out.println("        " + item);
		}
	}

}
