package astaire;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * Defines a dance show, which stores a list of dances, provides methods for the controller to access.
 * @author
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
	 * @param id
	 * @return
	 */
	public String listAllPerformers(String danceName) {
		//If the dance is in the show...
		if (dances.containsKey(danceName)) {
			//return it.
			return dances.get(danceName).listPerformers();
		}
		//Otherwise return an error message.
		return "The dance does not exist. ";
	}
	
	/**
	 * List all dance numbers and the names of the relevant performers in alphabetical order.
	 * @return
	 */
	public String listAll() {
		String data = "";
		//For each dance in the show...
		for (String danceName : dances.keySet()) {
			Dance dance = dances.get(danceName);
			//Add the dance's data to the return string.
			data += dance.getNumber() + ":    ";
			data += dance.listPerformers() + "\n";
		}
		
		return data;
	}
	
	/**
	 * Generate a running order for the dance show.
	 * @return
	 */
	public String generateRunningOrder(int gaps) {		
		//Initialize runningOrder
		DanceShow newRunningOrder = new DanceShow();
		
		//Initialize queue of performers
		LinkedHashSet<Performer> performerQueue = new LinkedHashSet<Performer>();
		
		
		//For each dance...
		for (String danceName : dances.keySet()) {
			Dance dance = dances.get(danceName);
			
			//Extract performers...
			String[] performers = dance.listPerformers().split(", ");
			
			//Initialize boolean
			boolean valid = true;
			
			//Check if any of the performers are already in the queue.
			for (Performer performer : performerQueue) {
				for (String performerName: performers) {
					if (performerName.contains(performer.getName())) {
						valid = false;	
					}
				}
			}
			
			//If valid...
			if (valid == true) {
				//Add the dance
				newRunningOrder.addDance(dance, danceName);
				
				//Update performers.
				Iterator<Performer> iterator = performerQueue.iterator();
				
				while (iterator.hasNext()) {
					Performer performer = iterator.next();
					performer.updateNumDances();
					if (performer.getNumDances() == 0) {
						iterator.remove();
					}
				}
				
				//Add new performers.
				for (String performerName : performers) {
					Performer performer = new Performer(performerName, gaps);
					performerQueue.add(performer);
				}
			}
		}
		
		//Check if the runningOrder generation was successful. (Must have a certain number of dances included.
		if (newRunningOrder.getDances().size() > 1) {
			return newRunningOrder.listAll();
		} else {
			return "No such running order exists";
		}
	}
	
	/**
	 * Determine if the performers will have enough time to change their costume between dances.
	 * @see Dance
	 * @return Dance show data with tags where any time errors appear to show that a running order is not feasible.
	 */
	public static String checkFeasibilityOfRunningOrder(DanceShow runningOrder, int gaps) {
		//Initialize the queue.
		
		//ArrayDeque<Performer> performerQueue = new ArrayDeque<Performer>();
		LinkedHashSet<Performer> restingPerformers = new LinkedHashSet<Performer>();
		
		//Initialize return data (String).
		String returnData = "";

		//Get list of dances.
		LinkedHashMap<String, Dance> runningOrderDances = runningOrder.getDances();
		
		//For each dance...
		for(String danceName : runningOrderDances.keySet()) {
			//...
			Dance dance = runningOrderDances.get(danceName);
			returnData += dance.checkFeasibilityOfRunningOrder(gaps, restingPerformers);
		}
		
		return returnData;
	}
	
	/**
	  * Add a dance to the dance show given the required data. 
	  */
	public void addDance(String name, String[] performers) {
		//Create the dance from the given data.
		ArrayList<String> dancers = new ArrayList<String>();
		for (String performer : performers) {
			dancers.add(performer);
		}
		
		Dance dance = new Dance(name, currentDanceNumber, dancers);
		
		dances.put(name, dance);
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
	 * @return
	 */
	public LinkedHashMap<String, Dance> getDances() {
		return dances;
	} 
}
