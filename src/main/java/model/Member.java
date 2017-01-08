package model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created by LulzimG on 29/12/16.
 */
public class Member {
    private String id;

    @NotNull(message = "First Name should not be null")
    @Size(min = 1, max = 10, message = "Name should contain between 1 and 10 characters")
    private String firstName;

    @NotNull(message = "LastName Name should  not be null")
    @Size(min = 1, max = 10, message = "Name should contain between 1 and 10 characters")
    private String lastName;

    @NotNull(message = "Start Date should  not be null")
    private Date startDate;

    @NotNull(message = "Position should  not be null")
    @Size(min = 1, max = 10, message = "Name should contain between 1 and 10 characters")
    private String position;

    @Valid
    private List<Activity> activities;
    private String image;

    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", startDate=" + startDate +
                ", position='" + position + '\'' +
                ", activities=" + activities +
                ", image='" + image + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
