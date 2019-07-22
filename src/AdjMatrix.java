import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            if (V < 0) throw new IllegalArgumentException("V must be non-negative");

            adj = new int[V][V];

            E = scanner.nextInt();
            if (E < 0) throw new IllegalArgumentException("E must be non-negative");

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                if (a == b) {
                    throw new IllegalArgumentException("self loop is Deteced!");
                }
                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("parallel edge is Deteced!");
                }

                adj[a][b] = 1;
                adj[b][a] = 1;
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
        return adj[v][w] == 1;
    }

    public ArrayList<Integer> adj(int v) {
        validteVetex(v);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                result.add(i);
            }
        }
        return result;
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
            for (int j = 0; j < V; j++)
                sb.append(String.format("%d ", adj[i][j]));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        AdjMatrix adjMatrix = new AdjMatrix("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        System.out.print(adjMatrix);
    }
}
