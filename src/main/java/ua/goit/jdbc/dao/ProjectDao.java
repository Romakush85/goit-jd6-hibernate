package ua.goit.jdbc.dao;

public class ProjectDao {
    Integer projectId;
    String name;
    Integer customerId;
    Integer cost;

    public ProjectDao() {
    }

    public ProjectDao(Integer projectId, String name, Integer customerId, Integer cost) {
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectDao{");
        sb.append("projectId=").append(projectId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", customerId=").append(customerId);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
