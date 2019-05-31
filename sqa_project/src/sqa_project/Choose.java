package sqa_project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Choose {

	public static void main(String[] args) {
		Choose c = new Choose();
		Readfile r = new Readfile();
		try {
			r.output(c.choose(r.loadSchoolList(), r.loadStudentList()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 選出正備取
	public ArrayList<School> choose(ArrayList<School> schoolList, ArrayList<Student> studentList) {
		for (int i = 0; i < schoolList.size(); i++) {
			for (int j = 0; j < studentList.size(); j++) {
				if (Arrays.asList(studentList.get(j).getWant()).contains(schoolList.get(i).getId())) {
					if (studentList.get(j).getGrade() >= schoolList.get(i).getGrade()) {
						schoolList.get(i).getList().add(studentList.get(j));
						studentListSort(schoolList.get(i).getList());
					}
				}
			}
			selectStudent(schoolList.get(i));
		}
		for (int i = 0; i < schoolList.size(); i++) {
			for (int j = 0; j < schoolList.get(i).getList().size(); j++) {
				System.out.print(schoolList.get(i).getList().get(j).getGrade() + " ");
			}
			System.out.println();
		}
		return schoolList;
	}

	// 依成績篩選學生
	public void selectStudent(School school) {
		int size;
		int count = 0;
		int total = school.getQuota() + school.getReady();
		int temp = -1;
		for (int i = 0; i < school.getList().size(); i++) {
			if (temp != school.getList().get(i).getGrade()) {
				temp = school.getList().get(i).getGrade();
				count++;
			}
		}
		size = count;
		count = 0;
		if (total < size) {
			temp = -1;
			for (int i = school.getList().size() - 1; i >= 0; i--) {
				if (temp != school.getList().get(i).getGrade()) {
					temp = school.getList().get(i).getGrade();
					count++;
					if (count > size - total) {
						break;
					}
				}
				school.getList().remove(i);
			}
		}
	}

	// 錄取名單依成績排序
	public void studentListSort(ArrayList<Student> list) {
		Collections.sort(list, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o2.getGrade() - o1.getGrade();
			}
		});
	}
}
