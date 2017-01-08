package model;


import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * Created by LulzimG on 29/12/16.
 */
public class Project {

    private String id;

    @NotNull(message = "Name should not be null")
    @Size(min = 1, max = 10, message = "Name should contain between 1 and 10 characters")
    private String name;

    @NotNull(message = "Description should not be null")
    @Size(min = 1, max = 30, message = "Description should contain characters between 1 and 30")
    private String description;

    @NotNull(message = "Start Date should not be null")
    private Date startDate;

    @NotNull(message = "Dead Line should not be null")
    private Date deadLine;

    @Valid
    private List<Member> members;

    @Valid
    private List<Sponsor> sponsors;

    @Valid
    private List<Comment> comments;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", deadLine=" + deadLine +
                ", members=" + members +
                ", sponsors=" + sponsors +
                ", comments=" + comments +
                '}';
    }
}
