package test.ananas.axk2.main;

import test.ananas.axk2.env.TestEnvironment;
import test.ananas.axk2.util.testing.ITest;

public class TestAxk_with_Env {

	public static void main(String[] args) {

		(new TestEnvironment()).init();

		try {

			String mainClassName = System.getProperty("test.main.class");
			Class<?> mainClass = Class.forName(mainClassName);

			ITest tester = (ITest) mainClass.newInstance();
			tester.test();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
