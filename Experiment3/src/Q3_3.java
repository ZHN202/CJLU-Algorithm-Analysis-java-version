import java.lang.String;
import java.util.Stack;

public class Q3_3 {
    public static void main(String[] args) {
        String s="/*char[22']' c=s.toCharArray()*/;\n";
        long startTime = System.currentTimeMillis(); //获取开始时间
        StackCheck(s);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("API程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        startTime = System.currentTimeMillis(); //获取开始时间
        myStackCheck(s);
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("myStack程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间


    }
    static void myStackCheck(String s){
        MylinkedListStack<Character> myStack = new MylinkedListStack<>();
        char[] c=s.toCharArray();
        boolean flag=false;
        for(int i=0;i<s.length();i++){
            if(c[i]=='['||c[i]=='{'||c[i]=='(')myStack.push(c[i]);
            else if(c[i]==']'){
                if(myStack.empty()) flag=true;
                else {
                    char ch = myStack.pop();
                    if (ch != '[') flag = true;
                }
            }
            else if(c[i]==')'){
                if(myStack.empty()) flag=true;
                else {
                    char ch = myStack.pop();
                    if (ch != '(') flag = true;
                }
            }
            else if(c[i]=='}'){
                if(myStack.empty()) flag=true;
                else {
                    char ch = myStack.pop();
                    if (ch != '{') flag = true;
                }
            }
            else if(c[i]=='/'&&c[i+1]=='*'){
                myStack.push(c[i]);
                myStack.push(c[i+1]);
                i++;
            }
            else if(c[i]=='*'&&c[i+1]=='/'){
                if(myStack.empty()) flag=true;
                else {
                    char ch = myStack.pop();
                    if (ch != '*') flag = true;
                    else {
                        ch = myStack.pop();
                        if (ch != '/') flag = true;
                    }
                }
            }
            if(flag)break;
        }

        if(flag||!myStack.empty()) System.out.println("not balance!!!");
        else System.out.println("balance!!!");
    }
    static void StackCheck(String s){
        Stack<Character> stack = new Stack<>();
        char[] c=s.toCharArray();
        boolean flag=false;
        for(int i=0;i<s.length();i++){
            if(c[i]=='['||c[i]=='{'||c[i]=='(')stack.push(c[i]);
            else if(c[i]==']'){
                if(stack.empty())flag=true;
                else {
                    char ch = stack.pop();
                    if (ch != '[') flag = true;
                }
            }
            else if(c[i]==')'){
                if(stack.empty())flag=true;
                else {
                    char ch = stack.pop();
                    if (ch != '(') flag = true;
                }
            }
            else if(c[i]=='}'){
                if(stack.empty())flag=true;
                else {
                    char ch = stack.pop();
                    if (ch != '{') flag = true;
                }
            }
            else if(c[i]=='/'&&c[i+1]=='*'){
                stack.push(c[i]);
                stack.push(c[i+1]);
                i++;
            }
            else if(c[i]=='*'&&c[i+1]=='/'){
                if(stack.empty()) flag=true;
                else {
                    char ch = stack.pop();
                    if (ch != '*') flag = true;
                    else {
                        ch = stack.pop();
                        if (ch != '/') flag = true;
                    }
                }
            }
            if(flag)break;
        }
        if(flag||!stack.empty()) System.out.println("not balance!!!");
        else System.out.println("balance!!!");
    }
}
