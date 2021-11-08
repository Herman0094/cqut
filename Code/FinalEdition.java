package ScoreAnalysis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FinalEdition {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Function fun = new Function();
		fun.move();
	}
}

// 学生类
class Student implements Serializable {
	public String num;
	public String name;
	public int math;
	public int english;
	public int technology;

	public Student() {

	}

	public Student(String num, String name, int math, int english, int technology) {
		this.num = num;
		this.name = name;
		this.math = math;
		this.english = english;
		this.technology = technology;
	}

	public String toString() {
		return num + "     " + name + "        " + math + "     " + english + "     " + technology;
	}
}

// 功能函数类
class Function {
	static List<Student> stus = new LinkedList<Student>();
	public static String fileName = "FinalData.txt";
	static Scanner sca = new Scanner(System.in);
	static Collection collection = new LinkedList<Student>();

	public Function() {

	}

	public static void move() throws Exception {
		initial();
		while (true) {
			System.out
					.println("请输入你的操作：\n0--新建\n1--查找\n2--显示\n3--数学排名\n4--英语排名\n5--计算机排名\n6--平均成绩排名\n7--成绩分析\n8--结束操作");
			int choose = Integer.parseInt(sca.nextLine());
			switch (choose) {
			case 0:
				newAndSave();
				break;
			case 1:
				Serch();
				break;
			case 2:
				display();
				break;
			case 3:
				Collections.sort(stus, new mathSort());
				display();
				break;
			case 4:
				Collections.sort(stus, new englishSort());
				display();
				break;
			case 5:
				Collections.sort(stus, new technologySort());
				display();
				break;
			case 6:
				Collections.sort(stus, new averageSort());
				display();
				break;
			case 7:
				Analysis();
				break;
			case 8:
				sca.close();
				System.out.println("操作结束！");
				return;
			default:
				System.out.println("输入的操作有误,请重新输入！");
				break;
			}
		}
	}

	// 判断文件是否存在
	public static void initial() throws Exception {
		File file = new File(fileName);
		if (!file.exists())
			return;
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
		int count = inputStream.readInt();
		for (int i = 0; i < count; i++) {
			Student student = (Student) inputStream.readObject();
			stus.add(student);
		}
		inputStream.close();
	}

	// 新建并保存数据
	public static void newAndSave() throws Exception {
		System.out.println("请依次输入学号--姓名--数学--英语--计算机");
		// Scanner scanner = new Scanner(System.in);
		Student student = new Student();
		student.num = sca.nextLine();
		student.name = sca.nextLine();
		student.math = Integer.parseInt(sca.nextLine());
		student.english = Integer.parseInt(sca.nextLine());
		student.technology = Integer.parseInt(sca.nextLine());
		stus.add(student);
		save();
		// scanner.close();
	}

	// 保存数据
	public static void save() throws Exception {
		File file = new File(fileName);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
		objectOutputStream.writeInt(stus.size());
		for (int i = 0; i < stus.size(); i++) {
			Student student = stus.get(i);
			objectOutputStream.writeObject(student);
		}
		objectOutputStream.flush();
		objectOutputStream.close();
	}

	// 查询
	public static void Serch() {
		System.out.println("请输入学生的姓名或学号");
		Scanner scanner = new Scanner(System.in);
		String a = scanner.nextLine();
		for (int i = 0; i < stus.size(); i++) {
			Student student = stus.get(i);
			if (student.num.equals(a)) {
				System.out.println(student);
			} else if (student.name.equals(a)) {
				System.out.println(student);
			}
		}
	}

