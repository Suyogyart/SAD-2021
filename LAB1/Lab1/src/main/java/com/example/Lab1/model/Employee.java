package com.example.Lab1.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    public enum Gender {Male, Female}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    private double salary;

    private double value;

    private int positionlevel;

    @Transient // No need to be a column in database (derived attribute)
    private double netvalue; // netvalue = salary - value

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getStringGender() {
        return gender.toString();
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getPositionLevel() {
        return positionlevel;
    }

    public void setPositionLevel(int positionLevel) {
        this.positionlevel = positionLevel;
    }

    public double getNetValue() {
        return this.salary - this.value;
    }

    public void setNetValue(double netValue) {
        this.netvalue = netValue;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
                ", gender=" + gender.toString() +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", value=" + value +
                ", netValue=" + netvalue +
                ", positionLevel=" + positionlevel +
                '}';
    }
}

