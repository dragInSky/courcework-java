package crossword;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import sitesParse.HTMLParse;

// zdesb bil misha, on el tvoi sup i spal na tvoei krovati.
public class Main {
    public static void main(String[] args) {
        int count = 0;
        while (count != 25) {
            String word = HTMLParse.wordGeneration();

            String wordRepr = switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0 -> HTMLParse.dictionary(word);
                case 1 -> HTMLParse.join(word);
                case 2 -> HTMLParse.related(word);
                case 3 -> HTMLParse.sentences(word);
                case 4 -> HTMLParse.synonym(word);
                case 5 -> HTMLParse.antonym(word);
                default -> null;
            };

            if (wordRepr != null) {
                System.out.println("\nWord is:\t" + word + '\n' + wordRepr);
                count++;
            }
        }

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

//        List<String> words = new ArrayList<>(List.of(
//                "кот", "кран", "озеро"
//        ));

        MultiCrossword MC = MultiCrossword.getInstance(words);
        String[][] crossword = MC.getCrossword();
        Tuple size = MC.getSize();
        List<Word> wordsInformation = MC.getWordsInformation();
    }
}

