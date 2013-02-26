/**
 * 
 */
package sk.emandem.michal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public class NumSet implements INumSet {

	private Set<Integer> nums = new HashSet<>(10); 
	
	public NumSet() {}
	public NumSet(int ... nums) {this.addNumbers(nums);}
	
	/* (non-Javadoc)
	 * @see sk.emandem.michal.INumSet#hasNumber(int)
	 */
	@Override
	public boolean hasNumbers(int ... nums) {
		boolean res = true;
		for(int num:nums)
			res &= this.nums.contains(num);
		return res;
	}

	@Override
	public void addNumbers(int ... nums) {
		for(int num:nums){
			if(num < 1 || num > 9){throw new RuntimeException("number out of bounds");}
			this.nums.add(num);
		}
	}

	@Override
	public void removeNumbers(int ... nums) {
		for(int num:nums)
			this.nums.remove(num);
	}

	@Override
	public void keepOnly(int... nums) {
		this.nums = new HashSet<>(10);
		this.addNumbers(nums);
	}

	@Override
	public int size() {
		return nums.size();
	}

	@Override
	public int[] getNumbers() {
		Integer[] numsArr = nums.toArray(new Integer[nums.size()]);
		int[] res = new int[numsArr.length];
		for(int i=0;i<numsArr.length;i++){
			res[i]=numsArr[i];
		}
		Arrays.sort(res);
		return res;
	}
	
	@Override
	public String toString() {
		// short version
		//return nums.toString();
		
		//long version
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=1; i<=9;i++){
			sb.append(nums.contains(i)?i:" ");
			if(i<9){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof NumSet)){
			return super.equals(obj);
		}
		NumSet ns2 = (NumSet)obj;
		return nums.equals(ns2.nums);
	}
	
}
