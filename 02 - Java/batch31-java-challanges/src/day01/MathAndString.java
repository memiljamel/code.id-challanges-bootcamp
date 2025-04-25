package day01;

public class MathAndString {

    public static void main(String[] args) {
        // Math

        /* 1. Reverse */
        // System.out.println(reverse(42)); // 24
        // System.out.println(reverse(413)); // 314
        // System.out.println(reverse(4563)); // 3654

        /* 2. isPalindrome */
        // System.out.println(isPalindrome(121)); // true
        // System.out.println(isPalindrome(2147447412)); // true
        // System.out.println(isPalindrome(110)); // false

        // String

        /* 3. Capitalize */
        // System.out.println(capitalize("this is a very special title")); // This Is A Very Special Title
        // System.out.println(capitalize("effective java is great")); // Effective Java Is Great

        /* 4. IsNoDuplicateChar */
        // System.out.println(isNoDuplicateChar("codeid")); // false
        // System.out.println(isNoDuplicateChar("abcde")); // true
        // System.out.println(isNoDuplicateChar("jdbc")); // true

        /* 5. IsBrace */
        System.out.println(isBrace("(())")); // true
        System.out.println(isBrace("()()")); // true
        System.out.println(isBrace("((()")); // false
    }

    public static int reverse(int n) {
        String reversedNumber = "";

        while (n != 0) {
            reversedNumber += n % 10;
            n = n / 10;
        }

        return Integer.parseInt(reversedNumber);
    }

    public static boolean isPalindrome(int n) {
        if (n == reverse(n)) {
            return true;
        }

        return false;
    }

    public static String capitalize(String input) {
        StringBuilder builder = new StringBuilder();

        String[] s = input.split(" ");

        for (int i = 0; i < s.length; i++) {
            char word = s[i].charAt(0);
            builder.append(Character.toUpperCase(word))
                    .append(s[i].substring(1))
                    .append(" ");
        }

        return builder.toString();
    }

    public static boolean isNoDuplicateChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = (i + 1); j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isBrace(String s) {
        int balance = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                balance++;
            } else if (chars[i] == ')') {
                balance--;
            }
        }

        return balance == 0;
    }
}
