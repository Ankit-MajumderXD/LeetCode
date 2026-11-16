
class Solution {
    public String convert(String s, int numRows) {

        // Edge cases
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        int direction = 1;

        for (int i = 0; i < s.length(); i++) {

            // Add character to current row
            rows[currentRow].append(s.charAt(i));

            // Change direction at top or bottom
            if (currentRow == 0) {
                direction = 1;
            } else if (currentRow == numRows - 1) {
                direction = -1;
            }

            // Move to next row
            currentRow += direction;
        }

        // Combine all rows
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numRows; i++) {
            result.append(rows[i]);
        }

        return result.toString();
    }
}
