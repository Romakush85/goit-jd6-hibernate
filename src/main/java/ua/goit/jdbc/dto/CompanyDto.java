package ua.goit.jdbc.dto;

public class CompanyDto {
    Integer companyId;
    String name;
    String country;
    String contactPerson;
    String email;

    public CompanyDto() {
    }

    public CompanyDto(Integer companyId, String name, String country, String contactPerson, String email) {
        this.companyId = companyId;
        this.name = name;
        this.country = country;
        this.contactPerson = contactPerson;
        this.email = email;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

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

        CompanyDto that = (CompanyDto) o;

        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null)
            return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = companyId != null ? companyId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompanyDto{");
        sb.append("companyId=").append(companyId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", contactPerson='").append(contactPerson).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
