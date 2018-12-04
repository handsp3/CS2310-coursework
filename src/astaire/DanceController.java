package astaire;

import io.AstaireFileReader;

/**
 * Central controller for the DanceSchool Program. Provides implemented methods for the four features that the system
 * is required to perform.
 * @author 
 */
public class DanceController implements Controller {

	private DanceShow show;
	
	public DanceController() {
		show = AstaireFileReader.readShowFile("data/danceShowData_dances.csv");
		AstaireFileReader.readGroupsFile("data/danceShowData_danceGroups.csv"); 
	}
	
	/**
	 * List all of the dancers in a specified dance.
	 */
	public String listAllDancersIn(String dance) {
		return show.listAllPerformers(dance);
	}
	
	/**
	 * List all of the dance numbers with the respective dancers in alphabetical order.
	 */
	@Override
	public String listAllDancesAndPerformers() {
		return show.listAll();
		
	}

	/**
	 * Determine if the dancers will have enough time to change their costume with a given running order.
	 */
	@Override
	public String checkFeasibilityOfRunningOrder(String fileName, int gaps) {
		// TODO Auto-generated method stub\
		DanceShow runningOrder = AstaireFileReader.readShowFile(fileName);
		return DanceShow.checkFeasibilityOfRunningOrder(runningOrder, gaps);
	}

	/**
	 * Generate a running order.
	 */
	@Override
	public String generateRunningOrder(int gaps) {
		String newRunningOrder = show.generateRunningOrder(gaps);
		return newRunningOrder;
	}

}
