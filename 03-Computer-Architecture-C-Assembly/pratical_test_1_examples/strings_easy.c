#include <stdint.h>
#include <stddef.h>

/* ==========================================================================
 * STRINGS EASY (E)
 * ========================================================================== */

/* --- Category: INSPECTION & SEARCH ---------------------------------------- */

// E-1 - EASY - STRINGS
unsigned int string_length(const char *str){
int i = 0;

while(str[i] != '\0'){
i++;
}
return i;
}
/* str:     Pointer to a null-terminated string
   returns: The number of characters in the string, excluding the null terminator
   Notes:   Implement this without using the standard library strlen().
            Not allowed to use functions from <string.h>.
   Example 1: string_length("hello") returns 5
   Example 2: string_length("") returns 0
*/

// E-2 - EASY - STRINGS
int count_char(const char *str, char c){
int i = 0;
int counter = 0;
while(str[i] !=  '\0'){
if(str[i] == c){
counter++;
}
i++;
}
return counter;
}
/* str:     Pointer to a null-terminated string
   c:       The character to search for
   returns: The total number of times character 'c' appears in the string
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: count_char("hello", 'l') returns 2
   Example 2: count_char("banana", 'z') returns 0
*/

// E-3 - EASY - STRINGS
int count_digits(const char *str){
int i = 0;
int counter = 0;
while(str[i] !=  '\0'){
if(str[i] >= '0' && str[i] <= '9'){
counter++;
}
i++;
}
return counter;
}
/* str:     Pointer to a null-terminated string
   returns: The total number of numeric characters ('0'-'9') found
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: count_digits("Code 2024") returns 4
   Example 2: count_digits("Hello") returns 0
*/

// E-4 - EASY - STRINGS
int count_vowels(const char *str) {
    int counter = 0;
    int i = 0;
    while (str[i] != '\0') {
        switch (str[i]) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                counter++;
                break;
            default:
                break;
        }
        i++;
    }
    return counter;
}
}
/* str:     Pointer to a null-terminated string
   returns: The total number of vowels (a, e, i, o, u) regardless of case
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: count_vowels("Apple") returns 2
   Example 2: count_vowels("Sky") returns 0
*/

// E-5 - EASY - STRINGS
int count_uppercase(const char *str){
int i = 0;
int counter = 0;

while(str[i] != '\0'){
if(str[i] >= 'A' && str[i] <= 'Z'){
counter++;
}
i++;
}
return counter;
}
/* str:     Pointer to a null-terminated string
   returns: The total number of uppercase characters ('A'-'Z') found
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: count_uppercase("C Programming") returns 2
   Example 2: count_uppercase("lowercase") returns 0
*/

// E-6 - EASY - STRINGS
int find_first_occurrence(const char *str, char c){
int i = 0;

while(str[i] != '\0'){
if(str[i] == c){
return i;
}
i++;
}
return -1;
}
/* str:     Pointer to a null-terminated string
   c:       The character to search for
   returns: The index (0-based) of the first 'c', or -1 if not found
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: find_first_occurrence("banana", 'a') returns 1
   Example 2: find_first_occurrence("apple", 'z') returns -1
*/

// E-7 - EASY - STRINGS
int find_last_occurrence(const char *str, char c){
int i = 0;
int holder = -1;
while(str[i] != '\0'){
if(str[i] == c){
holder = i;
}
i++;
}
return holder;
}
/* str:     Pointer to a null-terminated string
   c:       The character to search for
   returns: The index (0-based) of the last 'c', or -1 if not found
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: find_last_occurrence("banana", 'a') returns 5
   Example 2: find_last_occurrence("banana", 'b') returns 0
*/

// E-8 - EASY - STRINGS
int contains_substring(const char *str, const char *sub) {
    if (sub[0] == '\0') {
        return 1;
    }
    int i = 0;
        while (str[i] != '\0') {
        int j = 0;
        while (str[i + j] == sub[j] && sub[j] != '\0') {
            j++;
        }
        if (sub[j] == '\0') {
            return 1;
        }
        i++;
    }
    return 0;
}
/* str:     Pointer to a null-terminated string
   sub:     Pointer to the substring to look for
   returns: 1 if sub is found within str, 0 otherwise
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: contains_substring("hello world", "world") returns 1
   Example 2: contains_substring("apple", "banana") returns 0
*/

