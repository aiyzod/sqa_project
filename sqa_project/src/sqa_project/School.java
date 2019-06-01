package sqa_project;

import java.util.ArrayList;

public class School {
	private String id; // 校系代碼
	private String name; // 名稱
	private int grade; // 分數門檻
	private int quota; // 正取名額
	private int ready; // 備取名額
	private ArrayList<Student> list = new ArrayList<Student>(); // 錄取學生

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getReady() {
		return ready;
	}

	public void setReady(int ready) {
		this.ready = ready;
	}

	public ArrayList<Student> getList() {
		return list;
	}

	public void setList(ArrayList<Student> list) {
		this.list = list;
	}

	public void addList(Student student_id) { // 新增學生至錄取清單
		this.list.add(student_id);
	}
}
