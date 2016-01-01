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
: nonblank_line line*
;

comment_line
: COMMENT ( CHAR | WS )* EOL
;

blank_line
: WS* EOL
;

nonblank_line
: WS* CHAR ( CHAR | WS )* EOL
;

line
: blank_line
| nonblank_line
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
