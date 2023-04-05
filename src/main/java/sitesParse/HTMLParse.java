package sitesParse;

import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLParse {
    private final static List<String> titles = new ArrayList<>(List.of(
            "Поиск значения слов в словарях",
            "Cочетаемость слов"
    ));
    public static boolean isWordExist(String url) {
        try {
            var page = Jsoup.connect(url).get();
            String title = page.title();
//            System.out.println(title);
            return !titles.contains(title);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String dictionary(String url) {
        try {
            var page = Jsoup.connect(url).get();
            var definitions = page.body().select("p");
            for (var definition : definitions)
                if (!definition.id().isEmpty())
                    System.out.println(definition.text());
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String join(String url) {
        try {
            var page = Jsoup.connect(url).get();
            var definitions = page.body().select(".cut");
            for (int i = 0; i < 10 && i < definitions.size(); i++)
                System.out.println(definitions.get(i).text());
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
