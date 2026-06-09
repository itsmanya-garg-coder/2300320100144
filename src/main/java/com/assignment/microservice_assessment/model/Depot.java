package com.assignment.microservice_assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Depot {
    @JsonProperty("ID")
    private int id;

    @JsonProperty("MechanicHours")
    private int mechanicHours;
}
