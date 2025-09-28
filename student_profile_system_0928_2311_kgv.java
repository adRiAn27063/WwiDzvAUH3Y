// 代码生成时间: 2025-09-28 23:11:33
package com.example.studentprofilesystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// 学生实体类
class Student {
    private int id;
    private String name;
    private int age;
    private String gender;

    // 省略getter和setter方法
}

// 学生DAO类
class StudentDAO {
    private SessionFactory sessionFactory;

    public StudentDAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            students = session.createQuery("from Student", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // 省略其他方法
}

// 主程序类
public class StudentProfileSystem {
    private StudentDAO studentDAO;

    public StudentProfileSystem() {
        this.studentDAO = new StudentDAO();
    }

    public void createStudentProfile() {
        // 创建学生对象
        Student student = new Student();
        // 设置学生属性
        student.setName("John Doe");
        student.setAge(20);
        student.setGender("Male");

        // 添加学生到数据库
        studentDAO.addStudent(student);
    }

    public void displayStudentProfiles() {
        // 获取所有学生信息
        List<Student> students = studentDAO.getAllStudents();

        // 打印学生信息
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + ", Age: " + student.getAge() + ", Gender: " + student.getGender());
        }
    }

    public static void main(String[] args) {
        StudentProfileSystem system = new StudentProfileSystem();

        // 创建学生画像
        system.createStudentProfile();

        // 显示所有学生画像
        system.displayStudentProfiles();
    }
}