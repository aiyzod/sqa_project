package sqa_project;

import java.io.FileNotFoundException;

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

		}
	}

}
