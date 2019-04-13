import java.lang.StringBuilder;
import java.lang.Math;

public class LongestPalindrome {

    public static void main(String[] args) {
        String str = "ccc";
        String longest = longestPalindrome(str);
        System.out.println(longest);
    }

    private static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        String newStr = fillStr(s);

        int resultId = 0;
        int resultLen = 0;

        int newSize = newStr.length();
        int[] p = new int[newSize];
        int id = 0;
        int maxRight = 0;

        for (int i = 0; i < newSize; i++) {
            p[i] = i < maxRight ? Math.min(p[i - 2*id], maxRight - i) : 1;

            while(i - p[i] >= 0 && i + p[i] < newSize && newStr.charAt(i + p[i]) == newStr.charAt(i - p[i])) {
                p[i]++;
            }

            if (maxRight < p[i] + i) {
                maxRight = p[i] + i;
            }

            if (resultLen < p[i]) {
                resultLen = p[i];
                resultId = i;
            }
        }

        int start = (resultId - resultLen + 1) / 2;
        int end = start + resultLen - 1;
        System.out.println(String.format("resultId=%d, resultLen=%d, p=%s, start=%d, end=%d", resultId, resultLen, printArr(p), start, end));
        return s.substring(start, end);
    }

    private static String printArr(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int item : arr) {
            sb.append(String.valueOf(item)).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String fillStr(String str) {
        String specialChar = "#";
        StringBuilder sb = new StringBuilder(specialChar);
        for (int i = 0, size = str.length(); i < size; i++) {
            sb.append(str.charAt(i)).append(specialChar);
        }
        return sb.toString();
    }
}