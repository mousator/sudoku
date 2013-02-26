/**
 * 
 */
package sk.emandem.michal.rule;

import sk.emandem.michal.INumSet;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public interface ISudokuRule {

	/**
	 * 
	 * @param sudokuMatrix
	 * @return true if matrix was changed
	 */
	public boolean apply(INumSet[] numSets);
}
