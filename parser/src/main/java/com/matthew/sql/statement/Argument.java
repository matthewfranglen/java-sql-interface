package com.matthew.sql.statement;

public class Argument {

	private final ArgumentType type;
	private final String name;

    public Argument(ArgumentType type, String name) {
		this.type = type;
		this.name = name;
    }

	public String getName() {
		return name;
	}

    public String getGetterName() {
        return "get" + getCapitalizedName();
    }

	public ArgumentType getType() {
		return type;
	}

    public String getJavaType() {
        return type.getJavaType();
    }

    public String toString() {
        return getJavaType() + " " + getName();
    }

    private String getCapitalizedName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}
