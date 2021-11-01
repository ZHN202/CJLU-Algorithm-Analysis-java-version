import static java.lang.Math.pow;

public class Q3_4 {
    public static void main(String[] args) {
        String s = "((9+852+9.3)*8+5)/2-10/2";
        System.out.println(dataProcess(s));
    }

    static double cal() {

        return 0;
    }

    static MyLinkedListQueue<String> dataProcess(String s) {
        MylinkedListStack<String> myStack = new MylinkedListStack<>();
        MylinkedListStack<Double> number = new MylinkedListStack<>();
        MyLinkedListQueue<String> result = new MyLinkedListQueue<>();
        double num = 0;
        int bit;
        boolean flag = false;
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (c[i] == '(') myStack.push(String.valueOf(c[i]));
            else if (c[i] == ')') {
                while (!myStack.getTop().equals("(")) {
                    result.enqueue(myStack.pop());
                }
                myStack.pop();
            } else if(c[i]=='+'||c[i]=='-'){
                while(!myStack.empty()){
                    if(myStack.getTop().equals("("))break;
                    result.enqueue(myStack.pop());
                }
                myStack.push(String.valueOf(c[i]));
            }else if(c[i]=='*'||c[i]=='/'){
                if(myStack.getTop().equals("+")||myStack.getTop().equals("-")|| myStack.getTop().equals("(")){
                    myStack.push(String.valueOf(c[i]));
                }
                else if(myStack.getTop().equals("*")||myStack.getTop().equals("/")){
                    while(!myStack.getTop().equals("(")|| !myStack.empty()){
                        result.enqueue(myStack.pop());
                    }
                    myStack.push(String.valueOf(c[i]));
                }

            }
            else if (c[i] >= 48 && c[i] <= 57) {
                number.push((double) c[i]);
                boolean b = c[i + 1] < 48 || c[i + 1] > 57;
                if (b && !flag) {
                    bit = 0;
                    while (!number.empty()) {
                        num += number.pop() * pow(10, bit);
                        bit++;
                    }
                    result.enqueue(String.valueOf(num));
                    num = 0;
                } else if (b && flag) {
                    bit = -number.getSize();
                    while (!number.empty()) {
                        num += number.pop() * pow(10, bit);
                        bit++;
                    }
                    result.enqueue(String.valueOf(num));
                    num = 0;
                    flag = false;
                }
            } else if (c[i] == '.') flag = true;
        }
        while (!myStack.empty()){
            result.enqueue(myStack.pop());
        }
        return result;
    }
}







