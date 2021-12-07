import java.util.Arrays;
import java.util.Random;

public class Q6_3 {
    public static void main(String[] args) {
        int N[] = {100, 1000, 5000, 10000, 50000, 100000};
        Random r = new Random();

        int[][] t = new int[8][6];
        for(int[] l:t)
            Arrays.fill(l,0);

        for (int k = 0; k < 10; k++) {
            int p = 0;
            for (int i : N) {

                Integer[] a = new Integer[i];
                System.out.println("N=" + i);
                for (int j = 0; j < i; j++) {
                    while (true) {
                        int num = r.nextInt(i);
                        if (num > 0) {
                            a[j] = num;
                            break;
                        }
                    }
                }
                //选择排序
                Integer[] b = new Integer[a.length];
                System.arraycopy(a, 0, b, 0, a.length);
                long startTime = System.currentTimeMillis(); //获取开始时间
                selectSort(b);
                long endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("选择排序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
                t[0][p] += endTime - startTime;

                System.arraycopy(a, 0, b, 0, a.length);
                //冒泡排序
                startTime = System.currentTimeMillis(); //获取开始时间
                bubbleSort(b);
                endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("冒泡排序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
                t[1][p] += endTime - startTime;

                System.arraycopy(a, 0, b, 0, a.length);
                //插入排序
                startTime = System.currentTimeMillis(); //获取开始时间
                insertionSort(b);
                endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("插入排序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
                t[2][p] += endTime - startTime;

                System.arraycopy(a, 0, b, 0, a.length);
                //希尔排序
                startTime = System.currentTimeMillis(); //获取开始时间
                shellSort(b);
                endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("希尔排序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
                t[3][p] += endTime - startTime;

                System.arraycopy(a, 0, b, 0, a.length);
                //归并排序
                startTime = System.currentTimeMillis(); //获取开始时间
                mergeSort(b);
                endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("归并排序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
                t[4][p] += endTime - startTime;

                System.arraycopy(a, 0, b, 0, a.length);
                //快速排序
                startTime = System.currentTimeMillis(); //获取开始时间
                quickSort(b);
                endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("快速排序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
                t[5][p] += endTime - startTime;

                System.arraycopy(a, 0, b, 0, a.length);
                //桶排序
                startTime = System.currentTimeMillis(); //获取开始时间
                bucketSort(b, i);
                endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("桶排序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
                t[6][p] += endTime - startTime;
                System.arraycopy(a, 0, b, 0, a.length);
                //基数排序
                startTime = System.currentTimeMillis(); //获取开始时间
                radixSort(b);
                endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("基数排序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
                t[7][p] += endTime - startTime;
                p++;
            }
        }
        for (int i = 0; i < 6; i++) {

            System.out.println("N=" + N[i]);
            System.out.println("选择排序运行时间："+(t[0][i])/10.0+"ms");
            System.out.println("冒泡排序运行时间："+(t[1][i])/10.0+"ms");
            System.out.println("插入排序运行时间："+(t[2][i])/10.0+"ms");
            System.out.println("希尔排序运行时间："+(t[3][i])/10.0+"ms");
            System.out.println("归并排序运行时间："+(t[4][i])/10.0+"ms");
            System.out.println("快速排序运行时间："+(t[5][i])/10.0+"ms");
            System.out.println("桶排序运行时间："+(t[6][i])/10.0+"ms");
            System.out.println("基数排序运行时间："+(t[7][i])/10.0+"ms");
        }
    }

    //插入排序
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a) {
        int j;
        for (int p = 1; p < a.length; p++) {
            AnyType tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    //希尔排序
    public static <AnyType extends Comparable<? super AnyType>>
    void shellSort(AnyType[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2)
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
                    a[j] = a[j - gap];
                a[j] = tmp;
            }
    }

    //归并排序
    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a, AnyType[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (a[leftPos].compareTo(a[rightPos]) < 0)
                tmpArray[tmpPos++] = a[leftPos++];
            else
                tmpArray[tmpPos++] = a[rightPos++];
        while (leftPos <= leftEnd)
            tmpArray[tmpPos++] = a[leftPos++];
        while (rightPos <= rightEnd)
            tmpArray[tmpPos++] = a[rightPos++];

        for (int i = 0; i < numElements; i++, rightEnd--)
            a[rightEnd] = tmpArray[rightEnd];
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    //快速排序
    public static <AnyType extends Comparable<? super AnyType>>
    void quickSort(AnyType[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);

        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void swapReferences(AnyType[] a, int i, int j) {
        AnyType tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void quickSort(AnyType[] a, int left, int right) {
        if (left + 10 <= right) {
            AnyType pivot = median3(a, left, right);

            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {

                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }
            swapReferences(a, i, right - 1);

            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        } else
            insertionSort(a);
    }

    //选择排序
    public static <AnyType extends Comparable<? super AnyType>>
    void selectSort(AnyType[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = 0; j < a.length; j++)
                if (a[i].compareTo(a[j]) > 0)
                    min = j;
            swapReferences(a, i, min);
        }
    }

    //桶排序
    public static void bucketSort(Integer[] a, int N) {
        int BUCKET = N + 1;

        Integer[] b = new Integer[BUCKET];
        Arrays.fill(b, 0);
        for (Integer i : a)
            b[i] = 1;
        int j = 0;
        for (int i = 0; i < b.length; i++)
            if (b[i] == 1) a[++j] = i;
    }

    //基数排序
    public static void radixSort(Integer[] a) {
        int BUCKET = 10;
        int max = a[0];
        int bit = 1;
        for (Integer i : a)
            max = max < i ? i : max;
        while ((max = max / 10) > 0) bit++;
        int radix = 1;
        for (int b = 0; b < bit; b++) {
            int[] counts = new int[10];
            int len = a.length;
            int[] buffer = new int[len];

            for (Integer i : a) counts[(i / radix) % 10]++;
            for (int i = 1; i < counts.length; i++)
                counts[i] = counts[i] + counts[i - 1];

            for (int i = len - 1; i >= 0; i--) {
                buffer[counts[(a[i] / radix) % 10] - 1] = a[i];
                counts[(a[i] / radix) % 10] = counts[(a[i] / radix) % 10] - 1;
            }

            for (int i = 0; i < len; i++) a[i] = buffer[i];

            radix *= 10;
        }
    }

    //冒泡排序
    public static <AnyType extends Comparable<? super AnyType>>
    void bubbleSort(AnyType[] a) {
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length - 1; j++)
                if (a[j].compareTo(a[j + 1]) > 0) swapReferences(a, j, j + 1);
    }

}
