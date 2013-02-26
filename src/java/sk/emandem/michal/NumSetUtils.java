/**
 * 
 */
package sk.emandem.michal;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public class NumSetUtils {

	public NumSet createNewFullSet(){
		NumSet numSet = new NumSet();
		numSet.addNumbers(Nums.FULLSET);
		return numSet;
	}
	
	public NumSet[] createNumSets(int[][] nums){
		NumSet[] numSets = new NumSet[nums.length];
		for(int i=0;i<nums.length;i++){
			numSets[i] = new NumSet();
			numSets[i].addNumbers(nums[i]);
		}
		return numSets;
	}
	
	public void printNumSets(INumSet[] numSets){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<numSets.length;i++){
			sb.append(numSets[i]);
		}
		System.out.println(sb.toString());
	}
	
	public void printSudoku(INumSet[][] sudoku){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<sudoku.length;i++){
			for(int j=0; j < sudoku[i].length; j++){
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public void prettyPrintSudoku(INumSet[][] sudoku){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<sudoku.length;i++){
			for(int j=0; j < sudoku[i].length; j++){
				if(sudoku[i][j].size()==1){
					sb.append(sudoku[i][j].getNumbers()[0]);
				} else {
					sb.append('x');
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
