package com.matthew.sql.name;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;

public class CamelCaseConverter implements NameConverter {

    private static final Pattern separator = Pattern.compile("\\B(?=\\p{javaUpperCase})");
    private static final Joiner joiner = Joiner.on("");

    public List<String> split(String name) {
        String[] values = separator.split(name);
        List<String> result = new ArrayList<>(values.length);

        for (String current : values) {
            result.add(current.toLowerCase());
        }

        return result;
    }

    public String join(List<String> parts) {
        List<String> capitalizedParts = new ArrayList<>(parts.size());

        for (String current : parts) {
            capitalizedParts.add(capitalizeFirstLetter(current));
        }

        return joiner.join(capitalizedParts);
    }

    private String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}
