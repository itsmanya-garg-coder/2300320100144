package com.assignment.microservice_assessment.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepotScheduleResult {
    private int depotID;
    private List<String> selectedTaskIDs;
    private int totalImpact;
    private int totalHoursUsed;
}