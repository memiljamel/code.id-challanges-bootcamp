package day01;

import java.util.Scanner;

public class Iterators {

    public static void main(String[] args) throws InterruptedException {
        // iterator1(5);
        // iterator2(5);
        // iterator3(5);
        // iterator4(5);
        // iterator5(5);
        // iterator6(5);
        // iterator7();
        // iterator9();
         iterator10();
    }

    public static void iterator1(int n) throws InterruptedException {
        int count = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Thread.sleep(500L);
                System.out.printf("%d ", count);
                count++;
            }

            System.out.println();
        }
    }

    public static void iterator2(int n) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                Thread.sleep(500L);
                System.out.printf("%d ", i + (j + 1));
            }

            System.out.println();
        }
    }

    public static void iterator3(int n) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (n - i); j++) {
                Thread.sleep(500L);
                System.out.printf("%d ", i + (j + 1));
            }

            System.out.println();
        }
    }

    public static void iterator4(int n) throws InterruptedException {
        int count = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Thread.sleep(500L);
                if (i <= j) {
                    System.out.printf("%d ", count);
                } else {
                    System.out.print("  ");
                }

                count++;
            }

            System.out.println();
            count = 1;
        }
    }

    public static void iterator5(int n) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Thread.sleep(500L);
                if (i == j) {
                    System.out.printf("%d ", (i + 1));
                } else if (i <= j) {
                    System.out.print("10 ");
                } else {
                    System.out.print("20 ");
                }
            }

            System.out.println();
        }
    }

    public static void iterator6(int n) throws InterruptedException {
        for (int i = n; i > 0; i--) {
            for (int j = n; j > 0; j--) {
                Thread.sleep(500L);
                if (i == j) {
                    System.out.printf("%d ", i);
                } else if (i <= j) {
                    System.out.print("10 ");
                } else {
                    System.out.print("20 ");
                }
            }

            System.out.println();
        }
    }

    public static void iterator7() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input jumlah baris piramid: ");
        int n = scanner.nextInt();

        for (int i = n; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                Thread.sleep(500L);
                System.out.printf("%d ", j);
            }

            for (int j = 2; j <= i; j++) {
                Thread.sleep(500L);
                System.out.printf("%d ", j);
            }

            System.out.println();
        }
    }

    public static void iterator9() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input jumlah n: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = n; j > 0; j--) {
                    Thread.sleep(500L);
                    System.out.printf("%d ", j);
                }
            } else {
                for (int j = 0; j < n; j++) {
                    Thread.sleep(500L);
                    System.out.printf("%d ", (j + 1));
                }
            }

            System.out.println();
        }
    }

    public static void iterator10() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input jumlah n: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < n; j++) {
                    Thread.sleep(500L);
                    if (j % 2 == 0) {
                        System.out.print("- ");
                    } else {
                        System.out.printf("%d ", (j + 1));
                    }
                }
            } else {
                for (int j = 0; j < n; j++) {
                    Thread.sleep(500L);
                    if (j % 2 == 0) {
                        System.out.printf("%d ", (j + 1));
                    } else {
                        System.out.print("_ ");
                    }
                }
            }

            System.out.println();
        }
    }
}
