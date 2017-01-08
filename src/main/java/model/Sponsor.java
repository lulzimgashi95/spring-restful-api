package model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by LulzimG on 29/12/16.
 */
public class Sponsor {
    private String id;

    @NotNull(message = "Name should not be null")
    @Size(min = 1, max = 10, message = "Name should contain between 1 and 10 characters")
    private String name;

    @NotNull(message = "Amount should not be null")
    @Range(min = 1)
    private Double amount;

    @NotNull(message = "Amount should not be null")
    @Size(min = 1,message = "Comment should contain more than 1 character")
    private String comment;

    private String projectId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Sponsor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", projectId='" + projectId + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
