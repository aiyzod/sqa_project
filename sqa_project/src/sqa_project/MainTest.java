package sqa_project;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
	Main m = new Main();
	
	@Test
	public void testTest() {
		assertEquals(87, m.test());
	}

}
