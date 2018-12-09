package astaire;

import io.AstaireFileReader;

/**
 * Central controller for the DanceSchool Program. Provides implemented methods for the four features that the system
 * is required to perform.
 * @author Paul Hands
 */
public class DanceController implements Controller {

	private DanceShow show;
	
	public DanceController() {
		show = AstaireFileReader.readShowFile("danceShowData_dances.csv");
		AstaireFileReader.readGroupsFile("danceShowData_danceGroups.csv"); 
	}
	
	/**
	 * List all of the dancers in a specified dance.
	 * @param dance Name of the dance to list performers from.
	 * @see DanceShow
	 */
	public String listAllDancersIn(String dance) {
		return show.listAllPerformers(dance);
	}
	
	/**
	 * List all of the dance numbers with the respective dancers in alphabetical order.
	 * @see DanceShow
	 */
	@Override
	public String listAllDancesAndPerformers() {
		return show.listAll();
		
	}

	/**
	 * Determine if the dancers will have enough time to change their costume with a given running order.
	 * @param fileName Name of the file to extract data from.
	 * @param gaps Number of dances required for a dancer to prepare for their next dance.
	 * @see DanceShow
	 */
	@Override
	public String checkFeasibilityOfRunningOrder(String fileName, int gaps) {
		// TODO Auto-generated method stub\
		DanceShow runningOrder = AstaireFileReader.readShowFile(fileName);
		return DanceShow.checkFeasibilityOfRunningOrder(runningOrder, gaps);
	}

	/**
	 * Generate a running order.
	 * @see DanceShow
	 * @param gaps Number of dances required for a dancer to prepare for their next dance.
	 */
	@Override
	public String generateRunningOrder(int gaps) {
		String newRunningOrder = show.generateRunningOrder(gaps);
		return newRunningOrder;
	}

}
