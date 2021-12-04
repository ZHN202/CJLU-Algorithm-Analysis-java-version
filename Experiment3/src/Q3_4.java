import static java.lang.Math.pow;

public class Q3_4 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); //获取开始时间


        String s = "(1+2+3+4+5+6+7)/7";
        dataProcess(s).print();
        System.out.println(cal(dataProcess(s)));


        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间



    }

    static double cal(MyLinkedListQueue<String> s) {
        MylinkedListStack<Double> result = new MylinkedListStack<>();
        while (!s.empty()) {
            String c = s.dequeue();
            switch (c) {
                case "+" -> result.push(result.pop() + result.pop());


                case "-" -> result.push(-result.pop() + result.pop());


                case "/" -> result.push((1 / result.pop()) * result.pop());


                case "*" -> result.push(result.pop() * result.pop());


                default -> result.push(Double.valueOf(c));


            }

        }
        return result.pop();
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
            } else if (c[i] == '+' || c[i] == '-') {
                while (!myStack.empty()) {
                    if (myStack.getTop().equals("(")) break;
                    result.enqueue(myStack.pop());
                }
                myStack.push(String.valueOf(c[i]));
            } else if (c[i] == '*' || c[i] == '/') {
                if (myStack.empty() || myStack.getTop().equals("+") || myStack.getTop().equals("-") || myStack.getTop().equals("(")) {
                    myStack.push(String.valueOf(c[i]));
                } else if(!myStack.empty()) {
                    if (myStack.getTop().equals("*") || myStack.getTop().equals("/")) {
                        while (!myStack.empty() || !myStack.getTop().equals("(")) {

                            result.enqueue(myStack.pop());
                        }
                        myStack.push(String.valueOf(c[i]));
                    }
                }
            } else if (c[i] >= 48 && c[i] <= 57) {
                number.push((double) c[i] - 48);
                boolean b = (i + 1) == s.length() || c[i + 1] < 48 || c[i + 1] > 57;
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
                    num += Double.valueOf(result.getTail());
                    result.setTail(String.valueOf(num));
                    num = 0;
                    flag = false;
                }
            } else if (c[i] == '.') flag = true;
        }
        while (!myStack.empty()) {
            result.enqueue(myStack.pop());
        }
        return result;
    }
}







