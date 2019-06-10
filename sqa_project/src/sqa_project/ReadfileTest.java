package sqa_project;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ReadfileTest {
	Readfile r = new Readfile();
	Choose c = new Choose();
	ArrayList<School> schoolList = new ArrayList<School>();
	ArrayList<Student> studentList = new ArrayList<Student>();
	
	@Test
	public void testLoadSchoolList() throws Exception { // 測試讀進的第一筆school資料
		schoolList = r.loadSchoolList("res/test/r_test_0.csv");
		assertEquals("001", schoolList.get(0).getId());
		assertEquals("國立臺灣大學醫學系", schoolList.get(0).getName());
		assertEquals(90, schoolList.get(0).getGrade());
		assertEquals(2, schoolList.get(0).getQuota());
		assertEquals(1, schoolList.get(0).getReady());
	}
	
	@Test(expected = Exception.class)
	public void testLoadSchoolListException() throws Exception { // 找不到資料拋出例外
		r.loadSchoolList("res/404.csv");
	}
	
	@Test(expected = Exception.class)
	public void testLoadSchoolListExceptionMax() throws Exception { // 成績大於範圍拋出例外
		r.loadSchoolList("res/test/r_test_school_max+.csv");
	}
	
	@Test(expected = Exception.class)
	public void testLoadSchoolListExceptionMin() throws Exception { // 成績小於範圍拋出例外
		r.loadSchoolList("res/test/r_test_school_min-.csv");
	}
	
	@Test
	public void testLoadStudentList() throws Exception { // 測試讀進的第一筆student資料
		studentList = r.loadStudentList("res/test/r_test_1.csv");
		assertEquals(108001, studentList.get(0).getId());
		assertEquals("測試1號", studentList.get(0).getName());
		assertEquals(100, studentList.get(0).getGrade());
		assertEquals("001", studentList.get(0).getWant()[0]);
		assertEquals("002", studentList.get(0).getWant()[1]);
		assertEquals("003", studentList.get(0).getWant()[2]);
		assertEquals("004", studentList.get(0).getWant()[3]);
		assertEquals("005", studentList.get(0).getWant()[4]);
	}
	
	@Test(expected = Exception.class)
	public void testLoadStudentListException() throws Exception { // 找不到資料拋出例外
		r.loadStudentList("res/404.csv");
	}
	
	@Test(expected = Exception.class)
	public void testLoadStudentListExceptionMax() throws Exception { // 成績大於範圍拋出例外
		r.loadStudentList("res/test/r_test_student_max+.csv");
	}

	@Test(expected = Exception.class)
	public void testLoadStudentListExceptionMin() throws Exception { // 成績小於範圍拋出例外
		r.loadStudentList("res/test/r_test_student_min-.csv");
	}

	@Test
	public void testOutput() throws Exception {
		schoolList = r.loadSchoolList("res/test/r_test_0.csv");
		studentList = r.loadStudentList("res/test/r_test_1.csv");
		r.output(c.choose(schoolList, studentList), "res/test/output.txt");
		
		File f = new File("res/test/output.txt");
		File f_test = new File("res/test/output_test/output.txt");
		assertEquals(true, FileUtils.contentEqualsIgnoreEOL(f, f_test, "utf-8")); // 若兩個.TXT檔名、內容皆相同則回傳true
	}

}
