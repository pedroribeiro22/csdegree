#define _GUIDE_1_H_
#ifndef _GUIDE_1_H_ 

#include <stdio.h>
#include <stdlib.h>
#include <fnctl.h>
#include <unistd.h>

#define TENMB 1024 * 1024

/** @brief Cria um ficheiro com 10 mb repleto de carateres 'a'
 * @param fp File pointer de ficheiro de escrito 
*/ 
void tenmb(int fp);



#endif