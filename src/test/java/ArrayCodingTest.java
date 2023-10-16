import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayCodingTest {

    @Test
    @DisplayName("1. Two sum")
    void toSum() {
        int[] result = twoSum(new int[]{2,7,11,15}, 9);
        assertArrayEquals(new int[]{0,1}, result);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if(numMap.containsKey(target-nums[i])) {
                return new int[]{numMap.get(target-nums[i]), i};
            }
            numMap.put(nums[i],i);
        }
        return null;
    }

    @Test
    @DisplayName("42. Trapping Rain Water")
    void trapTest() {
        int[] nums = new int[]{1,1,0,2,1,0,1,3,2,1,2,1};
//        int result = trap(nums);
        int result = trapStack(nums);
        assertEquals(result, 6);
    }

    public int trapStack(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int volume = 0;

        for(int i=0; i<height.length; i++) {
            while(!stack.isEmpty() && height[i] > height[stack.peek()]) {
                Integer top = stack.pop();
                if(stack.isEmpty()) break;

                int distance = i - stack.peek() - 1;
                int waters = Math.min(height[i], height[stack.peek()]) - height[top];
                volume += distance * waters;
            }
            stack.push(i);
        }
        return volume;
    }

    public int trap(int[] height) {
        int volume = 0;
        int left = 0;
        int right = height.length-1;
        int leftMax = height[left];
        int rightMax = height[right];

        while(left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);

            if(leftMax <= rightMax) {
                volume += leftMax - height[left];
                left++;
            } else {
                volume += rightMax - height[right];
                right--;
            }
        }
        return volume;
    }

    @Test
    @DisplayName("15. 3Sum")
    void ThreeSum() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> sum = threeSum1(nums);
//        List<List<Integer>> sum = threeSum2(nums);
        List<List<Integer>> myList = Arrays.asList(
                Arrays.asList(-1, -1, 2),
                Arrays.asList(-1, 0, 1)
        );
        assertEquals(sum, myList);
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> reuslt = new LinkedList<>();
        Arrays.sort(nums);

        for(int i=0; i<nums.length-2; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j=i+1; j<nums.length-1; j++) {
                if(j > i+1 && nums[j]==nums[j-1]) continue;
                for(int k=j+1; k<nums.length; k++) {
                    if(k > j+1 && nums[k] == nums[k-1]) continue;
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        reuslt.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return reuslt;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        int left, right, sum;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for(int i=0; i<nums.length-2; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;
            left = i + 1;
            right = nums.length-1;
            while (left<right) {
                sum = nums[i] + nums[left] + nums[right];
                if(sum < 0) left++;
                else if (sum > 0) right--;
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left<right && nums[left] == nums[left+1]) left++;
                    while (left<right && nums[right] == nums[right-1]) right--;

                    left++;
                    right--;
                }
            }
        }
        return result;
    }

    @Test
    @DisplayName("561. Array Partition I")
    void arrayPartition1() {
        int[] nums = new int[]{1,4,3,2};
        int result = arrayPairSum(nums);
        assertEquals(result, 4);
    }

    private int arrayPairSum(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for(int i=0; i<nums.length; i++) {

            if(i % 2 == 0) {
                sum += nums[i];
            }
        }
        return sum;
    }

    @Test
    @DisplayName("238. Product of Array Except Self")
    void productExceptSelf() {
        int[] nums = new int[]{1,2,3,4};
        int[] result = productExceptSelf(nums);
        assertArrayEquals(result, new int[]{24,12,8,6});
    }

    private int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        int p = 1;
        for(int i=0; i<nums.length; i++) {
            result[i] = p;
            p *= nums[i];
        }
        p = 1;
        for (int i=nums.length-1; i>=0; i--) {
            result[i] *= p;
            p *= nums[i];
        }

        return result;
    }


}
