package com.assignment.microservice_assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @JsonProperty("TaskID")
    private String taskID;

    @JsonProperty("Duration")
    private int duration;

    @JsonProperty("Impact")
    private int impact;
}