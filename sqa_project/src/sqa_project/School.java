package sqa_project;

import java.util.ArrayList;

public class School {
	private String id; // 校系代碼
	private String name; // 名稱
	private int grade; // 分數門檻
	private int quota; // 名額
	private int ready;
	private ArrayList<Student> list = new ArrayList<Student>(); // 錄取學生
	private int quota_new;
	private int ready_new;

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
		this.setQuota_new(quota);
	}

	public int getReady() {
		return ready;
	}

	public void setReady(int ready) {
		this.ready = ready;
		this.ready_new = ready;
	}

	public ArrayList<Student> getList() {
		return list;
	}

	public void setList(ArrayList<Student> list) {
		this.list = list;
	}

	public void addList(Student student_id) {
		this.list.add(student_id);
	}

	public int getQuota_new() {
		return quota_new;
	}

	public void setQuota_new(int quota_new) {
		this.quota_new = quota_new;
	}

	public int getReady_new() {
		return ready_new;
	}

	public void setReady_new(int ready_new) {
		this.ready_new = ready_new;
	}

}
