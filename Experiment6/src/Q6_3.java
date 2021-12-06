import java.util.Arrays;

public class Q6_3 {
    public static void main(String[] args) {

    }
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a){
        int j;
        for(int p=1;p<a.length;p++){
            AnyType tmp=a[p];
            for(j=p;j>0&&tmp.compareTo(a[j-1])<0;j--)
                a[j]=a[j-1];
            a[j]=tmp;
        }
    }


    public static <AnyType extends Comparable<? super AnyType>>
    void shellSort(AnyType[] a){
        int j;
        for(int gap=a.length/2;gap>0;gap/=2)
            for(int i=gap;i<a.length;i++){
                AnyType tmp=a[i];
                for(j=i;j>=gap&&tmp.compareTo(a[j-gap])<0;j-=gap)
                    a[j]=a[j-gap];
                a[j]=tmp;
            }
    }


    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a,AnyType[]tmpArray,int left,int right){
        if(left<right){
            int center=(left+right)/2;
            mergeSort(a,tmpArray,left,right);
            mergeSort(a,tmpArray,center+1,right);
            merge(a,tmpArray,left,center+1,right);
        }
    }
    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] a,AnyType[] tmpArray,int leftPos,int rightPos,int rightEnd){
        int leftEnd=rightPos-1;
        int tmpPos=leftPos;
        int numElements=rightEnd-leftPos+1;

        while (leftPos<=leftEnd&&rightPos<=rightEnd)
            if(a[leftPos].compareTo(a[rightPos])<0)
                tmpArray[tmpPos++]=a[leftPos++];
            else
                tmpArray[tmpPos++]=a[rightPos++];
        while (leftPos<=leftEnd)
            tmpArray[tmpPos++]=a[leftPos++];
        while (rightPos<=rightEnd)
            tmpArray[tmpPos++]=a[rightPos++];

        for(int i=0;i<numElements;i++,rightEnd--)
            a[rightEnd]=tmpArray[rightEnd];
    }
    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a){
        AnyType[] tmpArray=(AnyType[]) new Comparable[a.length];
        mergeSort(a,tmpArray,0,a.length-1);
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void quickSort(AnyType[] a){
        quickSort(a,0,a.length-1);
    }
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3(AnyType[] a,int left,int right){
        int center=(left+right)/2;
        if(a[center].compareTo(a[left])<0)
            swapReferences(a,left,center);
        if(a[right].compareTo(a[left])<0)
            swapReferences(a,left,right);
        if(a[right].compareTo(a[center])<0)
            swapReferences(a,center,right);

        swapReferences(a,center,right-1);
        return a[right-1];
    }
    public static <AnyType extends Comparable<? super AnyType>>
    void quickSort(AnyType[] a,int left,int right){
        if(left+CUTOFF<=right){
            AnyType pivot=median3(a,left,right);

            int i=left,j=right-1;
            for(;;){
                while (a[++i].compareTo(pivot)<0){}
                while (a[--j].compareTo(pivot)>0){}
                if(i<j)
                    swapReferences(a,i,j);
                else
                    break;
            }
            swapReferences(a,i,right-1);

            quickSort(a,left,i-1);
            quickSort(a,i+1,right);
        }
        else
            insertionSort(a);
    }
}
