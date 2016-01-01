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
: COMMENT WS* TAKES_LABEL WS* argument_list EOL
;

returns_line
: COMMENT WS* RETURNS_LABEL WS* argument_list EOL
;

argument_list
: OPEN_PARENTHESIS WS* ( argument ( WS* SEPARATOR WS* argument )* WS* )? CLOSE_PARENTHESIS
;

argument
: argument_type WS+ argument_name
;

argument_type
: TEXT_TYPE
| WHOLE_NUMBER_TYPE
| FRACTIONAL_NUMBER_TYPE
| TIMESTAMP_TYPE
| invalid_type
;

argument_name
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

invalid_type
: CHAR+
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

TEXT_TYPE
: 'Text'
;

WHOLE_NUMBER_TYPE
: 'Integer'
| 'Long'
;

FRACTIONAL_NUMBER_TYPE
: 'Float'
| 'Double'
;

TIMESTAMP_TYPE
: 'Date'
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
