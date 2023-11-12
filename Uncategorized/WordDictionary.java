import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1)	Design a custom Word Dictionary that provides definitions about the words.
// Users should be able to lookup specific words and find their corresponding definitions,
// add/remove words with definitions and search words partially.

// The dictionary should have a unique definition for the given word and these words are case insensitive.
// Please implement the below WordDictionary class without using built-in java HashMap.


public class WordDictionary {
    private List<String> words;
    private List<String> definitions;

    public WordDictionary() {
		// initializes the WordDictionary
        this.words = new ArrayList<String>();
        this.definitions = new ArrayList<String>();
	}
		
	public void insertWord(String word, String definition) {
		// Inserts the word and corresponding definition into the dictionary,
        // if the word already exists, it will override the word and definition

        checkEdgeCasesWords(word); //edge case check

        int wordIdx = words.indexOf(word.toLowerCase());
        if(wordIdx == -1) {
            words.add(word.toLowerCase());
            definitions.add(definition);
        } else {
            definitions.set(wordIdx, definition);
        }
	}
	
    public String findDefinition(String word) {
        // Returns the definition for the word

        checkEdgeCasesWords(word); //edge case check

        int wordIdx = words.indexOf(word.toLowerCase());
        if(wordIdx != -1) {
            return definitions.get(wordIdx);
        } else {
            throw new IllegalArgumentException("Word not in Dictionary");
        }
    }
	
	public List<String> partialSearch(String partialWord) {
        // Returns the definitions for the words that are matched partially

        checkEdgeCasesWords(partialWord); //edge case check

        List<String> definitionList = new ArrayList<>();

        for (int i = 0; i < words.size(); i ++){
            if(words.get(i).contains(partialWord.toLowerCase())) {
                definitionList.add(definitions.get(i));
            }
        }
        return definitionList;
	}
	
	public void remove(String word) {
		// Removes the word with definition from the dictionary

        checkEdgeCasesWords(word); //edge case check

        int wordIdx = words.indexOf(word.toLowerCase());
        if(wordIdx != -1) {
            words.remove(wordIdx);
            definitions.remove(wordIdx);
        } else {
            throw new IllegalArgumentException("Word not in Dictionary");
        }
	}

    private void checkEdgeCasesWords(String word) {
        //edge case for more than 1 word or spaces
        if(word.contains(" ")) {
            throw new IllegalArgumentException("Word should not have spaces");
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();

        wordDictionary.insertWord("old", "Belongs in the past or lived long");
        wordDictionary.insertWord("gold", "Chemical element of atomic number 79");
        wordDictionary.insertWord("GoldenRetriever", "Large dog breed with long straight yellow hair");
        wordDictionary.insertWord("Poodle", "Dog breed with curly hair of various sizes");
        wordDictionary.insertWord("GoldenDoodle", "Dog mixed breed of Golden Retriever and poodle");
        wordDictionary.insertWord("labrador", "Dog mixed breed of Golden Retriever and poodle");
        wordDictionary.insertWord("LabraDoodle", "dog mixed breed of Labrador and poodle");
        wordDictionary.insertWord("BARBIE", "Famous Doll Brand");
        wordDictionary.insertWord("Oppenheimer", "Father of the Atomic Bomb");
        wordDictionary.insertWord("Barbenheimer", "Cultural Movie release of movies Barbie and Oppenheimer");

        System.out.println("findDefinition for old = "+ wordDictionary.findDefinition("old"));
        System.out.println("findDefinition for GOLd = "+ wordDictionary.findDefinition("GOLd"));

        System.out.println("partialSearch for oLd = "+ Arrays.toString(wordDictionary.partialSearch("oLd").toArray()));
        System.out.println("partialSearch for L = "+ Arrays.toString(wordDictionary.partialSearch("L").toArray()));
        System.out.println("partialSearch for e = "+ Arrays.toString(wordDictionary.partialSearch("e").toArray()));

        System.out.println("partialSearch for bAr = "+ Arrays.toString(wordDictionary.partialSearch("bAr").toArray()));
        wordDictionary.remove("bArBIe");
        System.out.println("partialSearch for bAr after deleting bArBIe = "+ Arrays.toString(wordDictionary.partialSearch("bAr").toArray()));
    }
}
