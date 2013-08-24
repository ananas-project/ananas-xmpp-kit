package test.ananas.axk2.env;

import ananas.lib.util.PropertiesLoader;

public class TestEnvironment {

	public void init() {

		PropertiesLoader.Util.loadPropertiesToSystem(this, "system.properties");
		PropertiesLoader.Util.loadPropertiesToSystem(this, "tester.select.properties");

	}

}
