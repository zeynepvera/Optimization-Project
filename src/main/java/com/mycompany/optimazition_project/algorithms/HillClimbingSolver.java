
package com.mycompany.optimazition_project.algorithms;


import com.mycompany.optimazition_project.models.DeliveryProblem;
import com.mycompany.optimazition_project.models.Solution;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class HillClimbingSolver {
    DeliveryProblem problem;
    Solution initialSolution;

    public HillClimbingSolver(DeliveryProblem problem, Solution initialSolution) {
        this.problem = problem;
        this.initialSolution = initialSolution;
    }

  public Solution solve() {
    List<List<Integer>> routes = new ArrayList<>(initialSolution.routes);
    double bestDistance = initialSolution.totalDistance;
    boolean improvement = true;
    int iterationCount = 0;
    int MAX_ITERATIONS = 10000;
    Random rand = new Random();

    while (improvement && iterationCount < MAX_ITERATIONS) {
        improvement = false;
        iterationCount++;

        for (List<Integer> route : routes) {  // Her aracın rotasını ayrı ayrı optimize et
            for (int i = 1; i < route.size() - 1; i++) {
                for (int j = i + 1; j < route.size(); j++) {
                    // Swap (İki noktayı değiştir)
                    Collections.swap(route, i, j);
                    double newDistance = calculateTotalDistance(route);
                    if (newDistance < bestDistance) {
                        bestDistance = newDistance;
                        improvement = true;
                    } else {
                        Collections.swap(route, i, j); // Swap geri alınır
                    }

                    // Subroute reverse (Alt rotayı ters çevir)
                    reverseSubroute(route, i, j);
                    newDistance = calculateTotalDistance(route);
                    if (newDistance < bestDistance) {
                        bestDistance = newDistance;
                        improvement = true;
                    } else {
                        reverseSubroute(route, i, j); // Geri al
                    }
                }
            }
        }

        // Eğer sıkıştıysa rastgele bir hareket dene
        if (!improvement) {
            int vehicleIndex = rand.nextInt(routes.size());
            int randomIndex1 = rand.nextInt(routes.get(vehicleIndex).size());
            int randomIndex2 = rand.nextInt(routes.get(vehicleIndex).size());
            Collections.swap(routes.get(vehicleIndex), randomIndex1, randomIndex2);
        }
    }

    return new Solution(bestDistance, routes,false);
}


    private void reverseSubroute(List<Integer> route, int start, int end) {
        while (start < end) {
            Collections.swap(route, start, end);
            start++;
            end--;
        }
    }

    private double calculateTotalDistance(List<Integer> route) {
        double totalDistance = problem.depot.distance(problem.clients.get(route.get(0)));
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += problem.clients.get(route.get(i)).distance(problem.clients.get(route.get(i + 1)));
        }
        totalDistance += problem.clients.get(route.get(route.size() - 1)).distance(problem.depot);
        return totalDistance;
    }
}