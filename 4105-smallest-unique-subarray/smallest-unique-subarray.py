
from typing import List

class Solution:
    def smallestUniqueSubarray(self, nums: List[int]) -> int:

        n = len(nums)

        # Build suffix array
        a = nums + [0]
        N = n + 1

        p = sorted(range(N), key=lambda i: a[i])
        c = [0] * N

        for i in range(1, N):
            c[p[i]] = c[p[i - 1]] + (
                a[p[i]] != a[p[i - 1]]
            )

        k = 0

        while (1 << k) < N:

            length = 1 << k

            p = [(x - length) % N for x in p]
            p.sort(key=lambda x: c[x])

            cn = [0] * N

            for i in range(1, N):
                prev = p[i - 1]
                curr = p[i]

                prev_pair = (
                    c[prev],
                    c[(prev + length) % N]
                )

                curr_pair = (
                    c[curr],
                    c[(curr + length) % N]
                )

                cn[curr] = cn[prev] + (
                    curr_pair != prev_pair
                )

            c = cn
            k += 1

        # Remove sentinel
        sa = p[1:]

        # Build rank array
        rank = [0] * n

        for i in range(n):
            rank[sa[i]] = i

        # Kasai's LCP algorithm
        lcp = [0] * n
        h = 0

        for i in range(n):

            r = rank[i]

            if r == 0:
                continue

            j = sa[r - 1]

            while (i + h < n and
                   j + h < n and
                   nums[i + h] == nums[j + h]):
                h += 1

            lcp[r] = h

            if h > 0:
                h -= 1

        # Find minimum valid unique subarray
        ans = n

        for r in range(n):

            max_lcp = lcp[r]

            if r + 1 < n:
                max_lcp = max(max_lcp, lcp[r + 1])

            unique_len = max_lcp + 1

            # Check that the subarray fits
            remaining = n - sa[r]

            if unique_len <= remaining:
                ans = min(ans, unique_len)

        return ans
