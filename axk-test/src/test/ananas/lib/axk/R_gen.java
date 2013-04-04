package test.ananas.lib.axk;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import ananas.lib.blueprint3.tools.R_file_generator;
import ananas.lib.util.CommandLinePropertiesUtil;

public class R_gen {

	public static void main(String[] args) {

		// -base-dir ${project_loc}
		// -res-dir src-demo2
		// -gen-dir gen
		// -R-class bp2.swing.demo2.gui.R
		// -accept-file .xml+.png
		// -accept-attr id+command

		final Properties prop = CommandLinePropertiesUtil
				.argumentsToProperties(args);
		(new R_gen()).doRun(prop);
	}

	private void doRun(Properties prop) {
		prop.setProperty("-base-dir", this.getBaseDir());
		String[] args = CommandLinePropertiesUtil.propertiesToArguments(prop);
		R_file_generator.main(args);
	}

	private String getBaseDir() {
		URL url = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation();
		File path = new File(url.getPath());
		path = path.getParentFile();
		return path.getAbsolutePath();
	}
}
