import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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
}
