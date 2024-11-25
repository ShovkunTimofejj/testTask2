package com.example.secondTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinCostPaths {
    public static void main(String[] args) {
        // Use try-with-resources to automatically close BufferedReader after execution
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("input: ");

            int testCases = Integer.parseInt(br.readLine()); // Read the number of test cases

            // Process each test case
            while (testCases-- > 0) {
                int numCities = Integer.parseInt(br.readLine()); // Read the number of cities
                Map<String, Integer> cityIndex = new HashMap<>(); // Map to associate city names with their indices
                List<List<Edge>> graph = new ArrayList<>(); // Adjacency list to represent the graph

                // Process city information and their neighbors
                for (int i = 0; i < numCities; i++) {
                    String cityName = br.readLine(); // Read the city name
                    cityIndex.put(cityName, i); // Add city to the map with its index
                    graph.add(new ArrayList<>()); // Initialize the neighbors list for the city

                    int neighbors = Integer.parseInt(br.readLine()); // Read the number of neighbors for the city
                    for (int j = 0; j < neighbors; j++) {
                        String[] neighborData = br.readLine().split(" "); // Split the input line by spaces
                        int neighborIndex = Integer.parseInt(neighborData[0]) - 1; // Neighbor index (convert 1-based to 0-based)
                        int cost = Integer.parseInt(neighborData[1]); // Read the cost of the path to the neighbor
                        graph.get(i).add(new Edge(neighborIndex, cost)); // Add an edge to the graph
                    }
                }

                int numPaths = Integer.parseInt(br.readLine()); // Read the number of path queries
                for (int i = 0; i < numPaths; i++) {
                    String[] pathData = br.readLine().split(" "); // Split the query into two cities
                    String startCity = pathData[0]; // Start city name
                    String endCity = pathData[1]; // End city name

                    // Check if both cities exist in the map
                    if (!cityIndex.containsKey(startCity) || !cityIndex.containsKey(endCity)) {
                        System.out.println(-1); // If one of the cities is missing, output -1
                        continue; // Proceed to the next query
                    }

                    int startIndex = cityIndex.get(startCity); // Get the index of the start city
                    int endIndex = cityIndex.get(endCity); // Get the index of the end city

                    // Run Dijkstra's algorithm to find the minimum cost path
                    int result = dijkstra(graph, startIndex, endIndex);

                    // Print "output: " only once for the first query
                    if (i == 0) {
                        System.out.println("output: ");
                    }

                    System.out.println(result); // Output the result (minimum path cost)
                }

                // Skip the blank line between test cases, if any
                if (testCases > 0) br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print error information in case of input/output exception
        }
    }

    // Dijkstra's algorithm to find the minimum cost path between two vertices in a graph
    private static int dijkstra(List<List<Edge>> graph, int start, int end) {
        // Array to store the minimum distances to each vertex
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize the array with maximum values
        dist[start] = 0; // Distance to the start vertex is 0

        // Priority queue to store current vertices and their distances
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        pq.add(new Edge(start, 0)); // Add the start vertex to the queue

        // Main loop for processing the queue
        while (!pq.isEmpty()) {
            Edge current = pq.poll(); // Retrieve the vertex with the minimum distance
            int currentNode = current.target;
            int currentCost = current.cost;

            // If the end vertex is reached, return its cost
            if (currentNode == end) return currentCost;

            // Process all neighbors of the current vertex
            for (Edge neighbor : graph.get(currentNode)) {
                int newCost = currentCost + neighbor.cost; // Calculate the path cost to the neighbor
                if (newCost < dist[neighbor.target]) { // If a shorter path is found
                    dist[neighbor.target] = newCost; // Update the minimum distance
                    pq.add(new Edge(neighbor.target, newCost)); // Add the neighbor to the queue
                }
            }
        }
        return -1; // Return -1 if no path is found
    }

    // Class to represent an edge in the graph
    static class Edge {
        int target; // Target vertex index
        int cost;   // Path cost

        Edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }
}

