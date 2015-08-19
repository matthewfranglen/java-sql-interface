grammar Statement;

root
: entry
;

entry
: ( comment_line | whitespace_line )* statement
;

statement
: statement_line ( statement_line | whitespace_line )*
;

comment_line
: COMMENT ( CHAR | WS )* EOL
;

whitespace_line
: WS* EOL
;

statement_line
: WS* CHAR ( CHAR | WS )* EOL
;

SEPARATOR
: ':'
;

COMMENT
: '--'
;

CHAR
: ~(' ' | '\t' | '\r' | '\n')
;

WS
: ' '
| '\t'
;

EOL
: '\r'
| '\n'
| '\r\n'
| '\n\r'
;
