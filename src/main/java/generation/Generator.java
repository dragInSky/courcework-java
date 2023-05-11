package generation;

import crossword.MultiCrossword;
import crossword.Tuple;
import crossword.Word;
import sitesParse.HTMLParse;
import sitesParse.RepresentationParse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    private static Generator instance;
    private final static List<String> words = new ArrayList<>();
    private final static Map<String, String> representations = new HashMap<>();
    private List<Word> wordsInformation;
    private String[][] crossword;
    private Tuple size;

    private Generator() {
        generateWords();
        launch();
    }

    public static Generator getInstance() {
        if (instance == null)
            instance = new Generator();
        return instance;
    }

    public String[][] getCrossword() {
        return crossword;
    }

    public Tuple getSize() {
        return size;
    }

    public List<Word> getWordsInformation() {
        return wordsInformation;
    }

    private void generateWords() {
        int count = 0;
        int quantity = ThreadLocalRandom.current().nextInt(6, 11);
        while (count != quantity) {
            String word = HTMLParse.wordGeneration().toLowerCase();
            if (word.equals(""))
                continue;

            String wordRepr = switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0 -> {
                    String[] tmpWord = new String[]{word};
                    String def = HTMLParse.dictionary(tmpWord);
                    word = tmpWord[0];
                    yield RepresentationParse.definitionParse(def, word);
                }
                case 1 -> RepresentationParse.joinParse(HTMLParse.join(word), word);
                case 2 -> HTMLParse.related(word);
                case 3 -> RepresentationParse.sentencesParse(HTMLParse.sentences(word), word);
                case 4 -> HTMLParse.synonym(word);
                case 5 -> HTMLParse.antonym(word);
                default -> null;
            };

            if (wordRepr != null) {
                System.out.println("Word is:\t" + word + '\n' + wordRepr + '\n');
                representations.put(word, wordRepr);
                words.add(word);
                count++;
            }
        }
    }

    private void launch() {
        MultiCrossword MC = MultiCrossword.getInstance(words);
        crossword = MC.getCrossword();
        size = MC.getSize();
        wordsInformation = MC.getWordsInformation();
        wordsInformation.replaceAll(word -> new Word(
                word.word(),
                word.orientation(),
                word.tuple(),
                representations.get(word.word())));
    }
}
