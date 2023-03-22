package org.example;

// zdesb bil misha, on el tvoi sup i spal na tvoei krovati.
public class Main {

    public static void main(String[] args) {
        String[] words = {"диаметр", "лось", "математика", "окорок", "левша", "яя"};

        CrosswordGenerator CG = new CrosswordGenerator(words);

        CG.initCrossword(words);
        CG.crosswordFill(words);

        for (String word : words)
            if (!CG.getUsedWords().contains(word))
                System.out.println(word);

        CG.clearEmptyCells();
        CG.crosswordPrint();
    }
}

