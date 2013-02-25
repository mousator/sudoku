/**
 * 
 */
package sk.emandem.michal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.BitSet;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public class Solver {

	BitSet[][] sudoku = new BitSet[9][9];
	
	public Solver(String fileName) throws IOException {
		init(fileName);
	}
	
	public void solve(){
		boolean wasChange;
		do {
			boolean rowsColsChange = checkRowsAndCols();
			boolean squareChange = checkSquares();
			wasChange = rowsColsChange || squareChange;
		} while(wasChange);
	}
	
	private boolean checkSquares(){
		boolean wasChange = false;
		for(int i=0; i<3;i++){
			for(int j=0; j < 3; j++){
				for(int k=0; k<3; k++){
					for(int l=0; l<3; l++){
						//small square
						BitSet pointer = sudoku[i*3+k][j*3+l];
						if(pointer.cardinality()==1){
							//remove value from small square except pointer
							wasChange = removeValueFromSmallSquare(i,j,findFirstValue(i*3+k, j*3+l), pointer);
						}
					}
				}				
			}
		}
		return wasChange;
	}
	
	private boolean removeValueFromSmallSquare(int squareRow, int squareCol, int val, BitSet pointer){
		boolean wasChange = false;
		for(int k=0; k<3; k++){
			for(int l=0; l<3; l++){
				BitSet vec = sudoku[squareRow*3+k][squareCol*3+l];
				if(vec!=pointer){
					if(vec.get(val)){
						vec.set(val, false);
						wasChange = true;
					}
				}
			}
		}
		return wasChange;
	}
	
	private boolean checkRowsAndCols(){
		boolean wasChange = false;
		for(int i=0; i<sudoku.length;i++){
			for(int j=0; j < sudoku[i].length; j++){
				if(sudoku[i][j].cardinality()==1){
					int singleValue = findFirstValue(i,j);
					//System.out.println("Removing all " + sudoku[i][j][0] + " from line " + i + " and column " + j);
					for(int k=0; k < 9; k++){
						if(k!=j){
							// remove singleValue from entire column
							wasChange = remove(i, k, singleValue);
						}
					}
					for(int k=0; k < 9; k++){
						if(k!=i){
							// remove singleValue from entire row
							wasChange = remove(k, j, singleValue);
						}
					}
				}
			}
		}
		return wasChange;
	}
	
	private int findFirstValue(int row, int col){
		int index = sudoku[row][col].nextSetBit(0);
		return index;
	}
	
	private boolean remove(int row, int col, int val){
		if(sudoku[row][col].get(val)){
			sudoku[row][col].set(val, false);
			return true;
		}
		return false;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Solver s = new Solver("medium.sudoku");
		s.prettyPrintSudoku();
		s.printSudoku();
		s.solve();
		s.printSudoku();
		s.prettyPrintSudoku();
	}
	
	public void printSudoku(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<sudoku.length;i++){
			for(int j=0; j < sudoku[i].length; j++){
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public void prettyPrintSudoku(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<sudoku.length;i++){
			for(int j=0; j < sudoku[i].length; j++){
				if(sudoku[i][j].cardinality()==1){
					sb.append(findFirstValue(i, j));
				} else {
					sb.append('x');
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
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
				BitSet vec = new BitSet(9);
				if(c=='x'){
					vec.set(0, 8, true);
				} else {
					vec.set(c-'1');
				}
				sudoku[lineNum][col] = vec;
			}
		}
	}

}
