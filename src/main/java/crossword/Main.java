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

        System.out.println();
        //Вот это кроссворд со строками вместо чаров
        String[][] strCrossword = CG.charToStrCrossword();
        CG.strCrosswordPrint(strCrossword);
    }
}

