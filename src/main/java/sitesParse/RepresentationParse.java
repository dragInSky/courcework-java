package sitesParse;

import java.util.Objects;

public class RepresentationParse {
    private static String toReplace(String definition, String word) {
        String vowels = "аеёийоуыэюя";
        int len = word.length();
        if (len > 3 && vowels.indexOf(word.charAt(len - 1)) != -1) {
            String preWord, repeat;
            if (vowels.indexOf(word.charAt(len - 2)) != -1) {
                preWord = word.substring(0, len - 2);
                repeat = "_".repeat(len - 2);
            } else {
                preWord = word.substring(0, len - 1);
                repeat = "_".repeat(len - 1);
            }
            definition = definition.replace(preWord, repeat)
                    .replace(preWord.toLowerCase(), repeat)
                    .replace(preWord.toUpperCase(), repeat);
        } else
            definition = definition.replace(word, "_".repeat(len));

        return definition;
    }

    public static String definitionParse(String definition, String word) {
        if (Objects.equals(definition, ""))
            return null;

        definition = definition.replace("подробнее", "")
                .substring(definition.indexOf(" — ") + 3);

        definition = RepresentationParse.toReplace(definition, word);

        if (definition.contains(") -"))
            definition = definition.substring(definition.indexOf(") -") + 3);

        if (definition.indexOf("(") < definition.indexOf("),")) {
            String translate = definition.substring(definition.indexOf("("), definition.indexOf("),"));
            if (translate.contains("-") && translate.contains("."))
                definition = definition.substring(definition.indexOf("),") + 2);
        }

        return "Определение:\t" + definition.trim();
    }

    public static String joinParse(String definition, String word) {
        if (Objects.equals(definition, ""))
            return null;

        definition = RepresentationParse.toReplace(definition, word);

        return definition;
    }

    public static String sentencesParse(String definition, String word) {
        if (Objects.equals(definition, "") ||
                (!definition.contains(word) && !definition.contains(word.toLowerCase())))
            return null;

        definition = RepresentationParse.toReplace(definition, word);

        return definition.trim();
    }
}
