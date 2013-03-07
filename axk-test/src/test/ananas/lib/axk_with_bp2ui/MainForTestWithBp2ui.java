package test.ananas.lib.axk_with_bp2ui;

public class MainForTestWithBp2ui implements Runnable {

	public static void main(String[] arg) {
		javax.swing.SwingUtilities.invokeLater(new MainForTestWithBp2ui());
	}

	@Override
	public void run() {
		MainFrame mf = new MainFrame();
		mf.show();
	}
}
