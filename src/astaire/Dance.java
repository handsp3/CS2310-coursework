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
	 * List names all of the performers in this dance, return the value as a string.
	 * @return Performers in the dance
	 */
	public String listPerformers() {
		String data = "";
		//For each performer in the dance add their data and return it.
		for (String performer : performers) {
			performer = performer.trim();
			
			//Check if the 'performer' is a guest dancer or a group name.
			DanceGroup group = Groups.findGroup(performer);
			
			if (group != null) {
				data += group.listMembers() + ", ";
			} else {
				data += performer += ", ";
			}
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
		String returnData = this.getNumber() + ":\t";
		returnData += String.format("%-30s", this.getName());
		
		//Extract the names of performers in this dance.
		String[] performers = this.listPerformers().split(", ");
		
		//For each performer name in this dance.
		for (String performerName : performers) {
			
			//Convert to a performer object.
			Performer performer = new Performer(performerName, gaps);
			
			//Identify time errors and add data to the return string.
			if(restingPerformers.contains(performer)) {
				returnData += "<<" + performerName + ">>";
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
