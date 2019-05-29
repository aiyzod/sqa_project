package sqa_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Readfile {

	public ArrayList<School> loadSchoolList() throws FileNotFoundException {
		Scanner line = new Scanner(new File("res/school.csv"));
		String[] value = null;
		ArrayList<School> schoolList = new ArrayList<School>();

		while (line.hasNextLine()) {
			value = line.nextLine().split(",");
			School school = new School();
			school.setId(value[0]);
			school.setName(value[1]);
			school.setGrade(Integer.parseInt(value[2]));
			school.setQuota(Integer.parseInt(value[3]));
			school.setReady(Integer.parseInt(value[4]));
			schoolList.add(school);
		}
		line.close();
		return schoolList;
	}

	public ArrayList<Student> loadStudentList() throws FileNotFoundException {
		Scanner line = new Scanner(new File("res/student.csv"));
		String[] value = null;
		ArrayList<Student> studentList = new ArrayList<Student>();
		while (line.hasNextLine()) {
			value = line.nextLine().split(",");
			Student student = new Student();
			student.setId(Integer.parseInt(value[0]));
			student.setName(value[1]);
			student.setGrade(Integer.parseInt(value[2]));
			student.setWant(Arrays.copyOfRange(value, 3, 8));
			studentList.add(student);
		}
		line.close();
		return studentList;
	}

}
