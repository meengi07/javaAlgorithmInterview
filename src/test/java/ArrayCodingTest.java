import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
        int[] nums = new int[]{1,3,5,7};
        int[] result = productExceptSelf(nums);
        assertArrayEquals(result, new int[]{105,35,21,15});
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

    @Test
    @DisplayName("121. Best Time to Buy and Sell Stock")
    void maxProfit() {
        int[] prices = new int[]{7,1,5,3,6,4};
        int result = maxProfit(prices);
        assertEquals(result, 5);
    }

    private int maxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];

        for(int price : prices) {
            min = Math.min(min, price);
            max = Math.max(max, price-min);
        }
        return max;
    }

    @Test
    @DisplayName("234. Palindrome Linked List")
    void isPalindromeTest() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        boolean result = isPalindrome(head);
        assertTrue(result);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    private boolean isPalindrome(ListNode head) {

        Deque<Integer> deque = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            deque.add(node.val);
            node = node.next;
        }

        while (deque.size() > 1 && !deque.isEmpty()) {
            if (!deque.pollFirst().equals(deque.pollLast())) {
                return false;
            }
        }

        return true;

    }
    @Test
    @DisplayName("2. Merge Two Sorted Lists")
    void mergeTwoSortedListsTest() {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(5)));
        ArrayList<Integer> listNode = new ArrayList<>();
        listNode.add(mergeTwoSortedLists(l1, l2).val);
        ListNode expected = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))));

        assertEquals(listNode.toString(), expected.toString());
    }
    ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {

        if(list1 == null) return list2;
        if(list2 == null) return list1;

        if(list1.val < list2.val) {
            list1.next = mergeTwoSortedLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoSortedLists(list1, list2.next);
            return list2;
        }
    }

    @Test
    @DisplayName("206. Reverse Linked List")
    void reverseListTest() {
        ListNode listNode = reverseList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
        assertEquals(listNode.val, 4);
    }

    ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    @Test
    @DisplayName("24. Swap Nodes in Pairs")
    void swapPairsTest() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode result = swapPairs(head);
        assertEquals(result.val, 2);
    }

    ListNode swapPairs(ListNode head) {
        if(head != null && head.next != null) {
            ListNode node = head.next;
            head.next = swapPairs(head.next.next);
            node.next = head;
            return node;
        }
        return head;
    }
}
