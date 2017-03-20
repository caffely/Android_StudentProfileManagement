package com.example.phong.android_studentprofilemanagement.Model;

public class Class {
    String classId;
    String classCode;
    String className;

    public Class() {
    }

    public Class(String classId, String classCode, String className) {
        this.classId = classId;
        this.classCode = classCode;
        this.className = className;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
