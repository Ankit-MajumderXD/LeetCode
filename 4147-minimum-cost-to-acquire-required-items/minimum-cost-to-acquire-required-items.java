
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        // Option 1: Buy type 1 and type 2 separately
        long option1 = (long) need1 * cost1 + (long) need2 * cost2;

        // Option 2: Buy type 3 items for both requirements
        long option2 = (long) Math.max(need1, need2) * costBoth;

        // Option 3: Buy type 3 for common requirements
        // and individual items for the remaining requirement
        long common = Math.min(need1, need2);

        long option3 = common * costBoth
                     + (long) (need1 - common) * cost1
                     + (long) (need2 - common) * cost2;

        return Math.min(option1, Math.min(option2, option3));
    }
}
