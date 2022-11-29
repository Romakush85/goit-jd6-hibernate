package ua.goit.jdbc.dao;

import jakarta.persistence.*;

@Entity
@Table(name="customers")
public class CustomerDao {
    Integer customerId;
    String name;
    String country;
    String contactPerson;
    String email;

    public CustomerDao() {
    }

    public CustomerDao(Integer customerId, String name, String country, String contactPerson, String email) {
        this.customerId = customerId;
        this.name = name;
        this.country = country;
        this.contactPerson = contactPerson;
        this.email = email;
    }

    @Id
    @Column(name = "customer_id", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "customer_name", length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "country", length = 100, nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "contact_person", length = 100, nullable = false)
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Column(name = "email", length = 100, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDao that = (CustomerDao) o;

        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null)
            return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerDto{");
        sb.append("customerId=").append(customerId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", contactPerson='").append(contactPerson).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

