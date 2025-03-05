package Lab8;

import java.util.*;

public class Graph {
    ArrayList<ArrayList<Integer>> adjList;
    int v = 0;
    int counter = 0;

    public Graph(int v) {
        this.v = v;
        adjList = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++){
            adjList.add(new ArrayList<Integer>());
        }
    }

    void addEdge(int u, int v) {
        adjList.get(u).add(v);
        counter++;
    }

    int getCount(){
        return counter;
    }

    void printAdjacencyList(){
        for (int i = 0; i < adjList.size(); i++){
            System.out.println("Adjacency list of vertex " + i);
            for (int j = 0; j < adjList.get(i).size(); j++){
                System.out.print(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    int[] calculateInDegree(){
        int[] inDegree = new int[v];
        for (int i = 0; i < v; i++){
            for (int j : adjList.get(i)){
                inDegree[j]++;
            }
        }
        return inDegree;
    }

    int[] calculateOutDegree(){
        int[] outDegree = new int[v];
        for (int i = 0; i < v; i++){
            outDegree[i] = adjList.get(i).size();
        }
        return outDegree;
    }


    void findAllPaths(int start, int end, List<Integer> path, List<List<Integer>> paths, int maxDepth) {
        //stop if the path gets too long
        if (path.size() > maxDepth) {
            return;
        }
        
        path.add(start);

        if (start == end) {
            paths.add(new ArrayList<>(path));
        } else {
            for (int i : adjList.get(start)) {
                findAllPaths(i, end, path, paths, maxDepth);
            }
        }
        path.remove(path.size() - 1);
    }


    List<List<Integer>> getAllPaths(int start, int end, int maxDepth) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> paths = new ArrayList<>();
        findAllPaths(start, end, path, paths, maxDepth);
        return paths;
    }

    public static void main(String[] args) {
        int V = 13;
        Graph g = new Graph(V);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(4, 8);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 11);
        g.addEdge(7, 11);
        g.addEdge(8, 9);
        g.addEdge(8, 10);
        g.addEdge(9, 11);
        g.addEdge(10, 11);
        g.addEdge(11, 3);
        g.addEdge(3, 12);

        g.printAdjacencyList();
        System.out.println("Vertices: " + V);
        System.out.println("Edges: " + g.getCount());
        System.out.println("Vertex\tInDegree\tOutDegree");
        int[] inDegree = g.calculateInDegree();
        int[] outDegree = g.calculateOutDegree();
        int max = 0;
        int maxNode = 0;
        for (int i = 0; i < V; i++){
            if ((inDegree[i] + outDegree[i]) > max){
                max = inDegree[i] + outDegree[i];
                maxNode = i;
            }
            System.out.println(i + "\t" + inDegree[i] + "\t" + outDegree[i]);
        }

        System.out.println("Node with most influence: " + maxNode);

        List<List<Integer>> paths = g.getAllPaths(0, 12, 10);
        System.out.println("All paths from 0 to 9 (with max depth 10):");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }
}
