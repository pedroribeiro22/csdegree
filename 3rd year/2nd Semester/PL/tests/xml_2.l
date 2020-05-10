%{
#include <stdio.h>
#include <glib.h>
#include <string.h>
int elements = 0;
char **elements_names;
char *curr_element;
GHashTable *elements_tags;
%}

open_tag    \<
close_tag   \>
ws          [\t\n ];
palavra     [^\"\<\>];

%x OPEN_TAG INSIDE_TAG_ATTRIBUTES

%%

BEGIN 0;

<*>{open_tag}{ws}*{palavra} {
    int i = 1;
    while(yytext[i] == ' ') i++;
    while(yytext[i] && (yytext[i] != '/0'))
        curr_element[i] = yytext[i++];
}

<OPEN_TAG>{close_tag} {
    BEGIN 0;
}

<INSIDE_TAG_ATTRIBUTES>{close_tag} {
    BEGIN 0;
}

<INSIDE_TAG_ATTRIBUTES>{palavra}{ws}*={ws}*{palavra} {
    char *attribute = (char *) malloc(sizeof(char) * 1024);
    int i = 0;
    while((yytext[i] != ' ') && (yytext[i] != '='))
        attribute[i] = yytext[i++];
    attribute[i] = '\0';
    int j = i;
    i = 0;
    char *value = (char *) malloc(sizeof(char) * 1024);
    while((yytext[j] != ' ') && (yytext[j] != '/'))
        value[i++] = yytext[j++];
    value[i] = '\0';
    char *to_save = (char *) malloc(sizeof(char) * 2048);
    sprintf(to_save, "%s=%s", attribute, value);
    g_hash_table_insert(elements_tags, curr_element, to_save);
}

%%

void printTableEntry(gpointer *key, gpointer *value) {
    print("ELEMENT: %s\n", (char *) key);
    print("VALUE: %s\n", (char *) value);
}

int main(int argc, char **argv) {

    curr_elements = (char *) malloc(sizeof(char) * 1024);
    elements_tags = g_hash_table(g_str_hash, g_str_equal);

    yylex();


}
