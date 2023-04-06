package sitesParse;

import java.util.Objects;

public class RepresentationParse {
    private static String toReplace(String definition, String word) {
        for (int i = 0, len = word.length(); i < 3 && len - i > len / 2; i++) {
            String preWord = word.substring(0, len - i);
            String repeat = "*".repeat(len - i);
            definition = definition.replace(preWord, repeat)
                    .replace(preWord.toLowerCase(), repeat);
        }

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
