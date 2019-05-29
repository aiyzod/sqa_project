package sqa_project;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

	public void choose(ArrayList<School> schoolList, ArrayList<Student> studentList) {
		for (int i = 0; i < schoolList.size(); i++) {
			for(int j = 0;j < studentList.size(); j++) {
				if(Arrays.asList(studentList.get(j).getWant()).contains(schoolList.get(i).getId())) {
					if(studentList.get(j).getGrade() >= schoolList.get(i).getGrade()) {
						System.out.println(studentList.get(j).getGrade() + " > " + schoolList.get(i).getGrade());
						
						schoolList.get(i).addList(studentList.get(j).getId());
					}
				}
			}
		}
		for (int i = 0; i < schoolList.size(); i++) {
			System.out.println(schoolList.get(i).getList());
		}
	}

}
