package statements;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
