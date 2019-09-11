import java.util.Scanner;

//stack class
class Stack{
    //char array 
    char []stack;
    //variable top to manage index
    int top = -1;
    //intialise stack with a fixed size
    public Stack(int size ){
        stack = new char [ size ];
    }
    public void push(char element){
        stack[++top] = element;
    }
    public char pop(){
        return stack[top--];
    }
    public char peek(){
        return stack[top];
    }
    public boolean isEmpty(){
       return  top == -1 ? true:false; 
    }
    public void printStack(){
        System.out.print("\nStack is :");
        for(char c: stack){
            System.out.print(c + " ");
        }
    }
}
class IntStack{
    //char array 
    int []stack;
    //variable top to manage index
    int top = -1;
    //intialise stack with a fixed size
    public IntStack(int size ){
        stack = new int [ size ];
    }
    public void push(int element){
        stack[++top] = element;
    }
    public int pop(){
        return stack[top--];
    }
    public int peek(){
        return stack[top];
    }
    public boolean isEmpty(){
       return  top == -1 ? true:false; 
    }
    public void printStack(){
        System.out.print("\nStack is :");
        for(int c: stack){
            System.out.print(c + " ");
        }
    }
}

public class Main{
    static int precedence(char a){
        switch(a){
            
            case '^': return 3;
            case '/': return 2;
            case '*': return 2;
            case '+': return 1;
            case '-': return 1;
        }
        return -1;
    }
    static String infixToPostfix(String x){
        String y = "";
        x  = "("+x+")";
        System.out.println(x);
        Stack myStack = new Stack(x.length());
        for(int index = 0;index <  x.length(); index++){
            char element = x.charAt(index);
            if(Character.isLetterOrDigit(element)){
                y += element;
            }
            else if(element == '('){
                myStack.push(element);
            }
            else if(element == ')'){
                while(!myStack.isEmpty() && myStack.peek() != '('){
                    y += myStack.pop();
                }
                myStack.pop();
            }
            else{
                if( !myStack.isEmpty() &&  precedence(element) > precedence(myStack.peek()) ){
                    myStack.push(element);
                }
                else{
                    
                while (!myStack.isEmpty() && precedence(element) <= precedence(myStack.peek())){ 
                    if(myStack.peek() == '(') 
                        return "Invalid Expression"; 
                    y += myStack.pop(); 
             } 
                myStack.push(element); 
                }
            }
            
        }


        return y;
    }
    static int postfixEvaluation(String exp){
        IntStack intstack = new IntStack(exp.length());
        char element;
        for(int i = 0;i <  exp.length() ;i++){
            element = exp.charAt(i);
            if(Character.isDigit(element)){
                intstack.push(element - '0');
            }
            else{
                int num1 = intstack.pop();
                int num2 = intstack.pop();
                switch(element){
                    case('+'): {
                        intstack.push(num2+num1);
                        break;
                    }
                    case('-'):{
                        intstack.push(num2-num1);
                        break;
                    }
                    case('*'):{
                        intstack.push(num2*num1);
                        break;
                    }
                    case('/'):{
                        intstack.push(num2 / num1);
                        break;
                    }
                    case('^'):{
                        intstack.push( (int) Math.pow( (double)num2,(double)num1 ));
                        break;
                    }
                }
            }
        }
       return intstack.pop();
    }
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
   // System.out.println(infixToPostfix(scanner.nextLine()));
   System.out.println(postfixEvaluation(scanner.nextLine())) ;
   scanner.close();
    
        
    }
}