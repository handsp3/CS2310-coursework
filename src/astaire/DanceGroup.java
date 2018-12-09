package astaire;

/**
 * Define a dance group and store relevant data about it's members.
 * @author Paul Hands 
 * 
 */
public class DanceGroup {
	
	///Members of the group.
	private String groupMembers;
	
	public DanceGroup(String members) {
		groupMembers = members;
	}
	
	/**
	 * List the names of the dance group's members.
	 * @return Members of the group.
	 */
	public String listMembers() {
		return groupMembers;
	}
}
