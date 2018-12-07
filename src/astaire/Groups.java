package astaire;

import java.util.HashMap;

/**
 * Stores all of the current dance groups so they can be accessed by the
 * relevant classes when the data is required.
 * 
 * @author Muhammed Avais Hussain
 */
public class Groups {

	// Static HashMap called groups with String Key and DanceGroup value.
	private static HashMap<String, DanceGroup> groups;

	/**
	 * Add a group to the collection.
	 * 
	 * Static method addGroup with parameters name and members of type string, If
	 * groups HashMap is null then create a new object, Create new DanceGroup object
	 * and give it members parameter, Put the name from the parameter as the key and
	 * set the newGroup as the value with members.
	 * 
	 * @param name
	 * @param members
	 */
	public static void addGroup(String name, String members) {
		if (groups == null) {
			groups = new HashMap<String, DanceGroup>();
		}

		DanceGroup newGroup = new DanceGroup(members);

		groups.put(name, newGroup);
	}

	/**
	 * Find if a group is in the collection, if it is return it.
	 * 
	 * @param groupName
	 * @return
	 */
	public static DanceGroup findGroup(String groupName) {
		DanceGroup group = groups.get(groupName);
		return group;
	}
}