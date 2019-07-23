import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;


public class AdjList {

    private int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public AdjList(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            if (V < 0) throw new IllegalArgumentException("V must be non-negative");

            adj = new LinkedList[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<Integer>();
            }
            E = scanner.nextInt();
            if (E < 0) throw new IllegalArgumentException("E must be non-negative");

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                if (a == b) {
                    throw new IllegalArgumentException("self loop is Deteced!");
                }
                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("parallel edge is Deteced!");
                }

                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validteVetex(v);
        validteVetex(w);
        return adj[v].contains(w);
    }

    public LinkedList<Integer> adj(int v) {
        validteVetex(v);
        return adj[v];
    }

    public int degree(int v) {
        return adj(v).size();
    }


    private void validteVetex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertx" + v + "is invalid");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            sb.append(String.format("%d:", i));
            for (int w : adj[i])
                sb.append(String.format("%d ", w));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        AdjList adjList = new AdjList("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        System.out.print(adjList);
    }
}
