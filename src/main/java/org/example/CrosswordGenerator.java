package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class CrosswordGenerator {
    private int len, height;
    private int startX, startY, endX, endY;
    private int wordPosX, wordPosY;

    private final ArrayList<String> usedWords = new ArrayList<>();
    private final ArrayList<Boolean> orientation = new ArrayList<>();
    private final String[] words;
    private char[][] crossword;

    public CrosswordGenerator(String[] words) {
        this.words = words;

        int wordsLen = 0;
        for (String word : words)
            wordsLen += word.length();

        len = wordsLen * 2;
        height = wordsLen * 2;

        crossword = new char[height][len];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < len; j++)
                crossword[i][j] = '_';

        appendHorizontal(words[0], wordsLen, wordsLen);
    }

    public ArrayList<String> getUsedWords() {
        return usedWords;
    }

    private void appendHorizontal(String word, int posX, int posY) {
        addWordWithOrientation(word, true);

        for (int idx = 0; idx < word.length(); idx++)
            crossword[posY][posX + idx] = word.charAt(idx);
    }

    private void appendVertical(String word, int posX, int posY) {
        addWordWithOrientation(word, false);

        for (int idx = 0; idx < word.length(); idx++)
            crossword[posY + idx][posX] = word.charAt(idx);
    }

    private boolean isHorizontalAccessible(String word, int posX, int posY) {
        for (int idx = 1; idx < word.length(); idx++)
            if ((crossword[posY][posX + idx] != '_' && crossword[posY][posX + idx] != word.charAt(idx)) ||
                    (crossword[posY + 1][posX + idx] != '_' && crossword[posY + 1][posX + idx - 1] != '_') ||
                    (crossword[posY - 1][posX + idx] != '_' && crossword[posY - 1][posX + idx - 1] != '_'))
                return false;
        return true;
    }

    private boolean isVerticalAccessible(String word, int posX, int posY) {
        for (int idx = 1; idx < word.length(); idx++)
            if ((crossword[posY + idx][posX] != '_' && crossword[posY + idx][posX] != word.charAt(idx)) ||
                    (crossword[posY + idx][posX + 1] != '_' && crossword[posY + idx - 1][posX + 1] != '_') ||
                    (crossword[posY + idx][posX - 1] != '_' && crossword[posY + idx - 1][posX - 1] != '_'))
                return false;
        return true;
    }

    private void absWordPos(String word, boolean prevWordOrient) {
        fillLimits();

        if (prevWordOrient) {
            for (int i = startY, count = 0; i < endY; i++)
                for (int j = startX; j < endX; j++) {
                    if (crossword[i][j] == word.charAt(count)) count++;
                    else count = 0;
                    if (count == word.length()) {
                        wordPosX = j - count + 1;
                        wordPosY = i;
                        return;
                    }
                }
        } else {
            for (int j = startX, count = 0; j < endX; j++)
                for (int i = startY; i < endY; i++) {
                    if (crossword[i][j] == word.charAt(count)) count++;
                    else count = 0;
                    if (count == word.length()) {
                        wordPosX = j;
                        wordPosY = i - count + 1;
                        return;
                    }
                }
        }
    }

    public void crosswordFill() {
        for (String word : words) {
            if (!getUsedWords().contains(word))
                continue;

            for (String secondWord : words)
                if (!usedWords.contains(secondWord) && !Objects.equals(word, secondWord))
                    for (int i = 0; i < word.length(); i++) {
                        char sym = word.charAt(i);
                        int secondWordSameCharPos = secondWord.indexOf(sym);

                        if (secondWordSameCharPos == -1)
                            continue;

                        boolean prevWordOrient = orientation.get(getUsedWords().indexOf(word));

                        absWordPos(word, prevWordOrient);

                        if (prevWordOrient) {
                            int posX = wordPosX + i;
                            int posY = wordPosY - secondWordSameCharPos;
                            if (isVerticalAccessible(secondWord, posX, posY))
                                appendVertical(secondWord, posX, posY);
                        } else {
                            int posX = wordPosX - secondWordSameCharPos;
                            int posY = wordPosY + i;
                            if (isHorizontalAccessible(secondWord, posX, posY))
                                appendHorizontal(secondWord, posX, posY);
                        }

                        break;
                    }
        }
    }

    private void addWordWithOrientation(String word, boolean curOrient) {
        usedWords.add(word);
        orientation.add(curOrient);
    }

    public void crosswordPrint() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < len; j++)
                System.out.print(crossword[i][j] + " ");
            System.out.println();
        }
    }

    private void fillLimits() {
        boolean flagX = true, flagY = true;
        for (int i = 0; i < height; i++)
            for (int j = 0; j < len; j++) {
                if (crossword[i][j] != '_') {
                    if (flagY) {
                        startY = i;
                        flagY = false;
                    }
                    endY = i + 1;
                }

                if (crossword[j][i] != '_') {
                    if (flagX) {
                        startX = i;
                        flagX = false;
                    }
                    endX = i + 1;
                }
            }
    }

    public void clearEmptyCells() {
        fillLimits();

        len = endX - startX;
        height = endY - startY;

        char[][] newCrossword = new char[height][len];

        for (int i = 0; i < height; i++)
            System.arraycopy(crossword[i + startY], startX, newCrossword[i], 0, len);

        crossword = newCrossword;
    }

    public void appendIncompatible(String word) {
        fillLimits();

        appendHorizontal(word, startX, endY + 1);
    }
}