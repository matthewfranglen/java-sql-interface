package statements;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.matthew.sql.handler.AbstractStatement;

public final class StatementWithComment extends AbstractStatement {

    private static final String statement = "SELECT * FROM table LIMIT 1;";

    public StatementWithComment(NamedParameterJdbcTemplate template) {
        super(template);
    }

    protected String getStatement() {
        return statement;
    }



}
