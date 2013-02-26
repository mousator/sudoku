/**
 * 
 */
package sk.emandem.michal.rule;

import sk.emandem.michal.INumSet;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public class CheckForSingleRule implements ISudokuRule {

	@Override
	public boolean apply(INumSet[] numSets) {
		boolean wasChange = false;
		boolean singleRoundChange;
		do {
			singleRoundChange=false;
			for(int i=0;i<numSets.length;i++){
				if(numSets[i].size()==1){
					//remove that one number from other numSets
					singleRoundChange |= remove(numSets[i], numSets);
				} else if(numSets[i].size()<5){
					int totalEqual = countEqualNumSets(numSets[i], i+1, numSets);
					if(totalEqual==numSets[i].size()-1){
						singleRoundChange |= remove(numSets[i], numSets);
					}
				}
			}
			wasChange|=singleRoundChange;
		} while(singleRoundChange);
		
		return wasChange;
	}

	private boolean remove(INumSet numSet, INumSet[] numSets){
		boolean wasChange = false;
		for(int i=0; i<numSets.length; i++){
			if(numSets[i].equals(numSet)){
				//do not remove form sets of same numbers
				continue;
			}
			int[] nums = numSet.getNumbers();
			if(numSets[i].hasNumbers(nums)){
				numSets[i].removeNumbers(nums);
				wasChange=true;
			}
		}
		return wasChange;
	}
	
	private int countEqualNumSets(INumSet numSet, int fromIndex, INumSet[] numSets){
		// traversing numSets array from index 'fromIndex' till the end checking if there
		// is another numSet equal to 'numSet'
		int total=0;
		for(int i=fromIndex; i<numSets.length; i++){
			if(numSets[i].equals(numSet)){
				total++;
			}
		}
		return total;
	}

}
