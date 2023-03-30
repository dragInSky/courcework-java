package crossword;

import java.util.ArrayList;
import java.util.List;

// zdesb bil misha, on el tvoi sup i spal na tvoei krovati.
public class Main {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of("оса", "паук", "муха", "деревня", "яд", "еж", "theatre", "zoo", "octet"));
        CrosswordGenerator ultimateCG = null;
        while (!words.isEmpty()) {
            CrosswordGenerator CG = createCrossword(words);
            if (ultimateCG == null)
                ultimateCG = CG;
            ultimateCG.mergeCrosswords(CG);
        }

        if (ultimateCG != null) {
            ultimateCG.crosswordPrint();

            //Вот это кроссворд со строками вместо чаров
            String[][] strCrossword = ultimateCG.charToStrCrossword();
        }
    }

    private static CrosswordGenerator createCrossword(List<String> words) {
        CrosswordGenerator CG = new CrosswordGenerator(words);
        CG.crosswordFill();

        List<String> leftWords = new ArrayList<>();
        for (String word : words)
            if (!CG.getUsedWords().contains(word))
                leftWords.add(word);

        CG.clearEmptyCells();

        words.clear();
        words.addAll(leftWords);

        return CG;
    }
}

