package sitesParse;

import java.util.Objects;

public class RepresentationParse {
    public static String definitionParse(String definition, String word) {
        if (Objects.equals(definition, ""))
            return null;

        definition = definition.replace(word, "*".repeat(word.length()))
                .replace(word.toLowerCase(), "*".repeat(word.length()))
                .replace("подробнее", "")
                .substring(definition.indexOf(" — ") + 3);

        if (definition.contains(") -"))
            definition = definition.substring(definition.indexOf(") -") + 3);

        if (definition.indexOf("(") < definition.indexOf("),")) {
            String translate = definition.substring(definition.indexOf("("), definition.indexOf("),"));
            if (translate.contains("-") && translate.contains("."))
                definition = definition.substring(definition.indexOf("),") + 2);
        }

        return definition.trim();
    }

    public static String joinParse(String definition, String word) {
        if (Objects.equals(definition, ""))
            return null;

        String preWord = word.substring(0, word.length() - 2);
        definition = definition.replace(preWord, "*".repeat(word.length() - 2))
                .replace(preWord.toLowerCase(), "*".repeat(word.length() - 2));

        return definition;
    }

    public static String sentencesParse(String definition, String word) {
        if (Objects.equals(definition, "") ||
                (!definition.contains(word) && !definition.contains(word.toLowerCase())))
            return null;

        definition = definition.replace(word, "*".repeat(word.length()))
                .replace(word.toLowerCase(), "*".repeat(word.length()));

        return definition.trim();
    }
}
