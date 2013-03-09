package ananas.lib.axk.api.roster;

import java.util.List;

public interface IRosterGroup {

	IExRosterManager getRoster();

	List<IRosterContact> listContacts();

	// binding
	boolean bind(IRosterContact group);

	boolean unbind(IRosterContact group);

	// attribute
	String getName();

}
