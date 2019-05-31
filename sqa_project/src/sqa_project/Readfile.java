package sqa_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Readfile {

	// 讀取學校資料
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

	// 讀取學生資料
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

	// 產生榜單
	public void output(ArrayList<School> schoolList) throws IOException {
		int count = 0;
		int temp = -1;
		int index = 0;
		File write = new File("res/output.txt");
		write.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter("res/output.txt"));
		for (int i = 0; i < schoolList.size(); i++) {
			bw.write(schoolList.get(i).getId() + " " + schoolList.get(i).getName());
			bw.newLine();
			bw.newLine();
			for (int j = 0; j < schoolList.get(i).getList().size(); j++) {
				if (temp != schoolList.get(i).getList().get(j).getGrade()) {
					temp = schoolList.get(i).getList().get(j).getGrade();
					count++;
					index++;
				}
				if (count <= schoolList.get(i).getQuota()) {
					bw.write("正取");
				} else if (count == schoolList.get(i).getQuota() + 1) {
					index = 1;
					bw.write("備取");
				} else {
					bw.write("備取");
				}
				bw.write(index + " " + schoolList.get(i).getList().get(j).getId() + " "
						+ schoolList.get(i).getList().get(j).getName());
				bw.newLine();
			}
			bw.newLine();
			index = 0;
			count = 0;
			temp = -1;
		}
		bw.flush();
		bw.close();
	}
}
