package model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by LulzimG on 29/12/16.
 */
public class Comment {
    private String id;

    @NotNull(message = "Comment should not be null")
    @Size(min = 1, max = 10, message = "Name should contain between 1 and 10 characters")
    private String comment;

    @NotNull(message = "Date should not be null")
    private Date date;

    private String projectId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
