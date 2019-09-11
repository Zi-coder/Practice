import java.util.Stack;
public class Main{
    public static int Prec(char ch){
        switch(ch){
            case('+'): return 1;
            case('-'): return 1;
            case('*'): return 2;
            case('/'): return 2;
            case('^'): return 3;
        }
        return -1;
    }
    public static String infixToPostfix(String x){
        Stack<Character> stack = new Stack <Character>();
        String y = "";
        x = "("+x+")";
        char ch ;
        for(int i = 0; i< x.length() ;i++){
            ch = x.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                y += ch;
            }
            else if(ch == '('){
                stack.push(ch);
            }
            else if(ch == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    y += stack.pop();
                 //   System.out.println("loop 1");
                }
                stack.pop();
            }
            else{
                if( Prec(ch) > Prec(stack.peek()) ){
                    stack.push(ch);
                }
                else{
                    while(!stack.isEmpty() && Prec(ch) <= Prec(stack.peek())){
                        y += stack.pop();
                     //   System.out.println("loop 2");
                    }
                    stack.push(ch);
                }
            }
        }
        return y;
    }
    static int eval(String x){
        Stack<Integer> stack = new Stack<Integer>();
        char ch;
        int num1,num2;
        for(int i = 0; i < x.length() ;i++){
            ch = x.charAt(i);
            if(Character.isDigit(ch)){
                stack.push(ch - '0');
            }
            else{
                num1 = stack.pop();
                num2 = stack.pop();
                switch(ch){
                    case('+'): {
                        stack.push(num2 + num1);
                        break;
                    }
                    case('-'): {
                        stack.push(num2 - num1);
                        break;
                    }
                    case('*'): {
                        stack.push(num2 * num1);
                        break;
                    }
                    case('/'): {
                        stack.push(num2 / num1);
                        break;
                    }
                    case('^'): {
                        stack.push( (int) Math.pow((double)num2,(double)num1));
                        break;
                    }

             }
            }
        }


        return stack.pop();
    }
    public static void main(String[] args) {
       String x = "2+5*(4/2)";
       String y = infixToPostfix(x);
       System.out.println(y);

       System.out.println(eval(y));
    }
}