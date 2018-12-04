package astaire;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;


/**
 * Define a dance and store the relevant data about it and it's performers.
 * @author
 */
public class Dance {
	//The performers in the dance.
	private ArrayList<String> performers;
	//Unique dance Number.
	private int number;
	//Name of the dance.
	private String danceName;
	
	
	public Dance(String name, int number, ArrayList<String> performers) {
		this.number = number;
		this.danceName = name;
		this.performers = performers;
	}
	
	/**
	 * List all of the performers in this dance, return the value as a string.
	 * @return
	 */
	public String listPerformers() {
		String data = "";
		//For every performer..
		for (String performer : performers) {
			//Remove white space...
			performer = performer.trim();
			//Determine if it is a groupName.
			DanceGroup group = Groups.findGroup(performer);
			//If it is...
			if (group != null) {
				//List all group members...
				data += group.listMembers() + ", ";
			} else {
				//Otherwise 
				data += performer += ", ";
			}
		} 
		
		return data;
	}
	
	/**
	 * Determines if any time errors will occur for this dance.
	 * @param gaps The size of the gap required to rest (prepare)
	 * @param restingPerformers The performers currently preparing
	 * @return String data detailing the dance and any time errors.
	 */
	public String checkFeasibilityOfRunningOrder(int gaps, LinkedHashSet<Performer> restingPerformers) {
		String returnData = "";
		//List the dance name...
		returnData += this.getNumber() + ":\t";
		returnData += this.getName() + "\t";
		//Extract the performers names...
		String[] performers = this.listPerformers().split(", ");
		
		
		//Update the existing performers data...

		Iterator<Performer> iterator = restingPerformers.iterator();
		
		while (iterator.hasNext()) {
			Performer performer = iterator.next();
			performer.updateNumDances();
			if (performer.getNumDances() == 0) {
				iterator.remove();
			}
		}
		
		//For each performer name...
		for (String performerName : performers) {
			//Convert to a performer object...
			Performer performer = new Performer(performerName, gaps);
			
			//Add it's name...
			returnData += performer.getName();
			//Identify time errors...
			
			if(restingPerformers.contains(performer)) {
				returnData += " <<TIME-ERROR>>";
			}
			//Separate...
			returnData += ", ";
			//Add the new performer to the end of the queue...
			restingPerformers.add(performer);
		}
		//End line.
		returnData += "\n";

		return returnData;
	}
	
	/**
	 * Return the unique dance number.
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Return the dance name.
	 */
	public String getName() {
		return danceName;
	}
}
