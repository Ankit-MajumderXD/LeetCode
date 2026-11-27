
from typing import List

class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:

        n = len(items)

        # Find the cheapest item
        min_price = min(price for factor, price in items)

        # 0/1 Knapsack DP
        dp = [-1] * (budget + 1)
        dp[0] = 0

        for i in range(n):

            factor, price = items[i]

            # First purchase value: purchased item + free items
            value = 0

            for f, p in items:
                if f % factor == 0:
                    value += 1

            # 0/1 Knapsack: iterate backwards
            for j in range(budget, price - 1, -1):

                if dp[j - price] != -1:
                    dp[j] = max(
                        dp[j],
                        dp[j - price] + value
                    )

        # Use remaining budget to buy cheapest items
        ans = 0

        for spent in range(budget + 1):

            if dp[spent] != -1:

                extra = (budget - spent) // min_price

                ans = max(ans, dp[spent] + extra)

        return ans
