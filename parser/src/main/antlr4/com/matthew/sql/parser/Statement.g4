grammar Statement;

root
: entry
;

entry
: name_line ( comment_line | whitespace_line )* statement
;

statement
: statement_line ( statement_line | whitespace_line )*
;

name_line
: COMMENT WS+ NAME WS* SEPARATOR WS* name EOL
;

comment_line
: COMMENT CHAR* EOL
;

whitespace_line
: WS* EOL
;

statement_line
: WS* CHAR ( CHAR | WS )* EOL
;

name
: CHAR ( CHAR | WS )*
;

NAME
: 'name'
;

SEPARATOR
: ':'
;

COMMENT
: '--'
;

CHAR
: [^ \t\r\n]
;

WS
: [ \t]
;

EOL
: [\r\n]
;
