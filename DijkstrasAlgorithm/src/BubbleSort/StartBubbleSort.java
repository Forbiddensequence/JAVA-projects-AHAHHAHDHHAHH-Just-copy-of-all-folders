package BubbleSort;

import java.util.Random;

public class StartBubbleSort {
    public static void main() {
        final Random random = new Random();
        int[] arr=new int[random.nextInt(100)];
        for(int i=0;i<arr.length;i++){
            arr[i]=random.nextInt(Integer.MAX_VALUE);
        }
        System.out.println("Array before sort");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        System.out.println("Array after sort");
        bubblesort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    public static void bubblesort(int[] array){

        for (int j=0;j<array.length-1;j++) {
            for(int i=0;i<array.length-1;i++){
                if(array[i]>array[i+1]){
                    array[i]=array[i]+array[i+1];
                    array[i+1]=array[i]-array[i+1];
                    array[i]=array[i]-array[i+1];
                }
            }
        }
    }
}
