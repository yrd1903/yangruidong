package com.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class LeetCode {
	
	public static void main(String[] args){
		LeetCode leetCode = new LeetCode();
		System.out.println(leetCode.add(-5, 9));
	}
	
	
	//leetcode第198      动态规划
    public int rob(int[] nums) {
        if(nums==null || nums.length==0){
        	return 0;
        }
        if(nums.length==1){
        	return nums[0];
        }
        if(nums.length==2){
        	return Math.max(nums[0], nums[1]);
        }
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for(int i=2;i<nums.length;i++){
        	dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
    	
    	return dp[nums.length-1];
    }
    
    
    //leetcode   213
    public int rob2(int[] nums) {
        if(nums==null || nums.length==0){
        	return 0;
        }
        if(nums.length==1){
        	return nums[0];
        }
        if(nums.length==2){
        	return Math.max(nums[0], nums[1]);
        }
        
        int[] dp = new int[nums.length-1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for(int i=2;i<nums.length-1;i++){
        	dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        
        int[] dp2 = new int[nums.length-1];
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1], nums[2]);
        
        for(int i=3;i<nums.length;i++){
        	dp2[i-1] = Math.max(dp2[i-2], dp2[i-3]+nums[i]);
        }
    	
    	return Math.max(dp[nums.length-2], dp2[nums.length-2]);
    }
    
    
    
    //leetcode第107
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }
    
    
    //不使用+  -  x  /  的加法
    public int add(int a, int b){
    	int sum = a^b;
    	int carry = (a&b)<<1;
    	int sumTem = 0;
    	while(carry!=0){
    		sumTem = sum^carry;
    		carry = (sum&carry)<<1;
    		sum = sumTem;
    	}
    	
    	return sum;
    }

}
