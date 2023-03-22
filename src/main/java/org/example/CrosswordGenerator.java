package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class CrosswordGenerator {
    private int len, height, zero;
    private int startX = -1, startY = -1, endX = -1, endY = -1;
    private int xOffset, yOffset;
    private final ArrayList<String> usedWords = new ArrayList<>();
    private final ArrayList<Boolean> orientation = new ArrayList<>();
    private char[][] crossword;

    public CrosswordGenerator(String[] words) {
        zero = 0;
        for (String word : words)
            zero += word.length();

        len = zero * 2;
        height = zero * 2;

        crossword = new char[height][len];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < len; j++)
                crossword[i][j] = '_';
    }

    public ArrayList<String> getUsedWords() {
        return usedWords;
    }

    public void initCrossword(String[] words) {
        addWordWithOrientation(words[0], true);
        appendHorizontal(words[0], zero, zero);
    }

    private void appendHorizontal(String word, int posX, int posY) {
        for (int idx = 0; idx < word.length(); idx++)
            crossword[posY][posX + idx] = word.charAt(idx);
    }

    private void appendVertical(String word, int posX, int posY) {
        for (int idx = 0; idx < word.length(); idx++)
            crossword[posY + idx][posX] = word.charAt(idx);
    }

    private boolean checkHorizontalAccessibility(String word, int posX, int posY) {
        for (int idx = 1, repeat = 0; idx < word.length(); idx++) {
            if (crossword[posY + 1][posX + idx] != '_' || crossword[posY - 1][posX + idx] != '_') repeat++;
            else repeat = 0;

            if (repeat >= 2 || (crossword[posY][posX + idx] != '_' && crossword[posY][posX + idx] != word.charAt(idx)))
                return false;
        }
        return true;
    }

    private boolean checkVerticalAccessibility(String word, int posX, int posY) {
        for (int idx = 1, repeat = 0; idx < word.length(); idx++) {
            if (crossword[posY + idx][posX + 1] != '_' || crossword[posY + idx][posX - 1] != '_') repeat++;
            else repeat = 0;

            if (repeat >= 2 || (crossword[posY + idx][posX] != '_' && crossword[posY + idx][posX] != word.charAt(idx)))
                return false;
        }
        return true;
    }

    private void absWordPos(String word, boolean curOrient) {
        fillLimits();

        if (curOrient) {
            for (int i = startY, count = 0; i < endY; i++)
                for (int j = startX; j < endX; j++) {
                    if (crossword[i][j] == word.charAt(count)) count++;
                    else count = 0;
                    if (count == word.length()) {
                        xOffset = j - count + 1;
                        yOffset = i;
                        return;
                    }
                }
        } else for (int j = startX, count = 0; j < endX; j++)
            for (int i = startY; i < endY; i++) {
                if (crossword[i][j] == word.charAt(count)) count++;
                else count = 0;
                if (count == word.length()) {
                    xOffset = j;
                    yOffset = i - count + 1;
                    return;
                }
            }
    }

    public void crosswordFill(String[] words) {
        for (String word : words) {
            if (!getUsedWords().contains(word))
                continue;
            for (String secondWord : words)
                if (!usedWords.contains(secondWord) && !Objects.equals(word, secondWord))
                    for (int i = 0; i < word.length(); i++) {
                        int newWordOffset = secondWord.indexOf(word.charAt(i));
                        if (newWordOffset != -1) {
                            boolean curOrient = orientation.get(getUsedWords().indexOf(word));
                            absWordPos(word, curOrient);
                            if (curOrient) {
                                if (checkVerticalAccessibility(secondWord, xOffset + i, yOffset - newWordOffset)) {
                                    appendVertical(secondWord, xOffset + i, yOffset - newWordOffset);
                                    addWordWithOrientation(secondWord, false);

                                }
                            } else if (checkHorizontalAccessibility(secondWord, xOffset - newWordOffset, yOffset + i)) {
                                appendHorizontal(secondWord, xOffset - newWordOffset, yOffset + i);
                                addWordWithOrientation(secondWord, true);
                            }
                            break;
                        }
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
}