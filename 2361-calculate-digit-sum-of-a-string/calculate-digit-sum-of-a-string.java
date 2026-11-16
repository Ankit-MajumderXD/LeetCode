
class Solution {
    public String digitSum(String s, int k) {

        while (s.length() > k) {

            StringBuilder result = new StringBuilder();

            // Divide string into groups of size k
            for (int i = 0; i < s.length(); i += k) {

                int sum = 0;

                // Calculate digit sum of current group
                for (int j = i; j < Math.min(i + k, s.length()); j++) {
                    sum += s.charAt(j) - '0';
                }

                // Append sum to the new string
                result.append(sum);
            }

            s = result.toString();
        }

        return s;
    }
}
