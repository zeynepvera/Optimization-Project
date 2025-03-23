package com.mycompany.optimazition_project.models;

import java.util.List;

public class Solution {
    public double totalDistance;
    public List<List<Integer>> routes;
    public boolean showRoute;  

    public Solution(double totalDistance, List<List<Integer>> routes, boolean showRoute) {
        this.totalDistance = totalDistance;
        this.routes = routes;
        this.showRoute = showRoute;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Total Distance: %.3f\n", totalDistance));

        if (showRoute) { 
            for (List<Integer> route : routes) {
                sb.append("Route: ");
                for (int client : route) {
                    sb.append(client).append(" ");
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
