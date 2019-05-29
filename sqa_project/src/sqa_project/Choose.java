package sqa_project;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
			for (int j = 0; j < studentList.size(); j++) {
				if (Arrays.asList(studentList.get(j).getWant()).contains(schoolList.get(i).getId())) {
					if (studentList.get(j).getGrade() >= schoolList.get(i).getGrade()) {
						checkQuota(schoolList.get(i), studentList.get(j));
						studentListSort(schoolList.get(i).getList());
					}
				}
			}
		}
		for (int i = 0; i < schoolList.size(); i++) {
			for (int j = 0; j < schoolList.get(i).getList().size(); j++) {
				System.out.print(schoolList.get(i).getList().get(j).getId() + " ");
			}
			System.out.println();
		}
	}

	public void checkQuota(School school, Student student) {
		int size = school.getList().size() - 1;
		int grade;
		if (size == -1) {
			System.out.println("--------0--------");
			school.getList().add(student);
			return;
		}
		grade = school.getList().get(size).getGrade();
		if (size + 1 == school.getQuota_new() + school.getReady_new()) {
			if (grade == student.getGrade()) {
				System.out.println("--------1--------");
				school.setQuota_new(school.getQuota_new() + 1);
				school.getList().add(student);
			} else if (student.getGrade() > grade) {
				System.out.println(grade + "--------2--------" + student.getGrade());
				school.getList().set(size, student);
			} else {
				System.out.println("--------3--------");
				return;
			}
		} else if (size + 1 < school.getQuota_new()) {
			System.out.println(size + "--------4--------" + school.getQuota_new());
			school.getList().add(student);
		}
	}

	public void studentListSort(ArrayList<Student> list) {
		Collections.sort(list, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o2.getGrade() - o1.getGrade();
			}
		});
	}
}
