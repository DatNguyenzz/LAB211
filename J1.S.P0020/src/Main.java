
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dat Nguyen
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //STEP 1: Display a screen to ask users to enter a positive decimal number
        int in = getInt("Enter number of array:", 1, Integer.MAX_VALUE);
        //STEP 2: Input elements of array
        int[] array = inputElementToArray(in);
        //STEP 3: Display array before sorting
        displayArray(array, "Unsorted array: ");
        //STEP 4: Display array after sorting
        int[] sortedArray = selectionSort(array);
        displayArray(sortedArray, "Sorted array: ");
    }

    //Display a screen to ask users to enter a positive decimal number
    private static int getInt(String msg, int min, int max) {
        while (true) {
            System.out.println(msg);
            try {
                int in = Integer.parseInt(sc.nextLine());
                if (in < min || in > max) {
                    System.out.println("Input out of range!");
                } else {
                    return in;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong enter format, please enter a number");
            }
        }
    }

    //Input elements of array
    private static int[] inputElementToArray(int number) {
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = getInt("Enter a elements to add to array", Integer.MIN_VALUE, Integer.MAX_VALUE);
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
}
