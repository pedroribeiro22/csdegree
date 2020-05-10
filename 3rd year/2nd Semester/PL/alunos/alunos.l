%{
#include "y.tab.h"
%}

ws          [ \t\n\r];

%x  conteudo

%%

ws          ;

\<\?xml{ws}+version=\"1\.0\"{ws}*\?\>   return DECL;

\<alunos\>                              return AALUNOS;

\<\/alunos\>                            return FALUNOS;

\<aluno\>                               return AALUNO;

\<\/aluno\>                             return FALUNO;

\<id\>                                  { BEGIN conteudo; return AID; }

\<\/id\>                                return FID;

\<nome\>                                { BEGIN conteudo; return ANOME; }

\<\/nome\>                              return FNOME;

\<nota\>                                return ANOTA;

\<\/nota\>                              return FNOTA;

\<uc\>                                  { BEGIN conteudo; return AUC; }

\<\/uc\>                                return FUC;

\<valor\>                               { BEGIN conteudo; return AVALOR; }

\<\/valor\>                             return FVALOR;

<conteudo>[^<]*     {

    BEGIN INITIAL;
    yyval.svalue = strdup(yytext);
    return string;

}

.                                       return ERRO;

