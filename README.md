[![Build Status](https://travis-ci.org/matthewfranglen/java-sql-interface.svg)](https://travis-ci.org/matthewfranglen/java-sql-interface.svg)
[![Coverage Status](https://coveralls.io/repos/matthewfranglen/java-sql-interface/badge.svg?branch=develop&service=github)](https://coveralls.io/github/matthewfranglen/java-sql-interface?branch=develop)

Java SQL Interface
==================

This will compile SQL statements into Java classes that can then be used freely
in your application. This allows the SQL to remain separate from the Java code
and remain legible.

This was inspired by [yesql](https://github.com/krisajenkins/yesql) and the
rationale for it is much the same. Why try to make a DSL or wrapper for writing
SQL statements? Why not make handling raw SQL statements easier?

This does mean that no database specific translation is done. If you change
databases you may have to rewrite your statements.

Synopsis
--------

This converts your SQL statements into Java code using a Maven plugin. The
maven plugin is defined as follows:

```xml
<properties>
    <java-sql-interface.version>0.1.0</java-sql-interface.version>
</properties>

<dependencies>
    <dependency>
        <groupId>com.matthew.java-sql-interface</groupId>
        <artifactId>handler</artifactId>
        <version>${java-sql-interface.version}</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>com.matthew.java-sql-interface</groupId>
            <artifactId>compiler</artifactId>
            <version>${java-sql-interface.version}</version>
            <configuration>
                <statements>
                    <directory>src/main/resources</directory>
                    <includes>
                        <include>**/*.sql</include>
                    </includes>
                </statements>
            </configuration>
        </plugin>
    </plugins>
</build>
```

This example looks for files ending in `sql` inside the resources folder of
your project. Each one is converted into a separate class that can be used.

You can build the classes as part of the default lifecycle. This means that
when you run `mvn clean install` the Java classes are correctly created. To do
this you must bind to the compile:generate-sources part of the maven lifecycle:

```xml
<properties>
    <java-sql-interface.version>0.1.0</java-sql-interface.version>
</properties>

<dependencies>
    <dependency>
        <groupId>com.matthew.java-sql-interface</groupId>
        <artifactId>handler</artifactId>
        <version>${java-sql-interface.version}</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>com.matthew.java-sql-interface</groupId>
            <artifactId>compiler</artifactId>
            <version>${java-sql-interface.version}</version>
            <executions>
                <execution>
                    <phase>generate-sources</phase>
                    <goals>
                        <goal>compile</goal>
                    </goals>
                    <configuration>
                        <statements>
                            <directory>src/main/resources</directory>
                            <includes>
                                <include>**/*.sql</include>
                            </includes>
                        </statements>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

Once this has been done you can then fetch the statement class using the
StatementHandler:

```java
SqlStatementHandler statementHandler = new SqlStatementHandler(dataSource);
YourStatementClass statement = statementHandler.makeYourStatementClass();
```


Description
-----------

It is recommended that you define the statements inside a module within your
application. Doing this makes code completion on the generated code far more
reliable. You can see an example of doing this in the example project.


Examples
--------

There is an example project in the examples folder.
