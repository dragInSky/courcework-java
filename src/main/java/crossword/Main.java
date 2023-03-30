package crossword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// zdesb bil misha, on el tvoi sup i spal na tvoei krovati.
public class Main {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of(
                "оса", "паук",
                "муха",
                "деревня", "яд", "еж",
                "theatre", "zoo", "octet"
        ));

        CrosswordGenerator ultimateCG = createCrossword(words);

        while (!words.isEmpty())
            ultimateCG.mergeCrosswords(createCrossword(words));

        ultimateCG.crosswordPrint();

        String[][] strCrossword = ultimateCG.charToStrCrossword();
        Tuple size = ultimateCG.getSize();
        HashMap<String, Boolean> wordsWithOrientation = ultimateCG.getWordsWithOrientation();
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

