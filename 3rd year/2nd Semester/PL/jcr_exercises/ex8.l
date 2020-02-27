%{

#include "ex8.h"

%}

%%

[ \n\t]      ;

[0-9]+        return INT;

(\+|\-|\/|\*) return OP;

\(            return AP;

\)            return FP;

.             return ERR;
