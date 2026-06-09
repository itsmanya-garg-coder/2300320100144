package com.assignment.microservice_assessment.controller;

import com.assignment.microservice_assessment.model.*;
import com.assignment.microservice_assessment.util.KnapsackSolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleSchedulerController {

    @GetMapping("/schedule")
    public ResponseEntity<List<DepotScheduleResult>> getOptimalSchedule() {
        try {
            DepotResponseWrapper depotResponse = new DepotResponseWrapper();
            List<Depot> mockDepots = new ArrayList<>();
            mockDepots.add(new Depot(1, 40));
            mockDepots.add(new Depot(2, 25));
            depotResponse.setDepots(mockDepots);

            VehicleResponseWrapper vehicleResponse = new VehicleResponseWrapper();
            List<Vehicle> mockVehicles = new ArrayList<>();
            mockVehicles.add(new Vehicle("TASK-101", 20, 50));
            mockVehicles.add(new Vehicle("TASK-102", 15, 35));
            mockVehicles.add(new Vehicle("TASK-103", 25, 60));
            mockVehicles.add(new Vehicle("TASK-104", 10, 20));
            vehicleResponse.setVehicles(mockVehicles);

            if (depotResponse == null || depotResponse.getDepots() == null ||
                    vehicleResponse == null || vehicleResponse.getVehicles() == null) {
                return ResponseEntity.badRequest().build();
            }

            List<Depot> depots = depotResponse.getDepots();
            List<Vehicle> vehicles = vehicleResponse.getVehicles();
            List<DepotScheduleResult> finalSchedule = new ArrayList<>();

            for (Depot depot : depots) {
                List<String> optimalTaskIDs = KnapsackSolver.findOptimalVehicles(depot.getMechanicHours(), vehicles);

                int totalImpact = 0;
                int totalHoursUsed = 0;

                for (Vehicle v : vehicles) {
                    if (optimalTaskIDs.contains(v.getTaskID())) {
                        totalImpact += v.getImpact();
                        totalHoursUsed += v.getDuration();
                    }
                }

                finalSchedule.add(new DepotScheduleResult(
                        depot.getId(),
                        optimalTaskIDs,
                        totalImpact,
                        totalHoursUsed
                ));
            }

            return ResponseEntity.ok(finalSchedule);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}