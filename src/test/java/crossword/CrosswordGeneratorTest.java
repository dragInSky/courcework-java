package crossword;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CrosswordGeneratorTest {

    void stringCreator(StringBuilder str, char[][] crossword) {
        for (char[] subCrossword : crossword)
            for (char sym : subCrossword)
                str.append(sym);
    }

    @Test
    void clearEmptyCellsOnlyWordTest() {
        String[] words = {"математика"};

        CrosswordGenerator CG = new CrosswordGenerator(words);
        CG.crosswordFill();
        CG.clearEmptyCells();

        char[][] expectedCrossword = {
                {'м', 'а', 'т', 'е', 'м', 'а', 'т', 'и', 'к', 'а'}
        };
        StringBuilder expectedString = new StringBuilder();
        stringCreator(expectedString, expectedCrossword);

        StringBuilder actualString = new StringBuilder();
        stringCreator(actualString, CG.getCrossword());

        Assertions.assertEquals(expectedString.toString(), actualString.toString());
    }

    @Test
    void clearEmptyCellsIncompatibleTest() {
        String[] words = {"мороженное", "як"};

        CrosswordGenerator CG = new CrosswordGenerator(words);
        CG.crosswordFill();
        CG.clearEmptyCells();

        char[][] expectedCrossword = {
                {'м', 'о', 'р', 'о', 'ж', 'е', 'н', 'н', 'о', 'е'}
        };
        StringBuilder expectedString = new StringBuilder();
        stringCreator(expectedString, expectedCrossword);

        StringBuilder actualString = new StringBuilder();
        stringCreator(actualString, CG.getCrossword());

        Assertions.assertEquals(expectedString.toString(), actualString.toString());
    }

    @Test
    void clearEmptyCellsThreeWordsTest() {
        String[] words = {"кот", "машина", "кран"};

        CrosswordGenerator CG = new CrosswordGenerator(words);
        CG.crosswordFill();
        CG.clearEmptyCells();

        char[][] expectedCrossword = {
                {'_', 'к', 'о', 'т', '_', '_'},
                {'_', 'р', '_', '_', '_', '_'},
                {'м', 'а', 'ш', 'и', 'н', 'а'},
                {'_', 'н', '_', '_', '_', '_'}
        };
        StringBuilder expectedString = new StringBuilder();
        stringCreator(expectedString, expectedCrossword);

        StringBuilder actualString = new StringBuilder();
        stringCreator(actualString, CG.getCrossword());

        Assertions.assertEquals(expectedString.toString(), actualString.toString());
    }

    @Test
    void clearEmptyCellsCloseWordsTest() {
        String[] words = {"кот", "кран", "озеро"};

        CrosswordGenerator CG = new CrosswordGenerator(words);
        CG.crosswordFill();
        CG.clearEmptyCells();

        char[][] expectedCrossword = {
                {'к', 'о', 'т'},
                {'р', '_', '_'},
                {'а', '_', '_'},
                {'н', '_', '_'}
        };
        StringBuilder expectedString = new StringBuilder();
        stringCreator(expectedString, expectedCrossword);

        StringBuilder actualString = new StringBuilder();
        stringCreator(actualString, CG.getCrossword());

        Assertions.assertEquals(expectedString.toString(), actualString.toString());
    }

    @Test
    void appendIncompatibleTest() {
        String[] words = {"мороженное", "як"};

        CrosswordGenerator CG = new CrosswordGenerator(words);
        CG.crosswordFill();

        for (String word : words)
            if (!CG.getUsedWords().contains(word))
                CG.appendIncompatible(word);

        CG.clearEmptyCells();

        char[][] expectedCrossword = {
                {'м', 'о', 'р', 'о', 'ж', 'е', 'н', 'н', 'о', 'е'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'я', 'к', '_', '_', '_', '_', '_', '_', '_', '_'}
        };

        StringBuilder expectedString = new StringBuilder();
        stringCreator(expectedString, expectedCrossword);

        StringBuilder actualString = new StringBuilder();
        stringCreator(actualString, CG.getCrossword());

        Assertions.assertEquals(expectedString.toString(), actualString.toString());
    }

    @Test
    void appendIncompatibleTwoWordsTest() {
        String[] words = {"кот", "кран", "озеро", "я"};

        CrosswordGenerator CG = new CrosswordGenerator(words);
        CG.crosswordFill();

        for (String word : words)
            if (!CG.getUsedWords().contains(word))
                CG.appendIncompatible(word);

        CG.clearEmptyCells();

        char[][] expectedCrossword = {
                {'к', 'о', 'т', '_', '_'},
                {'р', '_', '_', '_', '_'},
                {'а', '_', '_', '_', '_'},
                {'н', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_'},
                {'о', 'з', 'е', 'р', 'о'},
                {'_', '_', '_', '_', '_'},
                {'я', '_', '_', '_', '_'}
        };
        StringBuilder expectedString = new StringBuilder();
        stringCreator(expectedString, expectedCrossword);

        StringBuilder actualString = new StringBuilder();
        stringCreator(actualString, CG.getCrossword());

        Assertions.assertEquals(expectedString.toString(), actualString.toString());
    }
}