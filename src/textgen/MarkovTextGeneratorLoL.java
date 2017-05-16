package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	String pattern = "[!?.]+|[a-zA-Z]+";

	Pattern tokSplitter;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<>();
		starter = "";
		rnGenerator = generator;
		tokSplitter = Pattern.compile(pattern);
	}

	private List<String> getTokens(String text) {

		ArrayList<String> tokens = new ArrayList<>();
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {

		List<String> token = this.getTokens(sourceText);

		if (token.isEmpty())
			return;

		starter = token.get(0);

		String previousWord = token.get(0);

		if (token.size() == 1)
			return;

		for (int i = 1; i < token.size(); ++i) {

			ListNode listNode = getListNode(previousWord);

			if (listNode == null) {

				listNode = new ListNode(previousWord);
				listNode.addNextWord(token.get(i));
				wordList.add(listNode);

			} else {
				listNode.addNextWord(token.get(i));
			}

			previousWord = token.get(i);
		}

		starter = token.get(token.size() - 1);
	}

	private ListNode getListNode(String text) {

		for (ListNode listNode : wordList) {
			if (listNode.getWord().equals(text)) {
				return listNode;
			}
		}

		return null;
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {

		if (numWords == 0)
			return "";

		String currentWord = starter;

		StringBuilder output = new StringBuilder();
		output.append(currentWord);
		output.append(" ");

		for (int i = 1; i < numWords; ++i) {

			ListNode listNode = getListNode(currentWord);

			try {

				String nextWord = listNode.getRandomNextWord(rnGenerator);
				output.append(nextWord);
				output.append(" ");
				currentWord = nextWord;
			} catch (NullPointerException e) {
			}
		}

		return output.toString();
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {

		wordList = new LinkedList<>();
		starter = "";

		this.train(sourceText);
	}

	// TODO: Add any private helper methods you need here.

	/**
	 * This is a minimal set of tests. Note that it can be difficult to test
	 * methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
				+ "Oh no. You say goodbye and I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "I say high, you say low, "
				+ "You say why, and I say I don't know. " + "Oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "Why, why, why, why, why, why, "
				+ "Do you say goodbye. " + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "You say yes, I say no, "
				+ "You say stop and I say go, go, go. " + "Oh, oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {

		int index = new Random().nextInt(nextWords.size());
		return nextWords.get(index) == null ? "" : nextWords.get(index);
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}
