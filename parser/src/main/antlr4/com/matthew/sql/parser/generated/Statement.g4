grammar Statement;

root
: entry
;

entry
: metadata? blank_line* statement
;

metadata
: ( takes_line | returns_line | comment_line | blank_line )+
;

statement
: nonblank_line line*
;

comment_line
: COMMENT line
;

takes_line
: COMMENT WS* TAKES_LABEL argument_list EOL
;

returns_line
: COMMENT WS* RETURNS_LABEL argument_list EOL
;

argument_list
: OPEN_PARENTHESIS WS* ( argument ( WS* SEPARATOR WS* argument )* WS* )? CLOSE_PARENTHESIS
;

argument
: CHAR+
;

line
: blank_line
| nonblank_line
;

blank_line
: WS* EOL
;

nonblank_line
: WS* CHAR ( CHAR | WS )* EOL
;

SEPARATOR
: ','
;

OPEN_PARENTHESIS
: '('
;

CLOSE_PARENTHESIS
: ')'
;

COMMENT
: '--'
;

TAKES_LABEL
: 'takes:'
;

RETURNS_LABEL
: 'returns:'
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
