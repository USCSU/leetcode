package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Garvin on 10/17/2014.
 */
public class CombinationSum {
    public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(candidates == null || candidates.length ==0) return res;
        ArrayList<Integer> array = new ArrayList<Integer>();
        sumHelper(candidates,target,res,array,0);
        return res;
    }
    // we can use node more than once
    public static void sumHelper(int[] candidates, int target, ArrayList<ArrayList<Integer>> res,ArrayList<Integer> array, int index){
        //base
        if(target == 0){
            res.add(new ArrayList<Integer>(array));
            return;
        }
        //error checking
        if(index>=candidates.length || target<0)
            return;


            //not taking current node
            sumHelper(candidates,target,res,array,index+1);
            //take current node
            array.add(candidates[index]);
            sumHelper(candidates, target - candidates[index], res, array, index);
            array.remove(array.size() - 1);

    }
    public static ArrayList<ArrayList<Integer>> combinationSumI(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(candidates == null || candidates.length ==0) return res;
        res = sumHelperI(candidates, target, 0, new ArrayList<Integer>());
        return res;
    }
    public static ArrayList<ArrayList<Integer>> sumHelperI(int[] candidates, int target, int index, ArrayList<Integer> array){
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        //error checking
        if(index == candidates.length || target<0)
            return res;
        //base
        if(target == 0){
            res.add(new ArrayList<Integer>(array));
            return res;
        }
        //recursive
        // take current node
        array.add(candidates[index]);
        res.addAll(sumHelperI(candidates, target - candidates[index], index, array));
        array.remove(array.size()-1);
        // not taking current node
        res.addAll(sumHelperI(candidates, target, index + 1, array));
        return res;
    }
    //duplicate nodes in the array
    public static ArrayList<ArrayList<Integer>> combinationSumWithDuplicateNode(int[] array, int target){
        HashSet<ArrayList<Integer>> res = new HashSet<ArrayList<Integer>>();
        if(array == null || array.length ==0) return new ArrayList<ArrayList<Integer>>(res);
//        Arrays.sort(array);
        res = combinationSumWithDuplicateNodeHelper(array,0,target,new ArrayList<Integer>());
        return new ArrayList<ArrayList<Integer>>(res);
    }
    //Complete knapsack
    public static HashSet<ArrayList<Integer>> combinationSumWithDuplicateNodeHelper(int[] array, int index, int target, ArrayList<Integer> result){
        HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        //error checking
        if(index == array.length || target < 0)
            return set;
        //base
        if(target == 0){
            set.add(new ArrayList<Integer>(result));
            return set;
        }
        //recursive
        //not taking current node
        set.addAll(combinationSumWithDuplicateNodeHelper(array, index + 1, target, result));
        //take current node
        result.add(array[index]);
//        System.out.println(result);
        set.addAll(combinationSumWithDuplicateNodeHelper(array, index, target - array[index], result));
        result.remove(result.size()-1);
        return set;
    }



    // can't use distinct node
    public static ArrayList<ArrayList<Integer>> combinationSumII(int[] array, int target){
        HashSet<ArrayList<Integer>> res = new HashSet<ArrayList<Integer>>();
        if(array == null || array.length==0) return new ArrayList<ArrayList<Integer>>(res);
        Arrays.sort(array);
        int size = 1<<array.length;
        for(int i =1;i<size;i++){
            ArrayList<Integer> temp = convertIntToArray(i,array);

            if(sumEqualTarget(temp,target))
                res.add(temp);
        }
        return new ArrayList<ArrayList<Integer>>(res);
    }
    public static boolean sumEqualTarget(ArrayList<Integer> array, int target){
        if(array == null) return false;
        int sum = 0;
        for(int x:array){
            sum+=x;
        }
        return sum==target;
    }
    public static ArrayList<Integer> convertIntToArray(int x, int[] array){
        ArrayList<Integer> res = new ArrayList<Integer>();
        int index = 0;
        for(int i = x; i>0;i>>=1){
            if((i&1)==1){
                res.add(array[index]);
            }
            index++;
        }
        return res;
    }
    //01 knapsacl
    public static ArrayList<ArrayList<Integer>> combinationSumIII(int[] array, int target){
        HashSet<ArrayList<Integer>> res = new HashSet<ArrayList<Integer>>();
        Arrays.sort(array);
        if(array  == null || array.length ==0) return new ArrayList<ArrayList<Integer>>(res);
        res = combinationSumIIIHelper(array,target,0,new ArrayList<Integer>());
        return new ArrayList<ArrayList<Integer>>(res);
    }
    public static HashSet<ArrayList<Integer>> combinationSumIIIHelper(int[] array, int target, int index, ArrayList<Integer> result){
        HashSet<ArrayList<Integer>> res = new HashSet<ArrayList<Integer>>();
        if(target == 0){
            res.add(new ArrayList<Integer>(result));
            return res;
        }
        if(index == array.length || target<0)
            return res;
        //take current node
        result.add(array[index]);
        res.addAll(combinationSumIIIHelper(array,target-array[index],index+1,result));
        result.remove(result.size()-1);
        //not take current node
        res.addAll(combinationSumIIIHelper(array,target,index+1,result));
        return res;
    }
    public static void main(String[] args){
        int[] candidates = {3,2,6,7};
        System.out.println(combinationSum(candidates,7)); // no duplicate number in the array
        System.out.println(combinationSumI(candidates, 7));
        System.out.println(combinationSumWithDuplicateNode(candidates, 7));
        /*1. Need to sort array if duplicate value existed
          2. HashMap is needed to remove duplicate values
          3. bit muniplation is needed after sorting, or lots of duplicates will be there.
        */
        int[] array = {10,1,2,7,6,1,5};
        System.out.println(combinationSumII(array, 8));
        System.out.println(combinationSumIII(array, 8));
    }
}
