package test.ananas.lib.axk.helper.dom;

import ananas.lib.blueprint2.dom.helper.IImplementation;
import ananas.lib.blueprint2.dom.helper.INamespace;
import ananas.lib.blueprint2.dom.helper.INamespaceLoader;

public class NamespaceLoader implements INamespaceLoader {

	public final static String ns_uri = "uri:xmlns:test:axk:helper";
	public final static String ns_default_prefix = "helper";

	@Override
	public INamespace load(IImplementation impl) {
		INamespace ns = impl.newNamespace(ns_uri, ns_default_prefix);

		MyHelper hp = new MyHelper(ns);
		hp.reg_attr("id");
		hp.reg("text", MyText.class);
		hp.reg("text-group", MyTextGroup.class);

		return ns;
	}

	private class MyHelper {

		private final INamespace mNS;

		public MyHelper(INamespace ns) {
			this.mNS = ns;
		}

		public void reg_attr(String localName) {
			String uri = this.mNS.getNamespaceURI();
			this.mNS.registerClass(uri, localName, MyAttr.class, Object.class);
		}

		public void reg(String localName, Class<?> targetClass) {

			try {
				String uri = this.mNS.getNamespaceURI();
				String ctrlClassName = targetClass.getName() + "Ctrl";
				Class<?> ctrlClass = Class.forName(ctrlClassName);

				this.mNS.registerClass(uri, localName, ctrlClass, targetClass);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

}
