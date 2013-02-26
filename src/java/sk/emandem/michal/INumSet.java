/**
 * 
 */
package sk.emandem.michal;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public interface INumSet {

	public void addNumbers(int ... nums);
	
	public boolean hasNumbers(int ... nums);

	public void removeNumbers(int ... nums);
	
	public int[] getNumbers();
	
	public void keepOnly(int ... nums);
	
	public int size();
}
