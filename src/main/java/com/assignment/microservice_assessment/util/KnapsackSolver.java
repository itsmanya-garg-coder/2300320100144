package com.assignment.microservice_assessment.util;

import com.assignment.microservice_assessment.model.Vehicle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapsackSolver {

    public static List<String> findOptimalVehicles(int maxHours, List<Vehicle> vehicles) {
        int numberOfVehicles = vehicles.size();


        int[][] memo = new int[numberOfVehicles][maxHours + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        solveKnapsack(0, maxHours, vehicles, memo);


        List<String> selectedTaskIDs = new ArrayList<>();
        int remainingHours = maxHours;

        for (int i = 0; i < numberOfVehicles - 1; i++) {
            Vehicle vehicle = vehicles.get(i);
            if (remainingHours < vehicle.getDuration()) {
                continue;
            }
            int scoreIfSkipped = memo[i + 1][remainingHours];
            if (memo[i][remainingHours] != scoreIfSkipped) {
                selectedTaskIDs.add(vehicle.getTaskID());
                remainingHours -= vehicle.getDuration();
            }
        }

        if (numberOfVehicles > 0 && remainingHours >= vehicles.get(numberOfVehicles - 1).getDuration()) {
            Vehicle lastVehicle = vehicles.get(numberOfVehicles - 1);
            if (memo[numberOfVehicles - 1][remainingHours] == lastVehicle.getImpact()) {
                selectedTaskIDs.add(lastVehicle.getTaskID());
            }
        }

        return selectedTaskIDs;
    }


    private static int solveKnapsack(int index, int remainingHours, List<Vehicle> vehicles, int[][] memo) {
        if (index >= vehicles.size() || remainingHours <= 0) {
            return 0;
        }

        if (memo[index][remainingHours] != -1) {
            return memo[index][remainingHours];
        }

        Vehicle currentVehicle = vehicles.get(index);

        int skipImpact = solveKnapsack(index + 1, remainingHours, vehicles, memo);

        int pickImpact = 0;
        if (currentVehicle.getDuration() <= remainingHours) {
            pickImpact = currentVehicle.getImpact() +
                    solveKnapsack(index + 1, remainingHours - currentVehicle.getDuration(), vehicles, memo);
        }

        memo[index][remainingHours] = Math.max(skipImpact, pickImpact);
        return memo[index][remainingHours];
    }
}