// E-9 - EASY - STRINGS
int strings_are_equal(const char *s1, const char *s2){
int i = 0;

while(s1[i] == s2[i]){
if(s1[i] == '\0'){
return 1;
}
i++
}
return 0;
}
/* s1, s2:  Pointers to two null-terminated strings
   returns: 1 if the strings are identical, 0 otherwise
   Notes:   Implement this without using the standard library strcmp().
            Not allowed to use functions from <string.h>.
   Example 1: strings_are_equal("abc", "abc") returns 1
   Example 2: strings_are_equal("abc", "abd") returns 0
*/

// E-10 - EASY - STRINGS
int is_palindrome(const char *str){
int i = 0;
int j = 0;

while(str[j]!= '\0'){
j++;
}

while(str[i] == str[(j - 1) -i]){
i++;
if(str[i] == '\0'){
return 1;
}
}
return 0;
}
/* str:     Pointer to a null-terminated string
   returns: 1 if the string reads the same forwards and backwards, 0 otherwise
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: is_palindrome("racecar") returns 1
   Example 2: is_palindrome("hello") returns 0
*/

/* --- Category: MODIFICATION & TRANSFORM ----------------------------------- */

// E-11 - EASY - STRINGS
void to_uppercase(char *str){
int i = 0;
while(i != '\0'){
if(str[i] >= 'a' && str[i] <= 'z'){
str[i] -= 32;
}
i++
}
}
/* str:     Pointer to a null-terminated string
   returns: nothing
   Notes:   Modifies string in place, converting lowercase ASCII to uppercase.
            ASCII: 'A'=65, 'Z'=90, 'a'=97, 'z'=122.
            Not allowed to use functions from <string.h>.
   Example 1: if str is "abc-123", to_uppercase(str) results in "ABC-123"
   Example 2: if str is "Hello!", to_uppercase(str) results in "HELLO!"
*/

// E-12 - EASY - STRINGS
void to_lowercase(char *str){

int i = 0;
while(i != '\0'){
if(str[i] >= 'a' && str[i] <= 'z'){
str[i] += 32;
}
i++
}
}
/* str:     Pointer to a null-terminated string
   returns: nothing
   Notes:   Modifies string in place, converting uppercase ASCII to lowercase.
            Not allowed to use functions from <string.h>.
   Example 1: if str is "HELLO", to_lowercase(str) results in "hello"
   Example 2: if str is "123-ABC", to_lowercase(str) results in "123-abc"
*/

// E-13 - EASY - STRINGS
void toggle_case(char *str){
int i = 0;
while(i != '\0'){
if(str[i] >= 'a' && str[i] <= 'z'){
str[i] -= 32;
}
else if (str[i] >= 'A' && str[i] <= 'Z'){
str[i] += 32;
}
i++
}
}
/* str:     Pointer to a null-terminated string
   returns: nothing
   Notes:   Converts uppercase to lowercase and vice-versa in place.
            Not allowed to use functions from <string.h>.
   Example 1: if str is "Hi There", toggle_case(str) results in "hI tHERE"
   Example 2: if str is "123!", toggle_case(str) results in "123!"
*/

// E-14 - EASY - STRINGS
void censor_vowels(char *str){
int i = 0

while(str[i] != '\0'){

switch (str[i]) {
case 'a':
case 'e':
case 'i':
case 'o':
case 'u':

str[i] = '*';
break;

default:

break;
}
i++
}
}
/* str:     Pointer to a null-terminated string
   returns: nothing
   Notes:   Replaces every vowel (a, e, i, o, u) with an asterisk '*'.
            Not allowed to use functions from <string.h>.
   Example 1: if str is "hello", censor_vowels(str) results in "h*ll*"
   Example 2: if str is "fly", censor_vowels(str) results in "fly"
*/

