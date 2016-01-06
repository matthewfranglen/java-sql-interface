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











}
