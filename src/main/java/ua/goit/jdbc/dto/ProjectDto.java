package ua.goit.jdbc.dto;

public class ProjectDto {
    Integer projectId;
    String name;
    Integer customerId;
    Integer cost;

    public ProjectDto() {
    }

    public ProjectDto(Integer projectId, String name, Integer customerId, Integer cost) {
        this.projectId = projectId;
        this.name = name;
        this.customerId = customerId;
        this.cost = cost;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectDto that = (ProjectDto) o;

        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        return cost != null ? cost.equals(that.cost) : that.cost == null;
    }

    @Override
    public int hashCode() {
        int result = projectId != null ? projectId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectDao:");
        sb.append("projectId=").append(projectId);
        sb.append(", name='").append(name).append('\'');
        if(customerId != null) {
            sb.append(", customerId=").append(customerId);
        }
        sb.append(", cost=").append(cost);
        return sb.toString();
    }
}
