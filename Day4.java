public class Day4 {

    // Day 4:
    // Given an array of intergers, find the missing positive integer in linear
    // time and constant space. In other words, find the lowest positive integer
    // that does not exist in the array. The array can contain duplicates and
    // negative numbers as well.
    //
    // For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0]
    // should give 3.
    //
    // You can modify the input array in-place.

    // O(n) time, O(1) space
    // Since the first missing positive number must be between 1 and len(array) 
    // + 1, we can ignore any negative numbers and numbers bigger than 
    // len(array). The basic idea is to use the indices of the array itself to
    // reorder the elements to where they should be. We traverse the array and
    // swap elements between 0 and the length of the array to their value's 
    // index. We stay at each index until we find that index's value and keep on
    // swapping.
    //
    // By the end of this process, all the first positive numbers should be
    // grouped in order at the beginning of the array. We don't care about the
    // others. This only takes O(n) time since we swap each element at least
    // once.
    //
    // Then we can iterate through the array and return the index of the first
    // number that doesn't match, just like before.
    public static int firstMissingPositive(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }

        for (int i = 0; i < nums.length; i++) {
            while (i + 1 != nums[i] && 0 < nums[i] && nums[i] <= nums.length) {
                int v = nums[i];
                int temp = nums[i];
                nums[i] = nums[v - 1];
                nums[v - 1] = temp;

                if (nums[i] == nums[v - 1]) {
                    break;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}