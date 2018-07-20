package model;

public class Student {
	private int Id;
	private String name;
	private int age, year;
	
	public Student(int id, String name, int age, int year) {
		super();
		Id = id;
		this.name = name;
		this.age = age;
		this.year = year;
	}
	public Student(String name, int age, int year) {
		super();
		this.name = name;
		this.age = age;
		this.year = year;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Student [Id=" + Id + ", name=" + name + ", age=" + age + ", year=" + year + "]";
	}
	
	
}
