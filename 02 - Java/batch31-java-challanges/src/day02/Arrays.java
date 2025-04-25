package day02;

public class Arrays {

    public static void main(String[] args) {

        /* 1. OrderEvenBeforeOdd */
        // displayArray(orderEvenBeforeOdd(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})); // [ 2, 4, 6, 8, 10, 3, 7, 1, 9, 5 ]
        // displayArray(orderEvenBeforeOdd(new int[] { 3, 4, 5, 2, 10 })); // [ 2, 4, 10, 3, 5 ]
        // displayArray(orderEvenBeforeOdd(new int[] { 2, 4, 6, 10, 1 })); // [ 2, 4, 6, 10, 1 ]

        /* 2. IsPalindrome */
        // System.out.println((isPalindrome(new String[] { "donout", "king", "donout" }))); // true
        // System.out.println((isPalindrome(new String[] { "min", "max", "min" }))); // true
        // System.out.println((isPalindrome(new String[] { "true", "false", "false" }))); // false

        /* 3. AddOnePlus */
        displayArray(addOnePlus(new int[] { 1, 3, 2, 4 })); // [ 1, 3, 2, 5 ]
        displayArray(addOnePlus(new int[] { 1, 4, 8, 9 })); // [ 1, 4, 9, 0 ]
        displayArray(addOnePlus(new int[] { 9, 9, 9, 9 })); // [ 1, 0, 0, 0, 0 ]
    }

    public static void displayArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }

        System.out.println();
    }

    public static int[] orderEvenBeforeOdd(int[] array) {
        int i = 0;

        while (i < array.length && array[i] % 2 == 0) {
            i++;
        }

        for (int j = i + 1; j < array.length; j++) {
            if (array[j] % 2 == 0) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }

        return array;
    }

    public static boolean isPalindrome(String[] array) {
        for (int i = 0; i < (array.length / 2); i++) {
            if (!array[i].equals(array[array.length - i - 1])) {
                return false;
            }
        }

        return true;
    }

    public static int[] addOnePlus(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != 9) {
                array[i]++;
                break;
            } else {
                array[i] = 0;
            }
        }

        if (array[0] == 0) {
            int[] rst = new int[array.length + 1];
            rst[0] = 1;
            return rst;
        }

        return array;
    }
}
