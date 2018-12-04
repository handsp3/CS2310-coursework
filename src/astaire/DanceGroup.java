package astaire;

/**
 * Define a dance group and store relevant data about it and it's members.
 * @author 
 * 
 */
public class DanceGroup {
	
	/*
	 * Store group member data as a pure string...
	 * 
	 * 
	 * 
	 */
	
	
	///Members of the group.
	
	//private ArrayList<String> groupMembers;
	private String groupMembers;
	
	//Name of the group.
	//private String groupName;
	
	public DanceGroup(String members /*, String name */) {
		//groupName = name;
		//groupMembers = new ArrayList<String>();
		
		groupMembers = members;
	}
	
	/**
	 * List the names of the dance group's members
	 * @return
	 */
	public String listMembers() {
		/*
		String data = "";
		for (String name : groupMembers) {
			data += name + ", ";
		}
		
		return data;
		*/
		return groupMembers;
	}
	
	/**
	 * Return the name of the group.
	 * @return
	 */
	/*
	public String getName() {
		return groupName;
	}
	*/
}
