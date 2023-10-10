import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayCodingTest {

    @Test
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
}
