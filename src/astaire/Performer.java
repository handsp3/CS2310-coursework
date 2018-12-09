package astaire;

/**
 * Represents a performer, used when generating a running order, or checking the feasibility of an existing order.
 * @author Paul Hands
 *
 */
public class Performer extends java.lang.Object {
	//The performer's name
	private String name;
	//Number of dances 
	private int numDances;
	
	public Performer(String name, int numDances) {
		this.name = name;
		this.numDances = numDances;
	}
	
	/**
	 * Get the name of the performer.
	 * @return the performer's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the remaining number of dances the performer needs to prepare.
	 * @return numDances.
	 */
	public int getNumDances() {
		return numDances;
	}
	
	/**
	 * Reduce the number of dances by one.
	 */
	public void updateNumDances() {
		if (numDances > 0) {
			numDances--;
		}
	}
	
	/**
	 * Identify if the performer is equal to another given performer.
	 * @param other, The other performer.
	 * @return whether the performers are equal or not.
	 */
	@Override
	public boolean equals(Object other) {
		Performer otherPerformer = (Performer) other;
		if (this.name.equals(otherPerformer.getName())) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Return the HashCode for this performer.
	 */
	public int hashCode() {
		return name.hashCode();
	}
	
	
}
