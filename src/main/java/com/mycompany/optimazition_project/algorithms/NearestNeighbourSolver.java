package com.mycompany.optimazition_project.algorithms;

import com.mycompany.optimazition_project.models.DeliveryProblem;
import com.mycompany.optimazition_project.models.Point;
import com.mycompany.optimazition_project.models.Solution;
import java.util.ArrayList;
import java.util.List;

public class NearestNeighbourSolver {

    DeliveryProblem problem;

    public NearestNeighbourSolver(DeliveryProblem problem) {
        this.problem = problem;
    }

    public Solution solve() {
        List<List<Integer>> vehicleRoutes = new ArrayList<>();

        // m tane araç için boş liste oluştur
        for (int i = 0; i < problem.m; i++) {
            vehicleRoutes.add(new ArrayList<>());
        }

        boolean[] visited = new boolean[problem.n];
        Point currentDepot = problem.depot;
        double totalDistance = 0;
        int vehicleIndex = 0;

        for (int step = 0; step < problem.n; step++) { //checking every client
            int nextClient = -1;
            double minDistance = Double.MAX_VALUE;

            for (int i = 0; i < problem.n; i++) {
                if (!visited[i]) {
                    double dist = currentDepot.distance(problem.clients.get(i));
                    if (dist < minDistance) {
                        minDistance = dist;
                        nextClient = i;
                    }
                }
            }

            if (nextClient == -1) {
                break;
            }

            visited[nextClient] = true;
            vehicleRoutes.get(vehicleIndex).add(nextClient);
            totalDistance += minDistance;
            currentDepot = problem.clients.get(nextClient);

            // Eğer bir aracın kapasitesi dolduysa, diğer araca geç
            if (vehicleRoutes.get(vehicleIndex).size() >= problem.k) {
                totalDistance += currentDepot.distance(problem.depot); // Aracı depoya döndür
                vehicleIndex++; // Bir sonraki araca geç
                if (vehicleIndex >= problem.m) {
                    break; 
                }
                currentDepot = problem.depot;
            }

        }

        totalDistance += currentDepot.distance(problem.depot);
        return new Solution(totalDistance, vehicleRoutes, false);
    }

}
