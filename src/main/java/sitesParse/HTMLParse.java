package sitesParse;

import org.jsoup.Jsoup;
import java.io.IOException;

public class HTMLParse {
    private final static String wordGenerator = "https://calculator888.ru/random-generator/sluchaynoye-slovo";
    private final static String prefix = "https://makeword.ru/";
    private final static String dictionaryPrefix = prefix + "dictionary/";
    private final static String joinPrefix = prefix + "join/";
    private final static String relatedPrefix = prefix + "related/";
    private final static String sentencesPrefix = prefix + "sentences/";
    private final static String synonymPrefix = prefix + "synonym/";
    private final static String antonymPrefix = prefix + "antonym/";

    public static String wordGeneration() {
        try {
            var page = Jsoup.connect(wordGenerator).get();

            var word = page.body().selectFirst(".blok_otvet");

            return word != null ? word.text() : null;
        } catch (IOException e) {
            return null;
        }
    }

    public static String dictionary(String word) {
        try {
            var page = Jsoup.connect(dictionaryPrefix + word).get();

            String title = page.title();
            if (title.equals("Поиск значения слов в словарях"))
                return null;

            var definitions = page.body().select("p");
            StringBuilder res = new StringBuilder("Определения:\n");

            for (var definition : definitions)
                if (!definition.id().isEmpty())
                    res.append(definition).append('\n');

            return res.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static String join(String word) {
        try {
            var page = Jsoup.connect(joinPrefix + word).get();

            String title = page.title();
            if (title.equals("Cочетаемость слов"))
                return null;

            var definitions = page.body().select(".cut");
            StringBuilder res = new StringBuilder("Сочетаемость:\t");
            for (int i = 0; i < 10 && i < definitions.size(); i++)
                res.append(definitions.get(i).text()).append("; ");

            return res.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static String related(String word) {
        try {
            var page = Jsoup.connect(relatedPrefix + word).get();

            String title = page.title();
            if (title.equals("Ассоциации к словам, с чем ассоциируются слова"))
                return null;

            var definitions1 = page.body().select(".bestmatch");
            var definitions2 = page.body().select(".verybestmatch");
            StringBuilder res = new StringBuilder("Ассоциации:\t");

            for (int i = 0; i < 5 && i < definitions1.size(); i++)
                res.append(definitions1.get(i).text()).append("; ");
            for (int i = 0; i < 5 && i < definitions2.size(); i++)
                res.append(definitions2.get(i).text()).append("; ");

            return res.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static String sentences(String word) {
        try {
            var page = Jsoup.connect(sentencesPrefix + word).get();

            var definitions = page.body().select(".sentences");
            StringBuilder res = new StringBuilder("Предложения:\n");
            for (int i = 0; i < 6 && i < definitions.size(); i++)
                if (i % 2 == 0)
                    res.append(definitions.get(i).text()).append('\n');

            return res.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static String synonym(String word) {
        try {
            var page = Jsoup.connect(synonymPrefix + word).get();

            String title = page.title();
            if (title.equals("Синонимы, словарь синонимов, заменить слово синонимом"))
                return null;

            var definitions = page.body().select(".cut");
            StringBuilder res = new StringBuilder("Синонимы:\t");
            for (int i = 0; i < 10 && i < definitions.size(); i++)
                res.append(definitions.get(i).text()).append("; ");

            return res.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static String antonym(String word) {
        try {
            var page = Jsoup.connect(antonymPrefix + word).get();

            String title = page.title();
            if (title.equals("Антонимы, словарь антонимов, подобрать"))
                return null;

            var definitions = page.body().select(".cut");

            return "Антоним:\t" + definitions.text();
        } catch (IOException e) {
            return null;
        }
    }
}