	// 成绩分析
	public static void Analysis() {
		int s = 0;
		// 最值
		int MathMax = 0;
		int MathMin = 100;
		int EnglishMax = 0;
		int EnglishMin = 100;
		int TechnologyMax = 0;
		int TechnologyMin = 100;

		// 不及格
		int MathDisqualifiedCount1 = 0;
		int EnglishDisqualifiedCount1 = 0;
		int TechnologyDisqualifiedCount1 = 0;
		// 60-69
		int MathQualifiedCount1 = 0;
		int EnglishQualifiedCount1 = 0;
		int TechnologyQualifiedCount1 = 0;
		// 70-79
		int MathGeneralCount1 = 0;
		int EnglishGeneralCount1 = 0;
		int TechnologyGeneralCount1 = 0;
		// 80-89
		int MathExcellentCount1 = 0;
		int EnglishExcellentCount1 = 0;
		int TechnologyExcellentCount1 = 0;
		// 90
		int MathBestCount1 = 0;
		int EnglishBestCount1 = 0;
		int TechnologyBestCount1 = 0;
		// 数学成绩最值
		for (; s < stus.size(); s++) {
			Student temp = stus.get(s);
			if (temp.math > MathMax) {
				MathMax = temp.math;
			}
			if (temp.english > EnglishMax) {
				EnglishMax = temp.english;
			}
			if (temp.technology > TechnologyMax) {
				TechnologyMax = temp.technology;
			}
			if (temp.math < MathMin) {
				MathMin = temp.math;
			}
			if (temp.english < EnglishMin) {
				EnglishMin = temp.english;
			}
			if (temp.technology < TechnologyMin) {
				TechnologyMin = temp.technology;
			}
			if (temp.math < 60) {
				MathDisqualifiedCount1++;
			}
			if (temp.english < 60) {
				EnglishDisqualifiedCount1++;
			}
			if (temp.technology < 60) {
				TechnologyDisqualifiedCount1++;
			}
			if (temp.math > 60 && temp.math < 69) {
				MathQualifiedCount1++;
			}
			if (temp.english > 60 && temp.english < 69) {
				EnglishQualifiedCount1++;
			}
			if (temp.technology > 60 && temp.technology < 69) {
				TechnologyQualifiedCount1++;
			}
			if (temp.math > 70 && temp.math < 79) {
				MathGeneralCount1++;
			}
			if (temp.math > 70 && temp.math < 79) {
				EnglishGeneralCount1++;
			}
			if (temp.math > 70 && temp.math < 79) {
				TechnologyGeneralCount1++;
			}
			if (temp.math > 80 && temp.math < 89) {
				MathExcellentCount1++;
			}
			if (temp.math > 80 && temp.math < 89) {
				EnglishExcellentCount1++;
			}
			if (temp.math > 80 && temp.math < 89) {
				TechnologyExcellentCount1++;
			}
			if (temp.math > 90 && temp.math < 100) {
				MathBestCount1++;
			}
			if (temp.math > 90 && temp.math < 100) {
				EnglishBestCount1++;
			}
			if (temp.math > 90 && temp.math < 100) {
				TechnologyBestCount1++;
			}
		}
		System.out.println("数学成绩最大值为:" + MathMax + "数学成绩最小值为:" + MathMin + "英语成绩最大值为:" + EnglishMax + "英语成绩最小值为:"
				+ EnglishMin + "计算机成绩最大值为:" + TechnologyMax + "计算机成绩最小值为:" + TechnologyMin + "数学不及格人数为:"
				+ MathDisqualifiedCount1 + "英语不及格人数为:" + EnglishDisqualifiedCount1 + "计算机不及格人数为:"
				+ TechnologyDisqualifiedCount1 + "数学60-69分人数为:" + MathQualifiedCount1 + "英语60-69分人数为:"
				+ EnglishQualifiedCount1 + "计算机60-69分人数为:" + TechnologyQualifiedCount1 + "数学70-79分人数:" + MathGeneralCount1
				+ "英语70-79分人数:" + EnglishGeneralCount1 + "计算机70-79分人数:" + TechnologyGeneralCount1 + "数学80-89分人数:"
				+ MathExcellentCount1 + "英语80-89分人数:" + EnglishExcellentCount1 + "计算机80-89分人数:"
				+ TechnologyExcellentCount1 + "数学90分以上人数:" + MathBestCount1 + "英语0分以上人数:" + EnglishBestCount1
				+ "计算机90分以上人数:" + TechnologyBestCount1);
	}

	// 显示
	public static void display() {
		for (int i = 0; i < stus.size(); i++) {
			Student student = stus.get(i);
			System.out.println(student);
		}
	}

}

// 数学成绩排名
class mathSort implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		int s = s2.math - s1.math;
		return s;
	}
}

// 英语成绩排名
class englishSort implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		int d = s2.english - s1.english;
		return d;
	}
}

// 计算机成绩排名
class technologySort implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		int f = s2.technology - s1.technology;
		return f;
	}
}

// 平均成绩排名
class averageSort implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		int a1 = (s1.english + s1.math + s1.technology) / 3;
		int a2 = (s2.english + s2.math + s2.technology) / 3;
		int a = a2 - a1;
		return a;
	}
}

// 数学成绩分析
class mathAnalysis implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		int m = (s1.math + s2.math) / 2;
		return m;
	}
}
