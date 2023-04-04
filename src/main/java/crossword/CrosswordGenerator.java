package crossword;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CrosswordGenerator {
    private int len, height;
    private int startX, startY, endX, endY;

    private final List<String> words;
    private final List<Word> wordsInformation = new ArrayList<>();
    private char[][] crossword;

    public CrosswordGenerator(List<String> words) {
        this.words = words;
        this.words.sort(Comparator.comparingInt(String::length).reversed());

        int wordsLen = 0;
        for (String word : words)
            wordsLen += word.length();

        len = wordsLen * 2;
        height = wordsLen * 2;

        crossword = new char[height][len];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < len; j++)
                crossword[i][j] = '_';

        appendHorizontal(words.get(0), wordsLen, wordsLen);
    }

    public Tuple getSize() {
        return new Tuple(len, height);
    }

    public List<Word> getWordsInformation() {
        return wordsInformation;
    }

    public List<String> getUsedWords() {
        List<String> usedWords = new ArrayList<>();
        for (Word word : wordsInformation)
            usedWords.add(word.word());
        return usedWords;
    }

    private List<Boolean> getOrientation() {
        List<Boolean> orientation = new ArrayList<>();
        for (Word word : wordsInformation)
            orientation.add(word.orientation());
        return orientation;
    }

    public String[][] getCrossword() {
        String[][] strCrossword = new String[height][len];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < len; j++)
                strCrossword[i][j] = String.valueOf(crossword[i][j]);

        return strCrossword;
    }

    private void appendHorizontal(String word, int posX, int posY) {
        for (int idx = 0; idx < word.length(); idx++)
            crossword[posY][posX + idx] = word.charAt(idx);

        addWordWithOrientation(word, true);
    }

    private void appendVertical(String word, int posX, int posY) {
        for (int idx = 0; idx < word.length(); idx++)
            crossword[posY + idx][posX] = word.charAt(idx);

        addWordWithOrientation(word, false);
    }

    private boolean isHorizontalAccessible(String word, int posX, int posY) {
        for (int idx = 1, wordLen = word.length(); idx < wordLen; idx++)
            if ((crossword[posY][posX + idx] != '_' && crossword[posY][posX + idx] != word.charAt(idx)) ||
                    (crossword[posY + 1][posX + idx] != '_' && crossword[posY + 1][posX + idx - 1] != '_') ||
                    (crossword[posY - 1][posX + idx] != '_' && crossword[posY - 1][posX + idx - 1] != '_') ||
                    (posX - 1 >= 0 && crossword[posY][posX - 1] != '_') ||
                    (posX + wordLen <= len && crossword[posY][posX + wordLen] != '_'))
                return false;
        return true;
    }

    private boolean isVerticalAccessible(String word, int posX, int posY) {
        for (int idx = 1, wordLen = word.length(); idx < wordLen; idx++)
            if ((crossword[posY + idx][posX] != '_' && crossword[posY + idx][posX] != word.charAt(idx)) ||
                    (crossword[posY + idx][posX + 1] != '_' && crossword[posY + idx - 1][posX + 1] != '_') ||
                    (crossword[posY + idx][posX - 1] != '_' && crossword[posY + idx - 1][posX - 1] != '_') ||
                    (posY - 1 >= 0 && crossword[posY - 1][posX] != '_') ||
                    (posY + wordLen <= len && crossword[posY + wordLen][posX] != '_'))
                return false;
        return true;
    }

    private Tuple absWordPos(String word, boolean prevWordOrient) {
        int count = 0;
        if (prevWordOrient) {
            for (int i = 0; i < height; i++)
                for (int j = 0; j < len; j++) {
                    if (crossword[i][j] == word.charAt(count)) count++;
                    else count = 0;
                    if (count == word.length())
                        return new Tuple(j - count + 1, i);
                }
        } else {
            for (int j = 0; j < len; j++)
                for (int i = 0; i < height; i++) {
                    if (crossword[i][j] == word.charAt(count)) count++;
                    else count = 0;
                    if (count == word.length())
                        return new Tuple(j, i - count + 1);
                }
        }

        return new Tuple(0, 0);
    }

    public void crosswordFill() {
        for (String word : words) {
            if (!getUsedWords().contains(word))
                continue;

            for (String secondWord : words)
                if (!getUsedWords().contains(secondWord) && !Objects.equals(word, secondWord))
                    for (int i = 0; i < word.length(); i++) {
                        char sym = word.charAt(i);
                        int secondWordSameCharPos = secondWord.indexOf(sym);

                        if (secondWordSameCharPos == -1)
                            continue;

                        boolean prevWordOrient = getOrientation().get(getUsedWords().indexOf(word));

                        Tuple tuple = absWordPos(word, prevWordOrient);

                        if (prevWordOrient) {
                            int posX = tuple.x() + i;
                            int posY = tuple.y() - secondWordSameCharPos;
                            if (isVerticalAccessible(secondWord, posX, posY))
                                appendVertical(secondWord, posX, posY);
                        } else {
                            int posX = tuple.x() - secondWordSameCharPos;
                            int posY = tuple.y() + i;
                            if (isHorizontalAccessible(secondWord, posX, posY))
                                appendHorizontal(secondWord, posX, posY);
                        }

                        break;
                    }
        }
    }

    private void addWordWithOrientation(String word, boolean curOrient) {
        wordsInformation.add(new Word(word, curOrient, absWordPos(word, curOrient)));
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

        for (int i = 0; i < wordsInformation.size(); i++)
            if (words.contains(wordsInformation.get(i).word())) {
                Tuple tuple = wordsInformation.get(i).tuple();
                wordsInformation.set(i, new Word(
                        wordsInformation.get(i).word(), wordsInformation.get(i).orientation(),
                        new Tuple(tuple.x() - startX, tuple.y() - startY)
                ));
            }

        len = endX - startX;
        height = endY - startY;

        char[][] newCrossword = new char[height][len];

        for (int i = 0; i < height; i++)
            System.arraycopy(crossword[i + startY], startX, newCrossword[i], 0, len);

        crossword = newCrossword;
    }

    public void mergeCrosswords(CrosswordGenerator CG) {
        if (this == CG)
            return;

        List<String> secondUsedWords = CG.getUsedWords();
        List<Boolean> secondOrientation = CG.getOrientation();

        for (int i = 0, size = secondUsedWords.size(); i < size; i++)
            wordsInformation.add(
                    new Word(secondUsedWords.get(i), secondOrientation.get(i),
                            new Tuple(CG.absWordPos(secondUsedWords.get(i), secondOrientation.get(i)).x(),
                                    CG.absWordPos(secondUsedWords.get(i), secondOrientation.get(i)).y() + height + 1))
            );

        int newLen = Math.max(len, CG.len);
        int newHeight = height + CG.height + 1;

        char[][] newCrossword = new char[newHeight][newLen];
        char[][] secondCrossword = CG.crossword;

        for (int i = 0; i < newHeight; i++)
            for (int j = 0; j < newLen; j++)
                if (i < height)
                    newCrossword[i][j] = (j < len) ? crossword[i][j] : '_';
                else if (i == height)
                    newCrossword[i][j] = '_';
                else
                    newCrossword[i][j] = (j < CG.len) ? secondCrossword[i - height - 1][j] : '_';

        len = newLen;
        height = newHeight;

        crossword = newCrossword;
    }
}
