package crossword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// zdesb bil misha, on el tvoi sup i spal na tvoei krovati.
public class Main {
    private static String[][] strCrossword;
    private static Tuple size;
    private static HashMap<String, Boolean> wordsWithOrientation;

    public static String[][] getCrossword() {
        return strCrossword;
    }

    public static Tuple getSize() {
        return size;
    }

    public static HashMap<String, Boolean> getWordsMap() {
        return wordsWithOrientation;
    }

    public static void main(String[] args) {
//        List<String> words = new ArrayList<>(List.of(
//                "оса", "паук",
//                "муха",
//                "деревня", "яд", "еж",
//                "theatre", "zoo", "octet"
//        ));

//        List<String> words = new ArrayList<>(List.of(
//                "высота", "радиус", "циркуль", "диаметр", "центр", "хорда"
//        ));

        List<String> words = new ArrayList<>(List.of(
                "фрезер", "фуганок", "пила", "молоток", "ножовка", "гвоздодёр", "стамеска", "топор", "рейсмус"
        ));

        CrosswordGenerator ultimateCG = createCrossword(words);

        while (!words.isEmpty())
            ultimateCG.mergeCrosswords(createCrossword(words));

        ultimateCG.crosswordPrint();

        strCrossword = ultimateCG.charToStrCrossword();
        size = ultimateCG.getSize();
        wordsWithOrientation = ultimateCG.getWordsWithOrientation();
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

