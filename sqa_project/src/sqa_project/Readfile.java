package sqa_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Readfile {

	public static void main(String[] args) {
		Readfile r = new Readfile();
		try {
			r.loadStudentList();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public School[] loadSchoolList() throws FileNotFoundException {
		Scanner line = new Scanner(new File("res/school.csv"));
		String[] value = null;
		School[] school = new School[5];
		int i = 0;

		while (line.hasNextLine()) {
			value = line.nextLine().split(",");
			school[i] = new School();
			school[i].setId(Integer.parseInt(value[0]));
			school[i].setName(value[1]);
			school[i].setGrade(Integer.parseInt(value[3]));
			school[i].setQuota(Integer.parseInt(value[2]));
			i++;
		}
		for (i = 0; i < school.length; i++) {
			System.out.println(school[i].getId() + " " + school[i].getName() + " " + school[i].getQuota() + " "
					+ school[i].getGrade());
		}
		line.close();
		return school;
	}

	public Student[] loadStudentList() throws FileNotFoundException {
		Scanner line = new Scanner(new File("res/student.csv"));
		String[] value = null;
		Student[] student = new Student[10];
		int i = 0;
		while (line.hasNextLine()) {
			value = line.nextLine().split(",");
			student[i] = new Student();
			student[i].setId(Integer.parseInt(value[0]));
			student[i].setName(value[1]);
			student[i].setGrade(Integer.parseInt(value[2]));
			student[i].setWant(Arrays.copyOfRange(value, 3, 8));
			i++;
		}
		for (i = 0; i < student.length; i++) {
			System.out.println(student[i].getId() + " " + student[i].getName() + " " + student[i].getGrade());
			for(int j = 0;j < student[j].getWant().length;j++) {
				System.out.print(student[j].getWant()[j] + " ");
			}
			System.out.println();
		}
		line.close();
		return student;
	}

}
