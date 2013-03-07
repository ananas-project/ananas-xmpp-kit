package test.ananas.lib.axk_with_bp2ui;

import java.io.File;
import java.net.URL;
import java.util.Vector;

public class MainForTestWithBp2ui_R_gen {

	public static void main(String[] args) {

		// -base-dir ${project_loc}
		// -res-dir src-demo2
		// -gen-dir gen
		// -R-class bp2.swing.demo2.gui.R
		// -accept-file .xml+.png
		// -accept-attr id+command

		URL url = MainForTestWithBp2ui_R_gen.class.getProtectionDomain()
				.getCodeSource().getLocation();
		String path = url.getPath();
		path = (new File(path)).getParent();

		Vector<String> v = new Vector<String>();
		for (String s : args) {
			v.add(s);
		}
		v.add("-base-dir");
		v.add(path);
		args = v.toArray(new String[v.size()]);

		ananas.lib.blueprint2.tools.ResourceIdGen.main(args);

	}
}
