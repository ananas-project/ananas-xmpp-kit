package test.ananas.lib.axk;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ananas.lib.axk.XmppClient;
import ananas.lib.axk.api.IExConnection;

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

			IExConnection api = (IExConnection) this.mClient
					.getExAPI(IExConnection.class);
			api.sendStanza(text);

		}
	}

	protected void doCopyPrev() {
		String text = this.mTextPrev.getText();
		this.mTextSend.setText(text);
	}

	private void initLayout() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 640, 480);
		this.setTitle(this.getClass().getName());

		JMenu menu = this.createMenu();
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

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

	private JMenu createMenu() {

		JMenu menu = new JMenu("control");

		menu.add(this.createMenuItem("reset", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.this.getConnApi().reset();
			}
		}));
		menu.add(this.createMenuItem("connect", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.this.getConnApi().connect();
			}

		}));
		menu.add(this.createMenuItem("disconnect", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.this.getConnApi().disconnect();
			}
		}));
		menu.add(this.createMenuItem("close", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.this.getConnApi().close();
			}
		}));

		return menu;
	}

	protected IExConnection getConnApi() {
		return (IExConnection) this.mClient.getExAPI(IExConnection.class);
	}

	private JMenuItem createMenuItem(String string,
			ActionListener actionListener) {
		JMenuItem item = new JMenuItem(string);
		item.addActionListener(actionListener);
		return item;
	}

}
