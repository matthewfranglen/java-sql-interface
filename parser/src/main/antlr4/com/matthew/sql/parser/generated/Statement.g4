grammar Statement;

root
: entry
;

entry
: metadata? statement
;

metadata
: ( comment_line | blank_line )+
;

statement
: statement_line ( statement_line | blank_line )*
;

comment_line
: COMMENT ( CHAR | WS )* EOL
;

blank_line
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
