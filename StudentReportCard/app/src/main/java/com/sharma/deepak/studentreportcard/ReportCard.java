package com.sharma.deepak.studentreportcard;

import java.io.Serializable;

/**
 * Created by deepak on 6/27/2017.
 */

public class ReportCard implements Serializable{
    private String studentName, studentClass, studentRollNo, subject1, subject2, subject3, subject4, subject5;

    public ReportCard(String studentName, String studentClass, String studentRollNo, String subject1, String subject2, String subject3, String subject4, String subject5) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentRollNo = studentRollNo;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.subject4 = subject4;
        this.subject5 = subject5;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void setStudentRollNo(String studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    public void setSubject4(String subject4) {
        this.subject4 = subject4;
    }

    public void setSubject5(String subject5) {
        this.subject5 = subject5;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getStudentRollNo() {
        return studentRollNo;
    }

    public String getSubject1() {
        return subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public String getSubject4() {
        return subject4;
    }

    public String getSubject5() {
        return subject5;
    }

    @Override
    public String toString() {
        String reportCardInfo = "Student Name is " + studentName + " ,student class id " + studentClass + " ,student roll no is "
                + studentRollNo + " ,marks obtained in sub 1 is " + subject1
                + " ,marks obtained in sub 2 is " + subject2
                + " ,marks obtained in sub 2 is " + subject2
                + " ,marks obtained in sub 3 is " + subject3
                + " ,marks obtained in sub 4 is " + subject4
                + " ,marks obtained in sub 5 is " + subject5;
        return super.toString();
    }
}
