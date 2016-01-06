package statements;

public final class Statement {

    private static final String statement = "SELECT * FROM table LIMIT 1;";

    private final org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate template;

    public Statement(org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    protected String getStatement() {
        return statement;
    }









    public static final class Takes {

        private final java.lang.String tOne;
        private final java.lang.Long iTwo;
        private final java.lang.Double fThree;
        private final java.util.Date tsFour;

        public Takes(java.lang.String tOne, java.lang.Long iTwo, java.lang.Double fThree, java.util.Date tsFour) {
            this.tOne = tOne;
            this.iTwo = iTwo;
            this.fThree = fThree;
            this.tsFour = tsFour;
        }

        public java.lang.String getTOne() {
            return tOne;
        }

        public java.lang.Long getITwo() {
            return iTwo;
        }

        public java.lang.Double getFThree() {
            return fThree;
        }

        public java.util.Date getTsFour() {
            return tsFour;
        }

    }

    public static final class Returns {

        private final java.lang.String tOne;
        private final java.lang.Long iTwo;
        private final java.lang.Double fThree;
        private final java.util.Date tsFour;

        public Returns(java.lang.String tOne, java.lang.Long iTwo, java.lang.Double fThree, java.util.Date tsFour) {
            this.tOne = tOne;
            this.iTwo = iTwo;
            this.fThree = fThree;
            this.tsFour = tsFour;
        }

        public java.lang.String getTOne() {
            return tOne;
        }

        public java.lang.Long getITwo() {
            return iTwo;
        }

        public java.lang.Double getFThree() {
            return fThree;
        }

        public java.util.Date getTsFour() {
            return tsFour;
        }

    }

}