// E-15 - EASY - STRINGS
void trim_trailing_whitespace(char *str){
int i = 0;

while(str[i] != '\0'){
i++;
}
while (i > 0){
i--;
if(str[i] == ' '){
str[i] == '\0';
}
else{
break;
}
}
}
}
/* str:     Pointer to a null-terminated string
   returns: nothing
   Notes:   Removes spaces (' ') at the very end of the string.
            Not allowed to use functions from <string.h>.
   Example 1: if str is "hello  ", trim_trailing_whitespace(str) results in "hello"
   Example 2: if str is "test", trim_trailing_whitespace(str) results in "test"
*/

// E-16 - EASY - STRINGS
void reverse_string_inplace(char *str){
int i = 0;
int j = 0;
char saver = ' ';
while(str[i] != '\0'){
i++;
}
i--;

while(i > j){
saver = str[j];
str[j] = str[i]
str[i] = saver;
j++;
i--;
}

}
/* str:     Pointer to a null-terminated string
   returns: nothing
   Notes:   Reverses the characters in the string in-place.
            Not allowed to use functions from <string.h>.
   Example 1: if str is "live", reverse_string_inplace(str) results in "evil"
   Example 2: if str is "radar", reverse_string_inplace(str) results in "radar"
*/

// E-17 - EASY - STRINGS
void remove_all_spaces(char *str);
/* str:     Pointer to a null-terminated string
   returns: nothing
   Notes:   Removes every space ' ' character, shifting characters left.
            Not allowed to use functions from <string.h>.
   Example 1: if str is "a b c", remove_all_spaces(str) results in "abc"
   Example 2: if str is "  ", remove_all_spaces(str) results in ""
*/

/* --- Category: BUFFER MANIPULATION ---------------------------------------- */

// E-18 - EASY - STRINGS
void append_char(char *str, char c){
int i = 0;

while(str[i] != '\0'){
i++;
}

str[i] = c;
str[i+1] = '\0';
}
/* str:     Pointer to a null-terminated string (has enough allocated space)
   c:       The character to append
   returns: nothing
   Notes:   Finds the end of the string, places 'c', and adds a null terminator.
            Not allowed to use functions from <string.h>.
   Example 1: if str is "hi", append_char(str, '!') results in "hi!"
   Example 2: if str is "", append_char(str, 'A') results in "A"
*/

// E-19 - EASY - STRINGS
void remove_last_char(char *str){
int i = 0;

while(str[i] != '\0'){
i++;
}
if(i > 0){
str[i-1] = '\0';

}
}
/* str:     Pointer to a null-terminated string
   returns: nothing
   Notes:   If not empty, remove the last char by placing a null terminator.
            Not allowed to use functions from <string.h>.
   Example 1: if str is "test", remove_last_char(str) results in "tes"
   Example 2: if str is "A", remove_last_char(str) results in ""
*/

// E-20 - EASY - STRINGS
void string_copy(char *dest, const char *src){
int i = 0;

while(src[i] != '\0'){
dest[i] = src[i];
i++;
}
dest[i] = '\0';

}
/* dest:    Pointer to the destination buffer (assumed large enough)
   src:     Pointer to the source null-terminated string
   returns: nothing
   Notes:   Copies src into dest, including the null terminator.
            Not allowed to use functions from <string.h>.
   Example 1: string_copy(buffer, "hi") results in buffer becoming "hi"
   Example 2: string_copy(buffer, "") results in buffer becoming ""
*/

// E-21 - EASY - STRINGS
void string_concat(char *dest, const char *src){
int i = 0;
int j = 0;

while(dest[i] != '\0'){
i++;
}
while(src[j] != '\0'){
dest[i+j] = src[j];
j++;
}
dest[i+j] = '\0';
}
/* dest:    Pointer to the destination buffer (already contains a string)
   src:     Pointer to the string to append to the end of dest
   returns: nothing
   Notes:   Appends src to the end of dest. Assume dest has enough space.
            Not allowed to use functions from <string.h>.
   Example 1: if dest is "hello ", concat(dest, "world") results in "hello world"
   Example 2: if dest is "A", concat(dest, "B") results in "AB"
*/

