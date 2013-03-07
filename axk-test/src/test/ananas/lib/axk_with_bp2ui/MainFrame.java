package test.ananas.lib.axk_with_bp2ui;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;

import ananas.lib.blueprint2.Blueprint2;
import ananas.lib.blueprint2.awt.util.IResponseChainNode;
import ananas.lib.blueprint2.dom.IDocument;

public class MainFrame {

	private JFrame mFrame;

	public MainFrame() {

		final IDocument doc = this.loadDocument(R.file.mainframe_xml);
		this.mFrame = (JFrame) doc.findTargetById(R.id.root);
		this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		IResponseChainNode chainNode = (IResponseChainNode) doc
				.findTargetById(R.id.resp_chain_node_3);
		chainNode.setHook(this.mRespChainHook);

		// this.mMainClient = (JDesktopPane)
		// doc.findTargetById(R.id.main_client);

	}

	public IDocument loadDocument(String uri) {
		try {
			return Blueprint2.getInstance().loadDocument(uri);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private final IResponseChainNode mRespChainHook = new IResponseChainNode() {

		@Override
		public IResponseChainNode getNext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setNext(IResponseChainNode next) {
			// TODO Auto-generated method stub

		}

		@Override
		public IResponseChainNode getHook() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setHook(IResponseChainNode hook) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean processEvent(ActionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
	};

	public void show() {
		this.mFrame.setVisible(true);
	}
}
