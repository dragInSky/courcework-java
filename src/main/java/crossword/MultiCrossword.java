package crossword;

import java.util.ArrayList;
import java.util.List;

public class MultiCrossword {
    private static MultiCrossword instance;
    private static CrosswordGenerator CG;

    private MultiCrossword(List<String> words) {
        CG = createCrossword(words);

        while (!words.isEmpty())
            CG.mergeCrosswords(createCrossword(words));

//        CG.crosswordPrint();
    }

    public Tuple getSize() {
        return CG.getSize();
    }

    public List<Word> getWordsInformation() {
        return CG.getWordsInformation();
    }

    public String[][] getCrossword() {
        return CG.getCrossword();
    }

    public static MultiCrossword getInstance(List<String> words) {
        if (instance == null)
            instance = new MultiCrossword(words);
        return instance;
    }

    private static CrosswordGenerator createCrossword(List<String> words) {
        CrosswordGenerator secondaryCG = new CrosswordGenerator(words);

        List<String> leftWords = new ArrayList<>();
        for (String word : words)
            if (!secondaryCG.getUsedWords().contains(word))
                leftWords.add(word);

        words.clear();
        words.addAll(leftWords);

        return secondaryCG;
    }
}
