package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary {

	private LinkedList<String> dict;

	/**
	 * Add this word to the dictionary. Convert it to lowercase first for the
	 * assignment requirements.
	 * 
	 * @param word
	 *            The word to add
	 * @return true if the word was added to the dictionary (it wasn't already
	 *         there).
	 */
	
	public DictionaryLL(){
		dict = new LinkedList<>();
	}
	public boolean addWord(String word) {

		String wordLower = word.toLowerCase();
		
		if (!dict.contains(wordLower)) {
			dict.add(wordLower);
			return true;
		} else
			return false;
	}

	/** Return the number of words in the dictionary */
	public int size() {
		return dict.size();
	}

	/** Is this a word according to this dictionary? */
	public boolean isWord(String s) {

		if (dict.contains(s.toLowerCase()))
			return true;
		else
			return false;
	}

}
