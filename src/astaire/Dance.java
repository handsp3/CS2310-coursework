package astaire;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;


/**
 * Represents a dance and stores the relevant data about it and it's performers.
 * @author Paul Hands
 */
public class Dance {
	//The performers in the dance.
	private ArrayList<String> performers;
	
	
	
	public Dance(ArrayList<String> performers) {
		this.performers = performers;
	}
	
	/**
	 * List names all of the performers in this dance, return the value as a string.
	 * @return Performers in the dance
	 */
	public String listPerformers() {
		String data = "";
		//For each performer in the dance add their data and return it.
		for (String performer : performers) {
			performer = performer.trim();			
			data += performer + ", ";
		} 
		
		return data;
	}
	
	/**
	 * Determines if any time errors will occur for this dance given dancers already preparing.
	 * @param gaps The size of the gap required to rest (prepare).
	 * @param restingPerformers The performers currently preparing.
	 * @return String data detailing the dance and any time errors.
	 */
	public String checkFeasibilityOfRunningOrder(int gaps, LinkedHashSet<Performer> restingPerformers) {
		//List the dance data.
		String returnData = "";
		
		//Extract the names of performers in this dance.
		String[] performers = this.listPerformers().split(", ");
		
		//For each performer name in this dance.
		for (String performerName : performers) {
			
			//Convert to a performer object.
			Performer performer = new Performer(performerName, gaps);
			
			//Identify time errors and add data to the return string.
			if(restingPerformers.contains(performer)) {
				returnData += "**" + performerName + "**";
			} else {
				returnData += performerName;
				
			}
			
			returnData += ", ";
			
			//Add the new performer to the end of the queue...
			restingPerformers.add(performer);	
		}
		
		//Format string to end line.
		returnData = returnData.substring(0, returnData.length() - 2) + ".\n";

		return returnData;
	}
	
	/**
	 * Alphabetically sort all of the performers within the dance.
	 */
	public void sortPerformers() {
		
		identifyGroups();
		
		//Sorting algorithm
		for (int i = 0; i < performers.size(); i++) {
			
			int smallestIndex = i;
			String smallest = performers.get(i);
			
			//Identify the smallest value in the list.
			for (int j = i; j < performers.size(); j++) {
				
				String performerB = performers.get(j);
				
				if (performerB.compareTo(smallest) < 0) {
					smallest = performerB;
					smallestIndex = j;
				}
			}
			
			//Swap the values (smallest with index i)
			if (smallestIndex != i) {
				String performerA = performers.get(i);
				performers.set(i, smallest);
				performers.set(smallestIndex, performerA);
			}
		}
		
	}
	
	/**
	 * Identify any groups within the list of performers and replace them with the names of their members for ordering.
	 */
	private void identifyGroups() {
		
		Iterator<String> iterator = performers.iterator();
		
		ArrayList<String> newPerformers = new ArrayList<String>();
		
		while (iterator.hasNext()) {
			//Check if performer name is a dance group.
			String dancer = iterator.next();
			
			Groups groups = Groups.getGroups();
			DanceGroup group = groups.findGroup(dancer.trim());
			
			//If so, remove it and add all it's members to 'newPerformers'
			if (group != null) {
				iterator.remove();
				newPerformers.addAll(group.listMembers());
			} 
		}
		
		//Add all of newPerformers to performers.
		performers.addAll(newPerformers);
	}
}
