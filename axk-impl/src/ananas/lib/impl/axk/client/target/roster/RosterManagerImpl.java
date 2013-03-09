package ananas.lib.impl.axk.client.target.roster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import ananas.lib.axk.XmppAddress;
import ananas.lib.axk.api.roster.IExRosterManager;
import ananas.lib.axk.api.roster.IRosterContact;
import ananas.lib.axk.api.roster.IRosterGroup;

public class RosterManagerImpl implements IRosterManagerInner, IExRosterManager {

	private final IRosterManagerInnerCallback mCallback;
	private final Map<String, IRosterGroup> mGroupMap = new Hashtable<String, IRosterGroup>();
	private final Map<XmppAddress, IRosterContact> mContactMap = new Hashtable<XmppAddress, IRosterContact>();

	private List<IRosterGroup> mGroupListCache;
	private List<IRosterContact> mContactListCache;

	private boolean mIsAutoPullAB;

	public RosterManagerImpl(IRosterManagerInnerCallback callback) {
		this.mCallback = callback;
	}

	@Override
	public IExRosterManager toIExRosterManager() {
		return this;
	}

	@Override
	public IRosterManagerInnerCallback getCallback() {
		return this.mCallback;
	}

	@Override
	public boolean isAutoPullAfterBinding() {
		return this.mIsAutoPullAB;
	}

	@Override
	public void setAutoPullAfterBinding(boolean value) {
		this.mIsAutoPullAB = value;
	}

	@Override
	public IRosterGroup getGroup(String group) {
		return this.getGroup(group, false);
	}

	@Override
	public IRosterContact getContact(XmppAddress addr) {
		return this.getContact(addr, false);
	}

	private XmppAddress normalAddress(XmppAddress addr) {
		return addr.toPure();
	}

	@Override
	public boolean removeContact(XmppAddress addr) {
		addr = this.normalAddress(addr);
		IRosterContact rlt = this.mContactMap.remove(addr);
		if (rlt == null) {
			return false;
		} else {
			this.clearCache();
			return true;
		}
	}

	@Override
	public void reset() {
		this.mGroupMap.clear();
		this.mContactMap.clear();
		this.clearCache();
	}

	@Override
	public IRosterGroup getGroup(String groupName, boolean create) {
		groupName = this.normalGroupName(groupName);
		final Map<String, IRosterGroup> map = this.mGroupMap;
		IRosterGroup group = map.get(groupName);
		if (group == null && create) {
			group = new MyGroup(groupName);
			map.put(groupName, group);
		}
		return group;
	}

	private String normalGroupName(String groupName) {
		return groupName.trim();
	}

	@Override
	public IRosterContact getContact(XmppAddress addr, boolean create) {
		addr = this.normalAddress(addr);
		final Map<XmppAddress, IRosterContact> map = this.mContactMap;
		IRosterContact cont = map.get(addr);
		if (cont == null && create) {
			cont = new MyContact(addr);
			map.put(addr, cont);
		}
		return cont;
	}

	@Override
	public boolean removeGroup(String groupName) {
		IRosterGroup rlt = this.mGroupMap.remove(groupName);
		if (rlt == null) {
			return false;
		} else {
			this.clearCache();
			return true;
		}
	}

	private void clearCache() {
		// TODO Auto-generated method stub

	}

	@Override
	public IRosterContact addContact(XmppAddress addr, String[] ownerGroups) {
		addr = this.normalAddress(addr);
		IRosterContact contact = this.getContact(addr, true);
		for (String g : ownerGroups) {
			IRosterGroup group = this.getGroup(g, true);
			group.bind(contact);
			contact.bind(group);
		}
		return contact;
	}

	class MyGroup implements IRosterGroup {

		private final String mGroupName;
		private String mName;

		private final ICachedMap mCachedMap = new CachedMap();

		public MyGroup(String groupName) {
			this.mGroupName = groupName;
		}

		@Override
		public IExRosterManager getRoster() {
			return RosterManagerImpl.this;
		}

		@Override
		public List<IRosterContact> listContacts() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean bind(IRosterContact group) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean unbind(IRosterContact group) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getName() {
			return this.mName;
		}
	}

	class MyContact implements IRosterContact {

		private final List<IRosterGroup> mOwnerGroupList;
		private final XmppAddress mAddr;
		private String mName;

		public MyContact(XmppAddress addr) {
			this.mAddr = addr;
			this.mOwnerGroupList = new ArrayList<IRosterGroup>();
		}

		@Override
		public IExRosterManager getRoster() {
			return RosterManagerImpl.this;
		}

		@Override
		public List<IRosterGroup> listOwnerGroups() {
			return this.mOwnerGroupList;
		}

		@Override
		public String getName() {
			return this.mName;
		}

		@Override
		public XmppAddress getAddress() {
			return this.mAddr;
		}

		@Override
		public boolean bind(IRosterGroup group) {
			boolean rlt = this.mOwnerGroupList.add(group);
			return rlt;
		}

		@Override
		public boolean unbind(IRosterGroup group) {
			boolean rlt = this.mOwnerGroupList.remove(group);
			return rlt;
		}
	}

	@Override
	public void push() {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<IRosterContact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IRosterGroup> getGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeContact(IRosterContact contact) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeGroup(IRosterGroup group) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pull() {
		// TODO Auto-generated method stub

	}

}
