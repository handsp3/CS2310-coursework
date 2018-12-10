package astaire;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Defines a dance show, which stores a list of dances, provides methods for the controller to access.
 * @author Paul Hands
 */
public class DanceShow {
	
	//The dances in the dance show.
	private LinkedHashMap<String, Dance> dances;
	//Current dance number, used for assigning to dances when created.
	private int currentDanceNumber;
	
	public DanceShow() {
		dances = new LinkedHashMap<String, Dance>();
		currentDanceNumber = 0;
	}
	
	/**
	 * List all of the performers in a specified dance.
	 * @param danceName Name of the dance.
	 * @return String data containing a list of performer names in the dance.
	 */
	public String listAllPerformers(String danceName) {
		//If the dance is in the show...
		if (dances.containsKey(danceName)) {
			//Format and return it.
			String data = "\nDancers:\t" + dances.get(danceName).listPerformers();
			return data.substring(0, data.length() - 2) + ".\n";
		}
		//Otherwise return an error message.
		return "\nThe dance does not exist. ";
	}
	
	/**
	 * List all dance numbers and the names of the relevant performers in alphabetical order.
	 * @return String data containing dance numbers and their respective dancers all on individual lines.
	 */
	public String listAll() {
		String data = "\n";
		//For each dance in the show...
		for (Map.Entry<String, Dance> entry : dances.entrySet()) {
			Dance dance = entry.getValue();
			
			//Add the dance's data to the return string.
			data += String.format("%-40s", dance.getName() + ":");
			data += dance.listPerformers();
			data = data.substring(0, data.length() - 2) + ".\n";
		}
		
		return data;
	}
	
	/**
	 * Generate a running order for the dance show.
	 * @param gaps Number of dances required for a dancer to prepare for their next dance.
	 * @return String data for the generated running order.
	 */
	public String generateRunningOrder(int gaps) {		
		//Initialize runningOrder
		DanceShow newRunningOrder = new DanceShow();
		
		//Initialize queue of performers
		LinkedHashSet<Performer> performerQueue = new LinkedHashSet<Performer>();
		
		int numDances = 0;
		int previousNumDances = 0;
		boolean generating = true;
		
		//For each dance in the map
		while (generating) {
			for (Map.Entry<String, Dance> entry : dances.entrySet()) {
				
				Dance dance = entry.getValue();
				String danceName = entry.getKey();
				
				//Extract performers...
				String[] performerNames = dance.listPerformers().split(", ");
				
				//Initialize boolean
				boolean valid = true;
				
				//Check if any of the performers are already in the queue using names.
				for (String performerName : performerNames) {
					for (Performer performer : performerQueue) {
						if (performerName.equals(performer.getName())) {
							valid = false;	
						}
						
					}
				}
				
				if (newRunningOrder.contains(dance)) {
					valid = false;
				}
				
				//If the dance is valid...
				if (valid == true) {
					//Add the dance
					newRunningOrder.addDance(dance, danceName);
					numDances++;
					
					//Update existing performers.
					Iterator<Performer> iterator = performerQueue.iterator();
					
					while (iterator.hasNext()) {
						Performer performer = iterator.next();
						performer.updateNumDances();
						if (performer.getNumDances() == 0) {
							iterator.remove();
						}
					}
					
					//Add new performers.
					for (String performerName : performerNames) {
						Performer performer = new Performer(performerName, gaps);
						performerQueue.add(performer);
					}
				}
			}
			
			if (numDances > 10) {
				generating = false;
			} else if (numDances == previousNumDances) {
				generating = false;
			}
			
			previousNumDances = numDances;
		}
		
		return "\nGenerated running order contains " + numDances + " dances:\n" + newRunningOrder.listAll();
	}
	
	/**
	 * Determine if the performers will have enough time to change their costume between dances.
	 * @see Dance
	 * @param gaps Number of dances required for a dancer to prepare for their next dance.
	 * @param runningOrder The running order to check feasibility of.
	 * @return Dance show data with tags where any time errors appear to show that a running order is not feasible.
	 */
	public static String checkFeasibilityOfRunningOrder(DanceShow runningOrder, int gaps) {

		//Initialize the queue.
		LinkedHashSet<Performer> restingPerformers = new LinkedHashSet<Performer>();
		
		String returnData = "\n\n";

		//Get list of dances.
		LinkedHashMap<String, Dance> runningOrderDances = runningOrder.getDances();
		
		//For each dance...
		for(String danceName : runningOrderDances.keySet()) {
		
			Dance dance = runningOrderDances.get(danceName);
			//Extract data and determine feasibility of running order.
			returnData += dance.checkFeasibilityOfRunningOrder(gaps, restingPerformers);
			
			//Update the existing performers data.
			Iterator<Performer> iterator = restingPerformers.iterator();
			
			while (iterator.hasNext()) {
				Performer performer = iterator.next();
				performer.updateNumDances();
				if (performer.getNumDances() == 0) {
					iterator.remove();
				}
			}
		}
		
		returnData += "\n\nNOTE:\n<<name>> indicates a time error, where a performer or group does \nnot have enough time to prepare for their next dance.";
		
		return returnData;
	}
	
	/**
	  * Add a dance to the dance show given the required data. 
	  * @param danceName The name of the dance.
	  * @param performers The performers in the dance.
	  */
	public void addDance(String danceName, ArrayList<String> performers) {
		
		Dance dance = new Dance(danceName, currentDanceNumber, performers);
		
		//Add it to the show.
		dances.put(danceName, dance);
		currentDanceNumber++;
	}
	
	/**
	 * Add a dance to the dance show given the name of the dance and a Dance object.
	 * @param dance, the dance object.
	 * @param danceName, the name of the dance.
	 */
	public void addDance (Dance dance, String danceName) {
		dances.put(danceName, dance);
	}
	
	/**
	 * Return the collection of dances.
	 * @return dances
	 */
	public LinkedHashMap<String, Dance> getDances() {
		return dances;
	}
	
	/**
	 * Sort each of the dances in the show.
	 */
	public void sortDancers() {
		for (Map.Entry<String, Dance> entry :dances.entrySet()) {
			Dance dance = entry.getValue();
			dance.sortPerformers();
		}
	} 
	
	public boolean contains(Dance dance) {
		if (dances.containsValue(dance)) {
			return true;
		}
		return false;
	}
}
