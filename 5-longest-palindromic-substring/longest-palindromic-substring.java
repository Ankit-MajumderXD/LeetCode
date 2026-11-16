
class Solution {
    public String longestPalindrome(String s) {

        int start = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            // Odd length palindrome
            int len1 = expand(s, i, i);

            // Even length palindrome
            int len2 = expand(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > maxLength) {
                maxLength = len;

                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLength);
    }

    private int expand(String s, int left, int right) {

        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        // Return palindrome length
        return right - left - 1;
    }
}
