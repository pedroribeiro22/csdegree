%{
#include "y.tab.h"
%}

%%

\"[^"]*\"  {
    return string;
}

\,|:|\{|\}|\[\]     return yytext[0];

(\+|\-)?[0-9]+(\.[0-9]+)? {
    return number;
}
