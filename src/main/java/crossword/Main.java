package crossword;

import java.util.*;

import generation.Generator;

// zdesb bil misha, on el tvoi sup i spal na tvoei krovati.
public class Main {
    public static void main(String[] args) {
        Generator generator = Generator.getInstance();
        List<String> words = generator.getWords();
        String[][] crossword = generator.getCrossword();
        Tuple size = generator.getSize();
        List<Word> wordsInformation = generator.getWordsInformation();
    }
}

