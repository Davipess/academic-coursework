#include <stdint.h>
#include <stddef.h>

/* ==========================================================================
 * STRINGS VERY EASY (VE)
 * ========================================================================== */

/* --- Category: BASIC INSPECTION ------------------------------------------- */

// VE-1 — VERY EASY — STRINGS
char get_first_char(const char *str){
return str[0];
}

/* str:     Pointer to a null-terminated string (at least 1 char)
   returns: The very first character of the string
   Notes:   Not allwaed to use funcitons from <string.h>
   Example 1: get_first_char("apple") returns 'a'
   Example 2: get_first_char(" Hello") returns ' '
*/

// VE-2 — VERY EASY — STRINGS
int is_empty(const char *str){
if(str[0] == '\0') {
   return 1;
}
return 0;
}

/* str:     Pointer to a null-terminated string
   returns: 1 if the string is empty (""), 0 otherwise
   Notes:   Not allwaed to use funcitons from <string.h>
   Example 1: is_empty("") returns 1
   Example 2: is_empty("a") returns 0
*/

/* --- Category: SIMPLE MODIFICATION ---------------------------------------- */

// VE-3 — VERY EASY — STRINGS
void set_first_char(char *str, char c){
str[0] = c;
}
/* str:     Pointer to a null-terminated string (at least 1 char)
   c:       The character to place at the start
   returns: nothing
   Notes:   Not allwaed to use funcitons from <string.h>
   Example 1: if str is "bat", set_first_char(str, 'c') results in "cat"
*/

// VE-4 — VERY EASY — STRINGS
void clear_string(char *str){
str[0] = '\0';
}

/* str:     Pointer to a null-terminated string
   returns: nothing
   Notes:   Makes the string empty ("")
            Not allwaed to use funcitons from <string.h>
   Example 1: if str is "hello", clear_string(str) results in str bcoming ""
*/

int main() {
    // O programa só precisa disto para compilar e arrancar
    return 0;
}