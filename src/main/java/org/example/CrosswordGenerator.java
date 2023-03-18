package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class CrosswordGenerator {
    private static int len, height, zero;
    private final ArrayList<String> usedWords = new ArrayList<>();
    private final ArrayList<Boolean> orientation = new ArrayList<>();
    private char[][] crossword;

    public CrosswordGenerator(String[] words) {
        zero = 0;
        for (String word : words)
            zero += word.length();

        len = zero * 2;
        height = zero * 2;

        usedWords.add(words[0]);
        orientation.add(true);
        crossword = new char[height][len];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < len; j++)
                crossword[i][j] = '_';
    }

    public void appendHorizontal(String word) {
        appendHorizontal(word, zero, zero);
    }
    private void appendHorizontal(String word, int posX, int posY) {
        for (int idx = 0; idx < word.length(); idx++)
            crossword[posY][posX + idx] = word.charAt(idx);
    }
    private void appendVertical(String word, int posX, int posY) {
        for (int idx = 0; idx < word.length(); idx++)
            crossword[posY + idx][posX] = word.charAt(idx);
    }

    public void crosswordFill(String word, String[] words) {
        crosswordFill(word, zero, zero, words);
    }
    private void crosswordFill(String word, int xOffset, int yOffset, String[] words) {
        for (String secondWord : words)
            if (!Objects.equals(word, secondWord))
                for (int i = 0; i < word.length(); i++) {
                    int newWordOffset = secondWord.indexOf(word.charAt(i));
                    if (!usedWords.contains(secondWord) && newWordOffset != -1) {
                        boolean curOrient = orientation.get(orientation.size() - 1);
                        if (curOrient)
                            appendVertical(secondWord, xOffset + i, yOffset - newWordOffset);
                        else
                            appendHorizontal(secondWord, xOffset + i, yOffset - newWordOffset);
                        addWordWithOrientation(secondWord, !curOrient);
                        crosswordFill(secondWord, xOffset + i, yOffset - newWordOffset, words);
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

    public void clearEmptyCells() {
        int lenOffset = -1, heightOffset = -1;
        int tmpLen = 0, tmpHeight = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < len; j++)
                if (crossword[j][i] != '_') {
                    tmpLen++;
                    if (lenOffset == -1)
                        lenOffset = i;
                    break;
                }

            for (int j = 0; j < len; j++)
                if (crossword[i][j] != '_') {
                    tmpHeight++;
                    if (heightOffset == -1)
                        heightOffset = i;
                    break;
                }
        }

        len = tmpLen;
        height = tmpHeight;
        char[][] newCrossword = new char[height][len];

        for (int i = 0; i < height; i++)
            System.arraycopy(crossword[i + heightOffset], lenOffset, newCrossword[i], 0, len);

        crossword = newCrossword;
    }
}