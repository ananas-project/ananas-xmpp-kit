package test.ananas.lib.axk.helper.dom;

import ananas.lib.blueprint2.dom.IAttr;
import ananas.lib.blueprint2.dom.helper.IClass;

public class MyAttr implements IAttr {

	private IClass mBpClass;
	private String mValue;

	@Override
	public boolean bindBlueprintClass(IClass aClass) {
		if (this.mBpClass == null && aClass != null) {
			this.mBpClass = aClass;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public IClass getBlueprintClass() {
		return this.mBpClass;
	}

	@Override
	public String getValue() {
		return this.mValue;
	}

	@Override
	public void setValue(String value) {
		this.mValue = value;
	}

}
