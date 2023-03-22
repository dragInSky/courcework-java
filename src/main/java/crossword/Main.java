package crossword;

// zdesb bil misha, on el tvoi sup i spal na tvoei krovati.
public class Main {

    public static void main(String[] args) {
        String[] words = {"кот", "кран", "озеро"};

        CrosswordGenerator CG = new CrosswordGenerator(words);

        CG.crosswordFill();

        for (String word : words)
            if (!CG.getUsedWords().contains(word))
                CG.appendIncompatible(word);

        CG.clearEmptyCells();
        CG.crosswordPrint();
    }
}

