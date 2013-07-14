package test.ananas.axk2;

import ananas.lib.util.logging.Level;
import ananas.lib.util.logging.LogManager;
import ananas.lib.util.logging.Logger;

public class MyLogManager implements LogManager {

	@Override
	public Logger getLogger(String name) {
		return new MyLogger();
	}

	class MyLogger implements Logger {

		@Override
		public void setLevel(Level level) {
			// TODO Auto-generated method stub

		}

		@Override
		public void trace(String string) {
			// TODO Auto-generated method stub

		}

		@Override
		public void warn(String string) {
			// TODO Auto-generated method stub

		}

		@Override
		public void info(String string) {
			// TODO Auto-generated method stub

		}

		@Override
		public void error(String string) {
			// TODO Auto-generated method stub

		}

		@Override
		public void error(Throwable e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void error(String message, Throwable e) {
			// TODO Auto-generated method stub

		}
	}

}
