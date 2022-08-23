package ru.job4j.auth.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private String lastName;
    private int inn;
    private LocalDate date;
    private List<Person> accounts;

    public Employee(int id, String name, String lastName, int inn, LocalDate date, List<Person> accounts) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.inn = inn;
        this.date = date;
        this.accounts = accounts;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Person> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Person> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && inn == employee.inn && Objects.equals(name, employee.name) && Objects.equals(lastName, employee.lastName) && Objects.equals(date, employee.date) && Objects.equals(accounts, employee.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, inn, date, accounts);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", inn=").append(inn);
        sb.append(", date=").append(date);
        sb.append(", accaunts=").append(accounts);
        sb.append('}');
        return sb.toString();
    }
}
