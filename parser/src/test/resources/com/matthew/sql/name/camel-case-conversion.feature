Feature: Can handle CamelCase

    Scenario Outline: Splitting CamelCase words

        Given a CamelCase converter
        When the word "<word>" is split
        Then the returned array is <words>

    Examples:
        | word                       | words                                                |
        | hello                      | hello                                                |
        | helloWorld                 | hello, world                                         |
        | HelloWorld                 | hello, world                                         |
        | aBcDeFgHiJkLmNoPqRsTuVwXyZ | a, bc, de, fg, hi, jk, lm, no, pq, rs, tu, vw, xy, z |
        | hello-world                | hello-world                                          |
        | hello_world                | hello_world                                          |
        | hello/world                | hello/world                                          |

    Scenario Outline: Joining CamelCase list

        Given a CamelCase converter
        When the words <words> are joined
        Then the returned string is "<word>"

    Examples:
        | words                                                | word                       |
        | hello                                                | Hello                      |
        | hello, world                                         | HelloWorld                 |
        | hello, world                                         | HelloWorld                 |
        | a, bc, de, fg, hi, jk, lm, no, pq, rs, tu, vw, xy, z | ABcDeFgHiJkLmNoPqRsTuVwXyZ |
        | hello-world                                          | Hello-world                |
        | hello_world                                          | Hello_world                |
        | hello/world                                          | Hello/world                |
