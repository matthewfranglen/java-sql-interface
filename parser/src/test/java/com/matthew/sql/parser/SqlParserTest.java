package com.matthew.sql.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class SqlParserTest {

    @Test
    public void testParser() throws IOException {
        String source = Resources.toString(Resources.getResource("statement.sql"), Charsets.UTF_8);
        SqlParser parser = new SqlParser();
        SqlStatement statement = parser.parse(source);

        assertEquals(statement.getName(), "sample statement");
    }
}
