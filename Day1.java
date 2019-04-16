import java.util.*;

public class Day1 {
    
    // Day 1:
    // Given a list of numbers and a number k, return whether any two numbers
    // from the list add up to k.
    //
    // For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7
    // is 17.
    //
    // Bonus: Can you do this in one pass?

    // O(n^2) time, O(1) space
    // Brute force way would involve a nested iteration to check for every pair
    // of numbers.
    public static boolean twoSum1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == k) {
                    return true;
                }
            }
        }

        return false;
    }

    // O(n) time, O(n) space
    // Use a set to remember the numbers we've seen so far. Then for a given
    // number, we can check if there is another number that, if added, would sum
    // to k. This would be O(n) since lookups of sets are O(1) each.
    public static boolean twoSum2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (set.contains(k - num)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }

    // O(n log n) time, O(1) space
    // Yet another solution involves sorting a list. We can then interate
    // through the list and run a binary search on k - nums[i]. Since we run 
    // binary search on N elements, this would take O(n log n) with O(1) space.
    public static boolean twoSum3(int[] nums, int k) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int target = k - nums[i];
            int j = binarySearch(target, nums);

            // Check that binary search found the target and that it's not in 
            // the same index as i. If it is in the same index, we can check
            // nums[i + 1] and nums[i - 1] to see if there's another number with
            // the same value as nums[i].
            if (j == -1) {
                continue;
            } else if (j != i) {
                return true;
            } else if (j + 1 < nums.length && nums[j + 1] == target) {
                return true;
            } else if (j - 1 >= 0 && nums[j - 1] == target) {
                return true;
            }
        }

        return false;
    }

    private static int binarySearch(int target, int[] nums) {
        return binarySearchHelper(target, nums, 0, nums.length - 1);
    }

    private static int binarySearchHelper(int target, int[] nums, int low, int high) {
        if (high < low) {
            return -1;
        }

        int mid = low + ((high - low) / 2);

        if (nums[mid] == target) {
            return mid;
        }
        else if (nums[mid] > target) {
            return binarySearchHelper(target, nums, low, mid - 1);
        } else {
            return binarySearchHelper(target, nums, mid + 1, high);
        }
    }
}

