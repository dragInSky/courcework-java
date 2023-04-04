package crossword;

import java.util.ArrayList;
import java.util.List;

// zdesb bil misha, on el tvoi sup i spal na tvoei krovati.
public class Main {
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

//        List<String> words = new ArrayList<>(List.of(
//                "кот", "кран", "озеро"
//        ));

        MultiCrossword MC = MultiCrossword.getInstance(words);
        String[][] crossword = MC.getCrossword();
        Tuple size = MC.getSize();
        List<Word> wordsInformation = MC.getWordsInformation();
    }
}

