package com.matthew.sql.name;

import java.util.List;

public interface NameConverter {

    public List<String> split(String name);

    public String join(List<String> parts);

}
