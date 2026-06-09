package com.assignment.microservice_assessment.model;

import java.util.List;
import lombok.Data;

@Data
public class DepotResponseWrapper {

    private List<Depot> depots;
}