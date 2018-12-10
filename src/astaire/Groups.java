package astaire;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores all of the current dance groups so they can be accessed by the
 * relevant classes when the data is required.
 * 
 * @author Muhammed Avais Hussain
 */
public class Groups {

	// Static HashMap called groups with String Key and DanceGroup value.
	private HashMap<String, DanceGroup> groups;

	private static Groups groupInstance;

	private Groups() {
		groups = new HashMap<String, DanceGroup>();
	}

	public static Groups getGroups() {
		if (groupInstance == null) {
			groupInstance = new Groups();
		}
		return groupInstance;
	}

	/**
	 * Add a group to the collection.
	 * 
	 * @param name    Name of the groups.
	 * @param members List of the group members.
	 */
	public void addGroup(String name, ArrayList<String> members) {
		if (groups == null) {
			groups = new HashMap<String, DanceGroup>();
		}

		DanceGroup newGroup = new DanceGroup(members);

		groups.put(name, newGroup);
	}

	/**
	 * Find if a group is in the collection, if it is return it.
	 * 
	 * @param groupName Name of the group.
	 * @return returning the group.
	 */
	public DanceGroup findGroup(String groupName) {
		DanceGroup group = groups.get(groupName);
		return group;
	}
}