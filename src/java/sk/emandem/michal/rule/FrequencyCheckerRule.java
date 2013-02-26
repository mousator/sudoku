/**
 * 
 */
package sk.emandem.michal.rule;

import sk.emandem.michal.INumSet;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public class FrequencyCheckerRule implements ISudokuRule {

	/* (non-Javadoc)
	 * @see sk.emandem.michal.rule.ISudokuRule#apply(sk.emandem.michal.INumSet[])
	 */
	@Override
	public boolean apply(INumSet[] numSets) {
		int[] freqs = computeFreq(numSets);
		boolean wasChange = false;
		for(int i=0;i<freqs.length;i++){
			if(freqs[i]==1){
				//remove all numbers from single num set which can have one number only
				wasChange |= cleanSingleNumSetHaving(i+1, numSets);
			}
		}
		return wasChange;
	}
	
	private boolean cleanSingleNumSetHaving(int num, INumSet[] nums){
		for(INumSet ns : nums){
			if(ns.hasNumbers(num) && ns.size()>1){
				ns.keepOnly(num);
				return true;
			}
		}
		return false;
	}
	
	private int[] computeFreq(INumSet[] nums){
		int[] freqs = new int[9];
		for(INumSet ns:nums){
			for(int n : ns.getNumbers()){
				freqs[n-1]++;
			}
		}
		return freqs;
	}

}
