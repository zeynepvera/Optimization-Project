package com.mycompany.optimazition_project.algorithms;

import com.mycompany.optimazition_project.models.DeliveryProblem;
import com.mycompany.optimazition_project.models.Solution;
import java.util.ArrayList;
import java.util.List;

public class ExactSolver {

    DeliveryProblem problem;
    Solution bestSolution;
    long startTime; //sonsuz calismamasi icin takip. 
    long timeLimitMillis;

    public ExactSolver(DeliveryProblem problem, long timeLimitMillis) {
        this.problem = problem;
        this.bestSolution = new Solution(Double.MAX_VALUE, new ArrayList<>(), true);
        this.timeLimitMillis = timeLimitMillis;
    }

    public Solution solve() {
        startTime = System.currentTimeMillis();
        List<Integer> currentRoute = new ArrayList<>(); //suan olusturdugumus way
        boolean[] visited = new boolean[problem.n];
        backtrack(0, visited, currentRoute, 0);
        return bestSolution;
    }

    //depth: ziyaret edilen musteri sayaci
    private void backtrack(double currentDistance, boolean[] visited, List<Integer> currentRoute, int depth) {
        
        
       
        if (System.currentTimeMillis() - startTime > timeLimitMillis) {
            return; //over time limit
        }

        if (currentDistance >= bestSolution.totalDistance) {
            return; 
        }

        if (depth == problem.n) { //visited tum musteri
            currentDistance += problem.depot.distance(problem.clients.get(currentRoute.get(currentRoute.size() - 1)));
            if (currentDistance < bestSolution.totalDistance) {
                bestSolution.totalDistance = currentDistance;
                bestSolution.routes = new ArrayList<>();
                bestSolution.routes.add(new ArrayList<>(currentRoute));
            }
            return;
        }

        for (int i = 0; i < problem.n; i++) {//problem.n musteri sayisi
            if (!visited[i]) {
                visited[i] = true;
                currentRoute.add(i);
                double distanceToAdd = currentRoute.size() == 1
                        ? problem.depot.distance(problem.clients.get(i))
                        : problem.clients.get(currentRoute.get(currentRoute.size() - 2)).distance(problem.clients.get(i));

                backtrack(currentDistance + distanceToAdd, visited, currentRoute, depth + 1);

                visited[i] = false;
                currentRoute.remove(currentRoute.size() - 1);
            }
        }
    }
}
