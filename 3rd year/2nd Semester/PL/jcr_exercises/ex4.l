// usar stack para ver qual a ordem de abertura das tags
// o que estiver no topo da stack foi o último

%%

\<[^>]+\> {
    // todas as tags
}

\<\/.+\> {
    // tags de fecho
}

\<.+\> {
    // tags de abertura

}
