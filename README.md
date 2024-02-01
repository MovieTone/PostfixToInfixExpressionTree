# Usage
![image](https://github.com/MovieTone/PostfixToInfixExpressionTree/assets/15722914/b51e7634-528c-40dc-9542-ba00fff5e733)


# Test data
| Aspect Tested                        | Test          | Expected Outcome | Actual Outcome  |
|--------------------------------------|---------------|------------------|-----------------|
| + operator                           | 1 3 +         | (1+3)            | (1+3)           |
| - operator                           | 1 3 -         | (1-3)            | (1-3)           |
| * operator                           | 1 3 *         | (1*3)            | (1*3)           |
| / operator                           | 1 3 /         | (1/3)            | (1/3)           |
| Infix expression with parentheses    | 1 2 + 3 4 + * | ((1+2)*(3+4))    | ((1+2)*(3+4))   |
| Expression without spaces            | 1 4 3*+ 2+    | ((1+(4*3))+2)    | ((1+(4*3))+2)   |
| Invalid operator                     | 1 2 + 3 + 4 & | Invalid token &  | Invalid token & |
| Invalid token beginning with a digit | 1a 3 +        | Invalid token a  | Invalid token a |
