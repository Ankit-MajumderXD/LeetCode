
class Solution {
    public boolean isPalindrome(int x) {

        // Negative numbers are not palindromes
        // Numbers ending in 0 are not palindromes (except 0)
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0;

        while (x > reversed) {

            int digit = x % 10;

            reversed = reversed * 10 + digit;

            x = x / 10;
        }

        // Even digits: x == reversed
        // Odd digits: remove middle digit using / 10
        return x == reversed || x == reversed / 10;
    }
}
