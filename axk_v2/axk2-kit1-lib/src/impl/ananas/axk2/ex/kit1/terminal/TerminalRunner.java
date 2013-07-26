package impl.ananas.axk2.ex.kit1.terminal;

public interface TerminalRunner extends Runnable {

	class Factory {

		public static TerminalRunner create(TerminalCallback callback) {
			return new TerminalRunnerImpl(callback);
		}
	}

}
