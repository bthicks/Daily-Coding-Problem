import java.util.*;

public class Day2 {
    
    // Day 2:
    // Given an array of integers, return a new array such that each element at
    // index i of the new array is the product of all the numbers in the
    // original array except the one at i.
    //
    // For example, if our input was [1, 2, 3, 4, 5], the expected output would
    // be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output
    // would be [2, 3, 6].
    //
    // Follow up: what if you can't use division?

    // O(n^2) time, O(n) space
    // Brute force way involves a nested iteration, finding the product of every
    // number excluding the number at the current index.
    public static int[] arrayProduct1(int[] nums) {
        int[] products = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int product = 1;

            for (int j = 0; j < nums.length; j++) {
                if (j == i) {
                    continue;
                }
                product = product * nums[j];
            }

            products[i] = product;
        }

        return products;
    }

    // O(n) time, O(1) space
    // Find the product of all numbers in the array and then divide by each of
    // the numbers.
    public static int[] arrayProduct2(int[] nums) {
        int product = 1;

        for (int i = 0; i < nums.length; i++) {
            product = product * nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = product / nums[i];
        }

        return nums;
    }

    // O(n) time, O(n) space
    // Without division. The ith element simply needs the product of numbers
    // before i and the product of numbers after i. Then we can multiply the two
    // numbers to get our desired product.
    public static int[] arrayProduct3(int[] nums) {
        int[] prevProducts = new int[nums.length];
        int[] nextProducts = new int[nums.length];

        int prevProduct = 1;
        int nextProduct = 1;

        // Generate previous and next products.
        for (int i = 0; i < nums.length; i++) {
            int j = nums.length - 1 - i;

            prevProduct = prevProduct * nums[i];
            nextProduct = nextProduct * nums[j];

            prevProducts[i] = prevProduct;
            nextProducts[j] = nextProduct;
        }

        // Generate result.
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                nums[i] = nextProducts[i + 1];
            } else if (i == nums.length - 1) {
                nums[i] = prevProducts[i - 1];
            } else {
                nums[i] = prevProducts[i - 1] * nextProducts[i + 1];
            }
        }

        return nums;
    }
}