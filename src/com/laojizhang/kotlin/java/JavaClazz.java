package com.laojizhang.kotlin.java;

import com.laojizhang.kotlin.clazz.*;

import java.util.ArrayList;

public class JavaClazz {

    private String proprety = "我是JavaClazz 属性";

    public String getProprety() {
        return proprety;
    }

    public void setProprety(String proprety) {
        this.proprety = proprety;
    }

    public void voidMethod() {
        System.out.println("JavaClazz voidMethod()");
        return;
    }

    public void is() {
        System.out.println("JavaClazz is()");
    }

    public ArrayList<String> getList() {
//        return new ArrayList<>();
        return null;
    }


    public static void main(String[] args) {
        Kotlin2Java kotlin2Java = new Kotlin2Java();
        kotlin2Java.method1();

        Kotlin2JavaKt.callKotlinStaticMethod();

        new Person3("aaa");
        new EmptyClass();
//        bubbleSort();
//        System.out.println();
//        quickSort(quickSortArr, 0, quickSortArr.length - 1);

//        mergeArr();

//        int[] arr = {48, 56, 72, 32, 86, 12};
//        mergeIntoSort(arr, 0, arr.length - 1);
//        printlnArr(arr);
    }

    private static void bubbleSort() {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
        int temp = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
    }

    static int quickSortArr[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
//    static int quickSortArr[] = {48, 56, 72, 32, 86, 12};

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start;
        int j = end;
        int middle = arr[start];
        int tmp = 0;
        boolean flag = true;
        while (i < j) {
            if (flag) {
                if (arr[j] < middle) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    flag = false;
                } else {
                    j--;
                }
            } else {
                if (arr[i] > middle) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    flag = true;
                } else {
                    i++;
                }
            }
        }
        printlnArr(arr);
        quickSort(arr, start, i - 1);
        quickSort(arr, j + 1, end);
    }

    public static void printlnArr(int[] arr) {
        for (int item : arr)
            System.out.print(item + "     ");
        System.out.println();
    }

    public static void mergeArr() {
        int[] arrA = new int[]{1, 3, 4, 5, 7, 9, 11, 13, 15, 17, 19};
        int[] arrB = new int[]{2, 4, 6, 8, 10, 12, 14, 17};

        int[] newArr = new int[arrA.length + arrB.length];
        int i = 0, j = 0, k = 0;

        while (i < arrA.length && j < arrB.length) {
            int tmp = 0;
            if (arrA[i] < arrB[j]) {
                tmp = arrA[i];
                i++;
            } else {
                tmp = arrB[j];
                j++;
            }
            newArr[k] = tmp;
            k++;
        }
        if (i != arrA.length - 1) {
            for (; i < arrA.length; i++) {
                newArr[k++] = arrA[i];
            }
        }

        if (j != arrB.length - 1) {
            for (; j < arrB.length; j++) {
                newArr[k++] = arrB[j];
            }
        }
        printlnArr(newArr);
    }

    public static void mergeIntoSort(int[] arr, int start, int end) {
        int middle = (start + end) / 2;
        if (start < end) {
            mergeIntoSort(arr, start, middle);
            mergeIntoSort(arr, middle + 1, end);

            merge(arr, start, middle, end);
        }
    }

    private static void merge(int[] arr, int start, int middle, int end) {
        int[] tmp = new int[arr.length];
        int rstart = middle + 1;
        int lstart = start;

        int newIndex = start;
        while (lstart <= middle && rstart <= end) {
            if (arr[rstart] < arr[lstart]) {
                tmp[newIndex++] = arr[rstart++];
            } else {
                tmp[newIndex++] = arr[lstart++];
            }
        }

        while (lstart <= middle) {
            tmp[newIndex++] = arr[lstart++];
        }

        while (rstart <= end) {
            tmp[newIndex++] = arr[rstart++];
        }

        printlnArr(tmp);

        while (start <= end) {
            arr[start] = tmp[start];
            start++;
        }
    }
}
