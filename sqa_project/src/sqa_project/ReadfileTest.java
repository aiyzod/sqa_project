package sqa_project;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ReadfileTest {
	Readfile r = new Readfile();
	Choose c = new Choose();
	ArrayList<School> schoolList = new ArrayList<School>();
	ArrayList<Student> studentList = new ArrayList<Student>();
	
	@Test
	public void testLoadSchoolList() throws FileNotFoundException { // 測試讀進的第一筆school資料
		schoolList = r.loadSchoolList("res/test/r_test_0.csv");
		assertEquals("001222", schoolList.get(0).getId());
		assertEquals("國立臺灣大學醫學系", schoolList.get(0).getName());
		assertEquals(75, schoolList.get(0).getGrade());
		assertEquals(1, schoolList.get(0).getQuota());
		assertEquals(2, schoolList.get(0).getReady());
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testLoadSchoolListException() throws FileNotFoundException { // 找不到資料拋出例外
		r.loadSchoolList("res/404.csv");
	}
	
	@Test
	public void testLoadStudentList() throws FileNotFoundException { // 測試讀進的第一筆student資料
		studentList = r.loadStudentList("res/test/r_test_1.csv");
		assertEquals(1, studentList.get(0).getId());
		assertEquals("陳彥甫", studentList.get(0).getName());
		assertEquals(73, studentList.get(0).getGrade());
		assertEquals("001222", studentList.get(0).getWant()[0]);
		assertEquals("001242", studentList.get(0).getWant()[1]);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testLoadStudentListException() throws FileNotFoundException { // 找不到資料拋出例外
		r.loadStudentList("res/404.csv");
	}


	@Test
	public void testOutput() throws IOException {
		schoolList = r.loadSchoolList("res/test/r_test_0.csv");
		studentList = r.loadStudentList("res/test/r_test_1.csv");
		r.output(c.choose(schoolList, studentList));
		
		File f = new File("res/output.txt");
		File f_test = new File("res/test/output.txt");

		assertEquals(true, FileUtils.contentEqualsIgnoreEOL(f, f_test, "utf-8")); // 若兩個.TXT檔名、內容皆相同則回傳true
	}

}
