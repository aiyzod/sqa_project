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
			r.output(c.choose(r.loadSchoolList("res/school.csv"), r.loadStudentList("res/student.csv"))); // 讀檔後選出正備取並輸出榜單
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 選出正備取
	public ArrayList<School> choose(ArrayList<School> schoolList, ArrayList<Student> studentList) {
		for (int i = 0; i < schoolList.size(); i++) {
			for (int j = 0; j < studentList.size(); j++) {
				if (Arrays.asList(studentList.get(j).getWant()).contains(schoolList.get(i).getId())
						&& studentList.get(j).getGrade() >= schoolList.get(i).getGrade()) { // 學生志願裡包含該學校且分數大於錄取門檻
					schoolList.get(i).getList().add(studentList.get(j)); // 新增學生至錄取清單
					studentListSort(schoolList.get(i).getList()); // 排序錄取清單
				}
			}
			selectStudent(schoolList.get(i)); // 將大於名額的學生依成績篩選
		}
		return schoolList;
	}

	// 依成績篩選學生
	public void selectStudent(School school) {
		int size = 0;
		int total = school.getQuota() + school.getReady(); // 正備取名額
		int count = 0;
		int temp = -1;
		for (int i = 0; i < school.getList().size(); i++) { // 算出不同分的學生人數
			if (temp != school.getList().get(i).getGrade()) {
				temp = school.getList().get(i).getGrade();
				size++;
			}
		}
		if (total < size) {
			temp = -1;
			for (int i = school.getList().size() - 1; i >= 0; i--) { // 從成績最低的學生開始篩選
				if (temp != school.getList().get(i).getGrade()) {
					temp = school.getList().get(i).getGrade();
					count++;
					if (count > size - total) { // 當刪除人數 > 清單人數 - 總名額則結束迴圈 ( 不同分計算 )
						break;
					}
				}
				school.getList().remove(i); // 移除最後一位的學生
			}
		}
	}

	// 錄取名單依成績排序
	public void studentListSort(ArrayList<Student> list) {
		Collections.sort(list, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o2.getGrade() - o1.getGrade(); // 依成績高到低排序
			}
		});
	}
}
