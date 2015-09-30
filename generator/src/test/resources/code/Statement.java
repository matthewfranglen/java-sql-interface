package statements;

import com.matthew.sql.handler.AbstractStatement;

public class Statement extends AbstractStatement {

    private static final String statement = "SELECT * FROM table LIMIT 1;";

    public Statement(NamedParameterJdbcTemplate template) {
        super(template);
    }

    protected String getStatement() {
        return statement;
    }

}