// E-22 - EASY - STRINGS
void string_n_copy(char *dest, const char *src, unsigned int n){
int i = 0;

while(n > i && src[i] != '\0'){
dest[i] = src[i];
i++;
}
while(i < n){
dest[i] = '\0'
i++;
}
}
/* dest:    Pointer to the destination buffer
   src:     Pointer to the source string
   n:       Maximum number of characters to copy
   returns: nothing
   Notes:   Copies up to n characters. Must ensure dest is null-terminated.
            Not allowed to use functions from <string.h>.
   Example 1: string_n_copy(buf, "hello world", 5) results in "hello"
   Example 2: string_n_copy(buf, "hi", 10) results in "hi"
*/

/* --- Category: NUMERIC CONVERSION ----------------------------------------- */

// E-23 - EASY - STRINGS
int digit_to_int(char c){
if (c >= '0' && c <= '9') {
        return c - '0'; // A manha é subtrair o carácter '0'
    }
    return -1;
}
}
/* c:       A character '0'-'9'
   returns: The integer value 0-9, or -1 if c is not a digit
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: digit_to_int('5') returns 5
   Example 2: digit_to_int('a') returns -1
*/

// E-24 - EASY - STRINGS
char int_to_digit(int val);
/* val:     An integer 0-9
   returns: The character '0'-'9', or '\0' if out of range
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: int_to_digit(3) returns '3'
   Example 2: int_to_digit(10) returns '\0'
*/

// E-25 - EASY - STRINGS
int string_to_int(const char *str);
/* str:     Pointer to a string of digits (e.g., "123")
   returns: The represented integer
   Notes:   Assume positive numbers only.
            Not allowed to use functions from <string.h>.
   Example 1: string_to_int("42") returns 42
   Example 2: string_to_int("0") returns 0
*/

/* --- Category: CHARACTER CHECKS ------------------------------------------- */

// E-26 - EASY - STRINGS
int is_alpha(char c){
if(c >= 'a' && c <= 'z' || c >= 'A' && c<= 'Z'){
return 1;
}
return 0;
}
/* c:       A character
   returns: 1 if 'a'-'z' or 'A'-'Z', 0 otherwise
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: is_alpha('g') returns 1
   Example 2: is_alpha('!') returns 0
*/

// E-27 - EASY - STRINGS
int is_digit(char c);
/* c:       A character
   returns: 1 if '0'-'9', 0 otherwise
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: is_digit('9') returns 1
   Example 2: is_digit('a') returns 0
*/

// E-28 - EASY - STRINGS
int is_lower(char c);
/* c:       A character
   returns: 1 if 'a'-'z', 0 otherwise
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: is_lower('a') returns 1
   Example 2: is_lower('A') returns 0
*/

// E-29 - EASY - STRINGS
int count_words_by_spaces(const char *str);
/* str:     Pointer to a null-terminated string
   returns: The number of words (sequences of non-space characters)
   Notes:   Assume single spaces between words.
            Not allowed to use functions from <string.h>.
   Example 1: count_words_by_spaces("hello world") returns 2
   Example 2: count_words_by_spaces("test") returns 1
*/

// E-30 - EASY - STRINGS
char get_last_char(const char *str);
/* str:     Pointer to a null-terminated string
   returns: The last character before the null terminator, or '\0' if empty
   Notes:   Not allowed to use functions from <string.h>.
   Example 1: get_last_char("apple") returns 'e'
   Example 2: get_last_char("") returns '\0'
*/

// E-31 - EASY - STRINGS
void replace_char(char *str, char old_c, char new_c);
/* str:     Pointer to a null-terminated string
   old_c:   The character to be replaced
   new_c:   The character to put in its place
   returns: nothing
   Notes:   Replaces EVERY instance of old_c with new_c.
            Not allwaed to use funcitons from <string.h>
   Example 1: if str is "papa", replace_char(str, 'p', 'm') results in "mama"
*/
