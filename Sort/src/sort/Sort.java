/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Dat Nguyen
 */
public class Sort {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //STEP 1: Display a screen to ask users to enter a positive decimal number
        int in = getInt("Enter number of array:");
        //STEP 2: Generate random integer in number range for each array element.
        int[] array = generateArray(in);
        //STEP 3: Display array before sorting
        displayArray(array, "Unsorted array: ");
        //STEP 4: Display array after sorting
        int[] sortedArray = bubbleSort(array);
        displayArray(sortedArray, "Sorted array: ");
    }

    //Display a screen to ask users to enter a positive decimal number
    private static int getInt(String msg) {
        while (true) {
            System.out.println(msg);
            try {
                int in = Integer.parseInt(sc.nextLine());
                if (in > 0) {
                    return in;
                } else {
                    System.out.println("Enter a positive decimal number");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong enter format, please enter a number");
            }
        }
    }

    //Generate random integer in number range for each array element.
    private static int[] generateArray(int in) {
        int[] array = new int[in];
        Random random = new Random();
        for (int i = 0; i < in; i++) {
            array[i] = random.nextInt(in);
        }
        return array;
    }

    //Display array
    private static void displayArray(int[] array, String msg) {
        System.out.print(msg);
        System.out.print(" [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    //Selection sort
    private static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int indexMin = i;
            //Loop to find index of min number
            for (int j = i + 1; j < array.length; j++) {
                if (array[indexMin] > array[j]) {
                    indexMin = j;
                }
            }
            //Swap min number with index
            int temp = array[indexMin];
            array[indexMin] = array[i];
            array[i] = temp;
        }
        return array;
    }

    //Bubble sort
    private static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    //Swap
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

}
