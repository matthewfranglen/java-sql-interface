package com.matthew.sql.statement;

public enum ArgumentType {
    TEXT() {
        public String getJavaType() {
            return "java.lang.String";
        }
    },
    WHOLE_NUMBER() {
        public String getJavaType() {
            return "java.lang.Long";
        }
    },
    FRACTIONAL_NUMBER() {
        public String getJavaType() {
            return "java.lang.Double";
        }
    },
    TIMESTAMP() {
        public String getJavaType() {
            return "java.util.Date";
        }
    };

    public abstract String getJavaType();
}
