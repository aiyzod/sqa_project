package sqa_project;

public class Student {
	private int id; // 准考證號碼
	private String name; // 考生名
	private int grade; // 成績
	private String[] want; // 志願

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String[] getWant() {
		return want;
	}

	public void setWant(String[] want) {
		this.want = want;
	}

}
