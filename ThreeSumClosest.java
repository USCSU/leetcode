package LeetCode;

import java.util.Arrays;

/**
 * Created by Garvin on 9/4/2014.
 */
public class ThreeSumClosest {
    public static int threeSumClosest(int[] num, int target) {
        int res = 0;
        int min = Integer.MAX_VALUE;
        Arrays.sort(num);
        for(int i =0;i<num.length;i++){
            int low = i+1, high = num.length -1;
            while(low<high){
                int sum = num[i]+num[low]+num[high];
                if(sum == target){
                    return target;
                }else if(sum > target){
                    if(min > Math.abs(sum - target)){
                        min = Math.abs(sum - target);
                        res = sum;
                    }
                    high--;
                }else{
                    if(min > Math.abs(sum - target)){
                        min = Math.abs(sum - target);
                        res = sum;
                    }
                    low++;
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[] num = {-1, 2, 1, -4};
        System.out.println(threeSumClosest(num,1));
    }
}
