package ananas.lib.axk.api.roster;

import java.util.List;

import ananas.lib.axk.XmppAddress;

public interface IRosterContact {

	IExRosterManager getRoster();

	List<IRosterGroup> listOwnerGroups();

	// attribute
	String getName();

	XmppAddress getAddress();

	// binding
	boolean bind(IRosterGroup group);

	boolean unbind(IRosterGroup group);
}
