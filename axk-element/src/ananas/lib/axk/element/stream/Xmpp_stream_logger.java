package ananas.lib.axk.element.stream;

import java.util.List;

import org.apache.log4j.Logger;

import ananas.lib.util.log4j.AbstractLoggerFactory;

public class Xmpp_stream_logger {

	private final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	public void log(Xmpp_stream stream, Object object) {

		logger.info(stream + ".Rx  :  " + object);

		if (object instanceof Xmpp_features) {
			this.log4(stream, (Xmpp_features) object);
		}

	}

	private void log4(Xmpp_stream stream, Xmpp_features feat) {
		List<Object> list = feat.listItems();
		for (Object item : list) {
			logger.info("        " + item);
		}
	}

}
