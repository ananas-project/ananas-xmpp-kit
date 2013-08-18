package ananas.axk2.xep.xep_0030.disco.info;

import java.util.List;
import java.util.Vector;

public class Tar_query {

	private final List<Tar_identity> _list_identity = new Vector<Tar_identity>();
	private final List<Tar_feature> _list_featue = new Vector<Tar_feature>();

	public void add_feature(Tar_feature feature) {
		this._list_featue.add(feature);
	}

	public void add_identity(Tar_identity identity) {
		this._list_identity.add(identity);
	}

}
