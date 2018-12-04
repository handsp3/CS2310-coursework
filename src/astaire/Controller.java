/**
 * 
 */
package astaire;

/**
 * A controller for the dance show programme generator system.
 * This controller includes the 4 features that the intended
 * prototype system is expected to have.
 * 
 * @author Sylvia Wong
 * @version 08/11/2018
 */
public interface Controller {
	
	/**
	 * Lists the names of all performers in a specified dance.
	 * @param dance	a specified dance in the dance show
	 * @return the name of all performers that are in the specified dance. 
	 */
	String listAllDancersIn(String dance);
	
	/**
	 * Lists all dance numbers and the name of the respective performers in alphabetical order.
	 * @return	a String representation of dance numbers 
	 * 			and the name of the respective performers in alphabetical order
	 */
	String listAllDancesAndPerformers();

	/**
	 * Checks feasibility of a given running order.
	 * @param filename	the name of a tab-separated CSV file containing a proposed running order
	 * @param gaps the required number of gaps between dances for each dancer
	 * @return	a String representation of potential issues
	 */
	String checkFeasibilityOfRunningOrder(String filename, int gaps);
	
	/**
	 * Generates a running order of the dances for the dance show.
	 * @param gaps the required number of gaps between dances for each dancer
	 * @param  
	 * @return	a String representation of the generate running order
	 */
	String generateRunningOrder(int gaps);
}
