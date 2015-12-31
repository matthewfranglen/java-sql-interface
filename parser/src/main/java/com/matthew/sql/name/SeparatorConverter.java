package com.matthew.sql.name;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;

public class SeparatorConverter implements NameConverter {

    private final Pattern separator;
    private final Joiner joiner;

    public SeparatorConverter(String separator) {
        this.separator = Pattern.compile(separator, Pattern.LITERAL);
        joiner = Joiner.on(separator);
    }

    public List<String> split(String name) {
        return Arrays.asList(separator.split(name));
    }

    public String join(List<String> parts) {
        return joiner.join(parts);
    }

}
