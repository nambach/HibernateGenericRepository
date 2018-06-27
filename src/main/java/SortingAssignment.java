import util.HeapSort;
import util.MergeSort;
import util.QuickSort;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SortingAssignment {

    private static long selectionSort = 0;
    private static long bubbleSort = 0;
    private static long insertionSort = 0;
    public static long quickSort = 0;
    public static long mergeSort = 0;
    public static long heapSort = 0;
    private static long start;
    public static Map<String, Integer> count;

    private static String winner = null;

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        Random rd = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            array[i] = rd.nextInt();
        }
        return array;
    }

    private static int[] cloneArray(int[] src) {
        int[] des = new int[src.length];
        System.arraycopy(src, 0, des, 0, src.length);
        return des;
    }

    public static void runTest() {
        final int[] arr = generateArray(100000);

        start = System.currentTimeMillis();

        final int[] arr1 = cloneArray(arr);
        final int[] arr2 = cloneArray(arr);
        final int[] arr3 = cloneArray(arr);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(startHeapsort(arr1));
        executorService.execute(startQuicksort(arr2));
        executorService.execute(startMergesort(arr3));
        executorService.shutdown();
        try {
            if (executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                count.put(winner, count.get(winner) + 1);

                winner = null;
//                System.out.println();
//                System.out.println("hea:" + heapSort);
//                System.out.println("qui:" + quickSort);
//                System.out.println("meg:" + mergeSort);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable startInsertion(final int[] arr3) {
        return new Runnable() {
            public void run() {
                int n = arr3.length;
                for (int j = 1; j < n; j++) {
                    int key = arr3[j];
                    int i = j - 1;
                    while ((i > -1) && (arr3[i] > key)) {
                        arr3[i + 1] = arr3[i];
                        i--;
                    }
                    arr3[i + 1] = key;
                }
                if (winner == null) {
                    winner = "insertion";
                }
                insertionSort = System.currentTimeMillis() - start;
            }
        };
    }

    private static Runnable startQuicksort(final int[] arr) {
        return new Runnable() {
            public void run() {
                QuickSort.sort(arr, 0, arr.length - 1);
                if (winner == null) {
                    winner = "quickSort";
                }
                quickSort += System.currentTimeMillis() - start;
            }
        };
    }

    private static Runnable startMergesort(final int[] arr) {
        return new Runnable() {
            public void run() {
                MergeSort.sort(arr, 0, arr.length - 1);
                if (winner == null) {
                    winner = "mergeSort";
                }
                mergeSort += System.currentTimeMillis() - start;
            }
        };
    }

    private static Runnable startHeapsort(final int[] arr) {
        return new Runnable() {
            public void run() {
                HeapSort.sort(arr);
                if (winner == null) {
                    winner = "heapSort";
                }
                heapSort += System.currentTimeMillis() - start;
            }
        };
    }

    private static Runnable startBubble(final int[] arr2) {
        return new Runnable() {
            public void run() {
                int n = arr2.length;
                int temp = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 1; j < (n - i); j++) {
                        if (arr2[j - 1] > arr2[j]) {
                            //swap elements
                            temp = arr2[j - 1];
                            arr2[j - 1] = arr2[j];
                            arr2[j] = temp;
                        }

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }

                if (winner == null) {
                    winner = "bubble";
                }
                bubbleSort = System.currentTimeMillis() - start;
            }
        };
    }

    private static Runnable startSelection(final int[] arr1) {
        return new Runnable() {
            public void run() {
                for (int i = 0; i < arr1.length - 1; i++) {
                    int index = i;
                    for (int j = i + 1; j < arr1.length; j++) {
                        if (arr1[j] < arr1[index]) {
                            index = j;//searching for lowest index
                        }
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ignored) {
                        }
                    }
                    int smallerNumber = arr1[index];
                    arr1[index] = arr1[i];
                    arr1[i] = smallerNumber;
                }

                if (winner == null) {
                    winner = "selection";
                }
                selectionSort = System.currentTimeMillis() - start;
            }
        };
    }
}
