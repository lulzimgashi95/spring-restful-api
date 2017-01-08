package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by LulzimG on 29/12/16.
 */
public class Activity {
    private String id;

    @NotNull(message = "Name should not be null")
    @Size(min = 1, max = 10, message = "Name should contain between 1 and 10 characters")
    private String name;

    @NotNull(message = "Details should not be null")
    @Size(min = 1, message = "Name should contain more than 1 character")
    private String details;

    private String memberId;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
