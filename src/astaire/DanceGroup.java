package astaire;

import java.util.ArrayList;

/**
 * Define a dance group and store relevant data about it's members.
 * @author Paul Hands 
 * 
 */
public class DanceGroup {
	
	///Members of the group.
	private ArrayList<String> groupMembers;
	
	public DanceGroup(ArrayList<String> members) {
		groupMembers = members;
	}
	
	/**
	 * List the names of the dance group's members.
	 * @return Members of the group.
	 */
	public ArrayList<String> listMembers() {
		return groupMembers;
	}
}
