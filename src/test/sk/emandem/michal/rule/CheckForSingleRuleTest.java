/**
 * 
 */
package sk.emandem.michal.rule;

import static sk.emandem.michal.Nums.FULLSET;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import sk.emandem.michal.NumSet;
import sk.emandem.michal.NumSetUtils;

/**
 * @author Michal Antolik (michal@emandem.sk)
 *
 */
public class CheckForSingleRuleTest {

	@Test
	public void testSingleNumberRemoval() {
		NumSetUtils nsUtils = new NumSetUtils();
		int[][] sampleSet = new int[][]{FULLSET,FULLSET,FULLSET,FULLSET,FULLSET,FULLSET,{2,3},FULLSET,{1}};
		int[] ns2to9 = new int[]{2,3,4,5,6,7,8,9};
		NumSet[] numSets = nsUtils.createNumSets(sampleSet);
		
		CheckForSingleRule rule = new CheckForSingleRule();
		nsUtils.printNumSets(numSets);
		Assert.assertTrue(rule.apply(numSets));
		nsUtils.printNumSets(numSets);
		Assert.assertTrue(Arrays.equals(numSets, nsUtils.createNumSets(new int[][]{ns2to9,ns2to9,ns2to9,ns2to9,ns2to9,ns2to9,{2,3},ns2to9,{1}})));
	}

	@Test
	public void testTwinNumberRemoval() {
		NumSetUtils nsUtils = new NumSetUtils();
		int[][] sampleSet = new int[][]{FULLSET,FULLSET,{2,3},FULLSET,FULLSET,FULLSET,{2,3},FULLSET,{1}};
		int[] ns4to9 = new int[]{4,5,6,7,8,9};
		NumSet[] numSets = nsUtils.createNumSets(sampleSet);
		
		CheckForSingleRule rule = new CheckForSingleRule();
		nsUtils.printNumSets(numSets);
		Assert.assertTrue(rule.apply(numSets));
		nsUtils.printNumSets(numSets);
		Assert.assertTrue(Arrays.equals(numSets, nsUtils.createNumSets(new int[][]{ns4to9,ns4to9,{2,3},ns4to9,ns4to9,ns4to9,{2,3},ns4to9,{1}})));
	}
	
	@Test
	public void testTripletNumberRemoval() {
		NumSetUtils nsUtils = new NumSetUtils();
		int[][] sampleSet = new int[][]{FULLSET,FULLSET,{3,4,5},FULLSET,FULLSET,{3,4,5},{3,4,5},FULLSET,{1}};
		int[] ns2to9 = new int[]{2,6,7,8,9};
		int[] ns2to8 = new int[]{2,6,7,8};
		int[] ns2to7 = new int[]{2,6,7};
		NumSet[] numSets = nsUtils.createNumSets(sampleSet);
		
		CheckForSingleRule rule = new CheckForSingleRule();
		nsUtils.printNumSets(numSets);
		Assert.assertTrue(rule.apply(numSets));
		nsUtils.printNumSets(numSets);
		Assert.assertTrue(Arrays.equals(numSets, nsUtils.createNumSets(new int[][]{ns2to9,ns2to9,{3,4,5},ns2to9,ns2to9,{3,4,5},{3,4,5},ns2to9,{1}})));
		
		// keep 9 in first vector
		numSets[0].keepOnly(9);
		nsUtils.printNumSets(numSets);
		Assert.assertTrue(rule.apply(numSets));
		Assert.assertTrue(Arrays.equals(numSets, nsUtils.createNumSets(new int[][]{{9},ns2to8,{3,4,5},ns2to8,ns2to8,{3,4,5},{3,4,5},ns2to8,{1}})));
		nsUtils.printNumSets(numSets);
		
		// keep 9 in first vector
		numSets[2].keepOnly(4);
		nsUtils.printNumSets(numSets);
		Assert.assertTrue(rule.apply(numSets));
		Assert.assertTrue(Arrays.equals(numSets, nsUtils.createNumSets(new int[][]{{9},ns2to8,{4},ns2to8,ns2to8,{3,5},{3,5},ns2to8,{1}})));
		nsUtils.printNumSets(numSets);
		
		// keep 8 in 8. vector
		numSets[7].keepOnly(8);
		nsUtils.printNumSets(numSets);
		Assert.assertTrue(rule.apply(numSets));
		Assert.assertTrue(Arrays.equals(numSets, nsUtils.createNumSets(new int[][]{{9},ns2to7,{4},ns2to7,ns2to7,{3,5},{3,5},{8},{1}})));
		nsUtils.printNumSets(numSets);
	}
	
}
