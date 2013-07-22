package test.ananas.axk2.main;

import test.ananas.axk2.TestAxk2;
import test.ananas.axk2.env.TestEnvironment;

public class TestAxkCore {

	public static void main(String[] arg) {
		(new TestEnvironment()).init();
		TestAxk2.main(arg);
	}
}
