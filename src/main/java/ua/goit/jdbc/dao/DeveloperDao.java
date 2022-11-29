package ua.goit.jdbc.dao;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="developers")
public class DeveloperDao {
    Integer devId;
    String firstName;
    String lastName;
    Date birthDate;
    String gender;
    Integer salary;

    public DeveloperDao() {
    }

    public DeveloperDao(Integer devId, String firstName, String lastName, Date birthDate, String gender, Integer salary) {
        this.devId = devId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.salary = salary;
    }

    @Id
    @Column(name = "dev_id", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    @Column(name = "first_name", length = 100, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 100, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "birth_date", length = 10, nullable = false)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "gender", length = 6, nullable = false)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "salary", length = 10, nullable = false)
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeveloperDao{");
        sb.append("devId=").append(devId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }
}
