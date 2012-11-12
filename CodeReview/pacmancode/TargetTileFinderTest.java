package pacmanprogram;

import static org.junit.Assert.*;

import org.junit.Test;

public class TargetTileFinderTest {

	final static double epislonx = 1e-8; // used for testing
	double pacmanSpeed = 1;
	@Test
	
	//the getBlinkyChaseTargetTest() should return an array
	//consisting the two variables
	public void getBlinkyChaseTargetTest() {
		double[] temp= TargetTileFinder.getBlinkyChaseTarget(100,100);
		double[] temp2={100,100};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	
	//the getPinkyChaseTargetTest1() should return an array
	@Test
	public void getPinkyChaseTargetTest1() {
		double[] temp= TargetTileFinder.getPinkyChaseTarget(100,100,1,2);
		double[] temp2={100,164};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getPinkyChaseTargetTest2() {
		double[] temp= TargetTileFinder.getPinkyChaseTarget(100,100,-1,2);
		double[] temp2={100,164};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getPinkyChaseTargetTest3() {
		double[] temp= TargetTileFinder.getPinkyChaseTarget(100,1,2,1);
		double[] temp2={100,65};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getPinkyChaseTargetTest4() {
		double[] temp= TargetTileFinder.getPinkyChaseTarget(100,100,2,-1);
		double[] temp2={36,36};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	//the getInkyChaseTarget() should return an array
	@Test
	public void getInkyChaseTargetTest1() {
		double[] temp= TargetTileFinder.getInkyChaseTarget(100,100,1,5,50,50);
		double[] temp2={150,214};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getInkyChaseTargetTest2() {
		double[] temp= TargetTileFinder.getInkyChaseTarget(100,100,-1,5,50,50);
		double[] temp2={150,214};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getInkyChaseTargetTest3() {
		double[] temp= TargetTileFinder.getInkyChaseTarget(100,100,5,-1,50,50);
		double[] temp2={86,86};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getInkyChaseTargetTest4() {
		double[] temp= TargetTileFinder.getInkyChaseTarget(100,100,5,1,50,50);
		double[] temp2={150,214};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getInkyChaseTargetTest5() {
		double[] temp= TargetTileFinder.getInkyChaseTarget(100,100,0,0,50,50);
		double[] temp2={214,214};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	//getClydeChaseTarget()
	
	@Test
	public void getClydeChaseTargetTest1() {
		double[] temp= TargetTileFinder.getClydeChaseTarget(100,100,100,100);
		double[] temp2={0,544};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getClydeChaseTargetTest2() {
		double[] temp= TargetTileFinder.getClydeChaseTarget(0,0,100,100);
		double[] temp2={0,0};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	//getBlinkyScatterTargetTest
	@Test
	public void getBlinkyScatterTargetTest() {
		double[] temp= TargetTileFinder.getBlinkyScatterTarget();
		double[] temp2={400,0};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getPinkyScatterTargetTest() {
		double[] temp= TargetTileFinder.getPinkyScatterTarget();
		double[] temp2={48,0};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
	@Test
	public void getInkyScatterTargetTest() {
		double[] temp= TargetTileFinder.getInkyScatterTarget();
		double[] temp2={432,544};
		for (int i=0;i<temp.length;i++){
		assertEquals(temp[i],temp2[i],epislonx);}
	}
	
}
