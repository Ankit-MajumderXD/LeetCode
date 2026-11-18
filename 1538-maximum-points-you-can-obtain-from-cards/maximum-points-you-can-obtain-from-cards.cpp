
class Solution {
public:
    int maxScore(vector<int>& cardPoints, int k) {
        int n = cardPoints.size();
        int totalSum = 0;

        for (int points : cardPoints) {
            totalSum += points;
        }

        int windowSize = n - k;

        // If we take all cards
        if (windowSize == 0) {
            return totalSum;
        }

        int windowSum = 0;

        // Calculate the first window
        for (int i = 0; i < windowSize; i++) {
            windowSum += cardPoints[i];
        }

        int minWindowSum = windowSum;

        // Slide the window
        for (int i = windowSize; i < n; i++) {
            windowSum += cardPoints[i] - cardPoints[i - windowSize];

            minWindowSum = min(minWindowSum, windowSum);
        }

        return totalSum - minWindowSum;
    }
};
