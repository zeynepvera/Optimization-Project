
package com.mycompany.optimazition_project.models;


import java.util.List;

public class DeliveryProblem {
    
    public int n, m, k;
    public List<Point> clients;
    public Point depot;

    public DeliveryProblem(int n, int m, int k, List<Point> clients, Point depot) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.clients = clients;
        this.depot = depot;
    }
}
