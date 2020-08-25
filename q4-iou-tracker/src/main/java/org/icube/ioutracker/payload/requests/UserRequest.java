package org.icube.ioutracker.payload.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

public class UserRequest {

    @Size(min = 2,max = 50,message = "Please provide a name with 2 or more characters, and less than 50.")
    @JsonProperty("name")
    @NotEmpty(message = "name is a required field!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
