/**
 * 
 */
package sk.emandem.michal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sk.emandem.michal.rule.CheckForSingleRule;
import sk.emandem.michal.rule.FrequencyCheckerRule;
import sk.emandem.michal.rule.ISudokuRule;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public class Solver {

	INumSet[][] sudoku = new INumSet[9][9];
	
	public Solver(String fileName) throws IOException {
		init(fileName);
	}
	
	public void solve(){
		ISudokuRule[] rules = new ISudokuRule[]{new CheckForSingleRule(), new FrequencyCheckerRule()};
		boolean wasChange;
		do {
			wasChange = false;
			//rows
			for(int i=0;i<sudoku.length;i++){
				INumSet[] sudokuPart = sudoku[i];
				wasChange |= applyRules(rules, sudokuPart);
			}
			//cols
			for(int i=0;i<sudoku.length;i++){
				INumSet[] sudokuPart = new INumSet[]{
						sudoku[0][i],
						sudoku[1][i],
						sudoku[2][i],
						sudoku[3][i],
						sudoku[4][i],
						sudoku[5][i],
						sudoku[6][i],
						sudoku[7][i],
						sudoku[8][i]};
				wasChange |= applyRules(rules, sudokuPart);
			}
			//cubes
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					INumSet[] sudokuPart = new INumSet[]{
							sudoku[i*3][j*3],sudoku[i*3][j*3+1],sudoku[i*3][j*3+2],
							sudoku[i*3+1][j*3],sudoku[i*3+1][j*3+1],sudoku[i*3+1][j*3+2],
							sudoku[i*3+2][j*3],sudoku[i*3+2][j*3+1],sudoku[i*3+2][j*3+2],};
					wasChange |= applyRules(rules, sudokuPart);
				}
			}
		} while(wasChange);
	}
	
	private boolean applyRules(ISudokuRule[] rules, INumSet[] sudokuPart){
		boolean wasChange = false;
		for(ISudokuRule rule : rules){
			wasChange |= rule.apply(sudokuPart);
		}
		return wasChange;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		NumSetUtils nsUtils = new NumSetUtils();
		Solver s = new Solver("medium.sudoku");
		nsUtils.prettyPrintSudoku(s.sudoku);
		s.solve();
		nsUtils.prettyPrintSudoku(s.sudoku);
		nsUtils.printSudoku(s.sudoku);
		
		long delta = System.currentTimeMillis()-start;
		
		System.out.println();
		System.out.println("Solved in " + ((double)delta)/1000.0 + " seconds");
	}
	
	
	
	
	private void init(String name) throws IOException{
		InputStream is = Solver.class.getClassLoader().getResourceAsStream(name);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		int lineNum=-1;
		while((line=reader.readLine())!=null){
			lineNum++;
			for(int col=0; col < line.length(); col++){
				char c=line.charAt(col);
				INumSet numSet = new NumSet();
				if(c=='x'){
					numSet.addNumbers(Nums.FULLSET);
				} else {
					numSet.addNumbers(c-'0');
				}
				sudoku[lineNum][col] = numSet;
			}
		}
	}

}
