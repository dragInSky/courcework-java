package crossword;

import java.util.ArrayList;
import java.util.List;
import sitesParse.HTMLParse;

// zdesb bil misha, on el tvoi sup i spal na tvoei krovati.
public class Main {
    private final static String dictionaryPrefix = "https://makeword.ru/dictionary/";
    private final static String joinPrefix = "https://makeword.ru/join/";
    private final static String relatedPrefix = "https://makeword.ru/related";
    private final static String sentencesPrefix = "https://makeword.ru/sentences";
    private final static String synonymPrefix = "https://makeword.ru/synonym";
    private final static String antonymPrefix = "https://makeword.ru/antonym";

    public static void main(String[] args) {
        String url = dictionaryPrefix + "asd";
        System.out.println(HTMLParse.isWordExist(url));
        HTMLParse.dictionary(url);

        System.out.println();

        String url2 = dictionaryPrefix + "добро";
        System.out.println(HTMLParse.isWordExist(url2));
        HTMLParse.dictionary(url2);

        System.out.println();

        String url3 = joinPrefix + "ветер";
        System.out.println(HTMLParse.isWordExist(url3));
        HTMLParse.join(url3);

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

