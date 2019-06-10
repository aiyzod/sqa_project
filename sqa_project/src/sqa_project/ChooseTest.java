package sqa_project;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ChooseTest {

	Choose c = new Choose();
	Readfile r = new Readfile();
	ArrayList<School> test;

	@Before
	public void setup() {
		c = new Choose();
		r = new Readfile();
		test = new ArrayList<School>();
		try {
			test = c.choose(r.loadSchoolList("res/school.csv"), r.loadStudentList("res/student.csv"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testChoose() throws FileNotFoundException { // 國立臺灣大學醫學系
		int[] s = { 108001, 108002, 108003, 108004, 108005, 108006 };
		int size = test.get(0).getList().size();
		assertEquals("001", test.get(0).getId());
		for (int i = 0; i < size; i++) {
			assertEquals(s[i], test.get(0).getList().get(i).getId());
		}
	}

	@Test
	public void testChoose1() throws FileNotFoundException { // 國立成功大學電機工程學系
		int[] s = { 108001, 108002, 108003, 108004, 108005, 108006 };
		int size = test.get(1).getList().size();
		assertEquals("002", test.get(1).getId());
		for (int i = 0; i < size; i++) {
			assertEquals(s[i], test.get(1).getList().get(i).getId());
		}
	}

	@Test
	public void testChoose2() throws FileNotFoundException { // 交通大學電子工程學系
		int[] s = { 108001, 108002, 108003, 108004, 108005, 108006 };
		int size = test.get(2).getList().size();
		assertEquals("003", test.get(2).getId());
		for (int i = 0; i < size; i++) {
			assertEquals(s[i], test.get(2).getList().get(i).getId());
		}
	}

	@Test
	public void testChoose3() throws FileNotFoundException { // 東吳大學法律學系
		int[] s = { 108001, 108002, 108003, 108004, 108005, 108006 };
		int size = test.get(3).getList().size();
		assertEquals("004", test.get(3).getId());
		for (int i = 0; i < size; i++) {
			assertEquals(s[i], test.get(3).getList().get(i).getId());
		}
	}

	@Test
	public void testChoose4() throws FileNotFoundException { // 逢甲大學資訊工程系
		int[] s = { 108001, 108002, 108003, 108004, 108005, 108006 };
		int size = test.get(4).getList().size();
		assertEquals("005", test.get(4).getId());
		for (int i = 0; i < size; i++) {
			assertEquals(s[i], test.get(4).getList().get(i).getId());
		}
	}

	@Test
	public void testChoose5() throws FileNotFoundException { // 東海大學建築系
		int[] s = { 108008, 108009, 108010 };
		int size = test.get(5).getList().size();
		assertEquals("006", test.get(5).getId());
		for (int i = 0; i < size; i++) {
			assertEquals(s[i], test.get(5).getList().get(i).getId());
		}
	}

	@Test
	public void testChoose6() throws FileNotFoundException { // 淡江大學會計系
		int[] s = { 108008, 108009, 108010 };
		int size = test.get(6).getList().size();
		assertEquals("007", test.get(6).getId());
		for (int i = 0; i < size; i++) {
			assertEquals(s[i], test.get(6).getList().get(i).getId());
		}
	}

	@Test
	public void testChoose7() throws FileNotFoundException { // 大葉大學多媒體數位系
		int[] s = { 108008, 108009 };
		int size = test.get(7).getList().size();
		assertEquals("008", test.get(7).getId());
		for (int i = 0; i < size; i++) {
			assertEquals(s[i], test.get(7).getList().get(i).getId());
		}
	}
}
