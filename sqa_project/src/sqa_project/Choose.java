package sqa_project;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Choose {

	public static void main(String[] args) {
		Choose c = new Choose();
		Readfile r = new Readfile();
		try {
			c.choose(r.loadSchoolList(), r.loadStudentList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void choose(School[] school, Student[] student) {
		for (int i = 0; i < school.length; i++) {
			for(int j = 0;j < student.length; j++) {
				if(Arrays.asList(student[j].getWant()).contains(school[i].getId())) {
					if(student[j].getGrade() >= school[i].getGrade()) {
						System.out.println(student[j].getGrade() + " > " + school[i].getGrade());
						school[i].addList(student[j].getId());
					}
				}
			}
		}
		for (int i = 0; i < school.length; i++) {
			System.out.print(school[i].getList());
		}
	}

}
