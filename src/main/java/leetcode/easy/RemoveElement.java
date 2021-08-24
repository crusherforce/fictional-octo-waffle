package leetcode.easy;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int prev=0,cur=0;
        while(cur<nums.length) {
            if (nums[cur] != val) {
                int temp = nums[prev];
                nums[prev] = nums[cur];
                nums[cur] = temp;
                prev++;
            }
            cur++;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] test;
        test = new int[] {0,1,2,2,3,0,4,2};
        RemoveElement r = new RemoveElement();
        r.removeElement(test,2);
    }
}
