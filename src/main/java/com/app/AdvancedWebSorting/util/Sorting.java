package com.app.AdvancedWebSorting.util;

import com.app.AdvancedWebSorting.models.Mark;

import java.util.Comparator;

public class Sorting {
    private static final Comparator<Mark> markComparator = Comparator.comparingInt(Mark::getMark);


    public static void mergeSort(Mark[] arr, int startIndex, int endIndex) {
//      check whether the arr can be further subdivided
        if (startIndex >= endIndex) {
            return;
        }

//      calculate median
        int median = (startIndex + endIndex) / 2;

//      recursive call for the sub arr
        mergeSort(arr, startIndex, median);
        mergeSort(arr, median + 1, endIndex);

//        merge the current arr
        merge(arr, startIndex, median, endIndex);

    }

    public static void merge(Mark[] arr, int startIndex, int median, int endIndex) {
//      lengths of the two sub arrays
        int arr1Length = median - startIndex + 1;
        int arr2Length = endIndex - median;

//      copies of the two sub arrays
        Mark[] arr1 = new Mark[arr1Length];
        Mark[] arr2 = new Mark[arr2Length];

//      populating the two copies of the two sub arrays with the values of the two sub arrays
        for (int i = 0; i < arr1Length; i++) {
            arr1[i] = arr[startIndex + i];
        }

        for (int i = 0; i < arr2Length; i++) {
            arr2[i] = arr[median + i + 1];
        }

        int i, j, k;
        i = j = 0;
        k = startIndex;

        while (i < arr1Length && j < arr2Length) {
            if (arr1[i].getMark() <= arr2[j].getMark()) {
                arr[k] = arr1[i];
                i++;
            } else {
                arr[k] = arr2[j];
                j++;
            }
            k++;
        }

        while (i < arr1Length) {
            arr[k] = arr1[i];
            i++;
            k++;
        }

        while (j < arr2Length) {
            arr[k] = arr2[j];
            j++;
            k++;
        }
    }

    public static int partition(Mark[] arr, int startIndex, int endIndex) {
        Mark pivot = arr[endIndex];
        int greaterElementIndex = startIndex - 1;

        for (int i = startIndex; i < endIndex; ++i) {
            if (markComparator.compare(arr[i], pivot) <= 0) {
                ++greaterElementIndex;
                Mark temp = arr[greaterElementIndex];
                arr[greaterElementIndex] = arr[i];
                arr[i] = temp;
            }
        }

        Mark temp = arr[greaterElementIndex + 1];
        arr[greaterElementIndex + 1] = arr[endIndex];
        arr[endIndex] = temp;
        return greaterElementIndex + 1;
    }


    public static void quickSort(Mark[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivotIndex = partition(arr, startIndex, endIndex);
            quickSort(arr, startIndex, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, endIndex);
        }
    }

}
