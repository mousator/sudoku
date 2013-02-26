/**
 * 
 */
package sk.emandem.michal;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public class NumSetTest {

	@Test
	public void testExtremeCases() {
		NumSet numSet = new NumSet();
		Assert.assertTrue(numSet.hasNumbers());
		
		numSet.addNumbers(1,2,3,4,5,6,7,8,9);
		Assert.assertTrue(numSet.hasNumbers(1,2,3,4,5,6,7,8,9));

		//do not allow save out of bound numbers
		try {
			numSet.addNumbers(-1);
			Assert.fail();
		} catch (RuntimeException e){};
		try {
			numSet.addNumbers(10);
			Assert.fail();
		} catch (RuntimeException e){};
	}

	@Test
	public void testDuplicates(){
		NumSet numSet = new NumSet();

		Assert.assertTrue(numSet.size()==0);
		
		numSet.addNumbers(1);
		Assert.assertTrue(numSet.hasNumbers(1));
		Assert.assertTrue(numSet.size()==1);
		
		//make duplicates
		numSet.addNumbers(1);
		Assert.assertTrue(numSet.hasNumbers(1));
		Assert.assertTrue(numSet.size()==1);
	}
	
	@Test
	public void testAddingRemoving(){
		NumSet numSet = new NumSet();

		Assert.assertTrue(numSet.size()==0);
		
		numSet.addNumbers(1,2,3,4);
		Assert.assertTrue(numSet.hasNumbers(1,2,3,4));
		Assert.assertTrue(numSet.size()==4);
		
		//remove two
		numSet.removeNumbers(1,3);
		Assert.assertTrue(numSet.hasNumbers(2,4));
		Assert.assertTrue(numSet.size()==2);
		
		//ignore non existence
		numSet.removeNumbers(9,0);
		Assert.assertTrue(numSet.size()==2);
		
		// keep some only
		numSet.keepOnly(2,5);
		Assert.assertTrue(numSet.hasNumbers(2,5));
		Assert.assertTrue(numSet.size()==2);
	}
	
	@Test
	public void testGetNumbers(){
		NumSet numSet = new NumSet();
		Assert.assertTrue(numSet.getNumbers().length==0);
		
		numSet.addNumbers(1,5,9);
		Assert.assertTrue(Arrays.equals(numSet.getNumbers(),new int[]{1,5,9}));
		
		numSet.removeNumbers(5);
		Assert.assertTrue(Arrays.equals(numSet.getNumbers(),new int[]{1,9}));
	}
	
	@Test
	public void testEquals(){
		NumSet numSet1 = new NumSet();
		NumSet numSet2 = new NumSet();
		
		//empty are equal
		Assert.assertTrue(numSet1.equals(numSet2));
		
		//full are equal
		numSet1.addNumbers(Nums.FULLSET);
		numSet2.addNumbers(Nums.FULLSET);
		Assert.assertTrue(numSet1.equals(numSet2));
		
		//remove sth
		numSet1.removeNumbers(1,3,6);
		Assert.assertFalse(numSet1.equals(numSet2));
	}
}
