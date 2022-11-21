package ua.goit.jdbc.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class DeveloperDto {
    Integer devId;
    String firstName;
    String lastName;
    Date birthDate;
    String gender;
    Integer salary;

    public DeveloperDto() {
    }

    public DeveloperDto(Integer devId, String firstName, String lastName, Date birthDate, String gender, Integer salary) {
        this.devId = devId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.salary = salary;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeveloperDto{");
        sb.append("devId=").append(devId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperDto that = (DeveloperDto) o;
        return (devId == that.devId) && (firstName.equals(that.firstName)) && (lastName.equals(that.lastName)) &&
                (birthDate.equals(that.birthDate)) && (gender.equals(that.gender)) && (salary.equals(that.salary));
    }

    @Override
    public int hashCode() {
        return Objects.hash(devId, firstName, lastName, birthDate, gender, salary);
    }
}
