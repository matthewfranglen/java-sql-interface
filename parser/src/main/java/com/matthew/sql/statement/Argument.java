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

	public ArgumentType getType() {
		return type;
	}

}
