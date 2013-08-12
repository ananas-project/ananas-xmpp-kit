package ananas.axk2.stringio.test;

import ananas.axk2.stringio.IMessageListener;
import ananas.axk2.stringio.IStringIO;
import ananas.axk2.stringio.MessagePump;

public class TestMessagePump {

	public static void main(String[] arg) {
		(new TestMessagePump()).test();
	}

	private void test() {
		IStringIO io = new MyIO();
		IMessageListener li = new MyListener();
		MessagePump pump = new MessagePump(io, li);
		pump.start();
	}

	class MyIO implements IStringIO {
		long _now = 0;
		long _cnt = 0;

		@Override
		public String io(String cmd) {
			long now = System.currentTimeMillis();
			if (now == _now) {
				return null;
			} else {
				_now = now;
			}
			System.out.println("                        " + (_cnt++)
					+ " : io() : " + now);
			if ((now & 0x1000) == 0) {
				return now + "";
			}
			return null;
		}
	}

	class MyListener implements IMessageListener {

		long _cnt = 0;

		@Override
		public void onEvent(String event) {
			System.out.println((_cnt++) + " : onEvent() : " + event);
		}
	}
}
