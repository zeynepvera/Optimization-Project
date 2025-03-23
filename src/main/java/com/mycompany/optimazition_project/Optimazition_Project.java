package com.mycompany.optimazition_project;
import com.mycompany.optimazition_project.models.Point;
import com.mycompany.optimazition_project.models.Solution;
import com.mycompany.optimazition_project.models.DeliveryProblem;
import com.mycompany.optimazition_project.algorithms.ExactSolver;
import com.mycompany.optimazition_project.algorithms.HillClimbingSolver;
import com.mycompany.optimazition_project.algorithms.NearestNeighbourSolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Optimazition_Project {

    public static void main(String[] args) throws IOException {
        
        String exampleFile = "C:\\Users\\WINCHESTER\\Downloads\\example.txt";
        String testFile1 = "C:\\Users\\WINCHESTER\\Downloads\\test_1car.txt";
        String testFile2 = "C:\\Users\\WINCHESTER\\Downloads\\test_2cars.txt";

        // Exact Algorithm-backtracking
        if (exampleFile.contains("example.txt")) {
            DeliveryProblem exampleProblem = readInput(exampleFile);
            System.out.println("\nExact Algorithm:");
            ExactSolver exactSolver = new ExactSolver(exampleProblem, 60000);
            Solution exactSolution = exactSolver.solve();
            System.out.println(exactSolution);
        } else {
            System.out.println("\nExact Algorithm is skipped for large instances.");
        }

        // Metaheuristic Algoritmalar-Nearest Neighbour and Hill Climbing
        for (String testFile : Arrays.asList(testFile1, testFile2)) {
            System.out.println("\n" + new java.io.File(testFile).getName());
            DeliveryProblem problem = readInput(testFile);

            // Metaheuristic 1: Nearest Neighbour**
            System.out.println("\nMetaheuristic 1: Nearest Neighbour");
            NearestNeighbourSolver nnSolver = new NearestNeighbourSolver(problem);
            Solution nnSolution = nnSolver.solve();
            System.out.println(nnSolution);

            // Metaheuristic 2: Hill Climbing**
            System.out.println("\nMetaheuristic 2: Hill Climbing");
            HillClimbingSolver hcSolver = new HillClimbingSolver(problem, nnSolution);
            Solution hcSolution = hcSolver.solve();
            System.out.println(hcSolution);
        }
    }

    public static DeliveryProblem readInput(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int k = Integer.parseInt(firstLine[2]);

        List<Point> clients = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            double x = Double.parseDouble(line[0]);
            double y = Double.parseDouble(line[1]);
            clients.add(new Point(x, y));
        }

        String[] depotLine = reader.readLine().split(" ");
        Point depot = new Point(Double.parseDouble(depotLine[0]), Double.parseDouble(depotLine[1]));

        reader.close();
        return new DeliveryProblem(n, m, k, clients, depot);
    }
}
