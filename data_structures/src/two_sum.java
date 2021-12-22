/*
Find the indices of two numbers whose sum equals target.


**** two sum ****

 */
import java.util.Arrays;

class TwoSum {
    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        int[] array1 =  {4,9,13,11,0,-4,34};
        int target = 45;

        System.out.println("**** two sum ****");
        System.out.print("array1: ");
        for (int i = 0; i  < array1.length; i++) {
            System.out.print(array1[i] + " ");
        }
        System.out.println("\ntarget: " + target);
        int[] result = ts.begin(array1,target);
        System.out.println("result: " + Arrays.toString(result));
    }

    public int[] begin(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("no solution found....");
    }

}
