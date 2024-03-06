package suggestion;

import java.util.ArrayList;

/**
 * List of suggestions.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class SuggestionList {
	/**
	 * Array list to store suggestions.
	 */
	private ArrayList<Suggestion> SuggestionList;
	
	/**
	 * Constructor of SuggestionList.
	 */
	public SuggestionList() {
		this.SuggestionList = new ArrayList<Suggestion>();
	}
	/**
	 * Accessor of SuggestionList.
	 * @return This Suggestion List.
	 */
	public ArrayList<Suggestion> getSuggestionList(){
		return SuggestionList;
	}
	
	/**
	 * Add suggestion into SuggestionList.
	 * @param Suggestion The suggestion by user.
	 */
	public void add(Suggestion Suggestion) {
		this.SuggestionList.add(Suggestion);
	}
	
	/**
	 * Get size of SuggestionList.
	 * @return The number of suggestions.
	 */
	public int size() {
		return SuggestionList.size();
	}
	
	/**
	 * Get Suggestion object at index i.
	 * @param i The index of the suggestion in the suggestion list.
	 * @return Index i of SuggestionList.
	 */
	public Suggestion get(int i) {
		return SuggestionList.get(i);
	}
	
	/**
	 * Set the Suggestion object at index i to the new Suggestion object.
	 * @param i The index of the suggestion in the suggestion list.
	 * @param Suggestion The suggestion that will be set at index i.
	 */
	public void set(int i, Suggestion Suggestion) {
		SuggestionList.set(i, Suggestion);
	}
	
	/**
	 * Remove the Suggestion object at index i from SuggestionList.
	 * @param i The index of the suggestion in the suggestion list.
	 */
	public void remove(int i) {
		SuggestionList.remove(i);
	}
	
	/**
	 * Converts to string.
	 * @return Suggestion List as string.
	 */
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (Suggestion Suggestion : SuggestionList) {
	        sb.append(Suggestion.getName()).append(", ");
	    }
	    return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : ""; 
	}
}
