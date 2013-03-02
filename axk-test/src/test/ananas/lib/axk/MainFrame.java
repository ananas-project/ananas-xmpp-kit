package test.ananas.lib.axk;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ananas.lib.axk.XmppClient;
import ananas.lib.axk.api.IExConnectionSync;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final XmppClient mClient;
	private JTextArea mTextPrev;
	private JTextArea mTextSend;
	private JButton mBtnPrev;
	private JButton mBtnSend;

	public MainFrame(XmppClient client) {
		this.mClient = client;
		this.initLayout();
		this.setListeners();
	}

	private void setListeners() {
		this.mBtnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.this.doCopyPrev();
			}
		});
		this.mBtnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.doSend();
			}
		});
	}

	protected void doSend() {
		String text = this.mTextSend.getText().trim();
		if (text.length() > 0) {
			this.mTextSend.setText("");
			this.mTextPrev.setText(text);

			IExConnectionSync api = (IExConnectionSync) this.mClient
					.getExAPI(IExConnectionSync.class);
			(new Thread(new MySender(api, text))).start();

		}
	}

	class MySender implements Runnable {

		private IExConnectionSync mAPI;
		private String mText;

		public MySender(IExConnectionSync api, String text) {
			this.mAPI = api;
			this.mText = text;
		}

		@Override
		public void run() {
			this.mAPI.syncSendStanza(this.mText);
		}
	}

	protected void doCopyPrev() {
		String text = this.mTextPrev.getText();
		this.mTextSend.setText(text);
	}

	private void initLayout() {

		this.setBounds(100, 100, 640, 480);
		this.setTitle(this.getClass().getName());

		JTextArea txtPrev = new JTextArea();
		JTextArea txtSend = new JTextArea();
		JButton btnSend = new JButton("send");
		JButton btnCopy = new JButton("copy to send");
		this.mTextPrev = txtPrev;
		this.mTextSend = txtSend;
		this.mBtnPrev = btnCopy;
		this.mBtnSend = btnSend;

		JScrollPane scrollSend = new JScrollPane(txtSend);
		JScrollPane scrollPrev = new JScrollPane(txtPrev);

		JPanel plMain = new JPanel(new BorderLayout());
		JPanel plLeft = new JPanel(new GridLayout(2, 1));
		JPanel plRight = new JPanel(new GridLayout(2, 1));
		plMain.add(plLeft, BorderLayout.CENTER);
		plMain.add(plRight, BorderLayout.EAST);
		this.add(plMain);

		plLeft.add(scrollPrev);
		plLeft.add(scrollSend);
		plRight.add(btnCopy);
		plRight.add(btnSend);

		txtPrev.setEditable(false);
	}

}
