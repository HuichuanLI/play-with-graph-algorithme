package Chapter01Graph_Basics;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AdjMatrix {
    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            adj = new int[V][V];

            E = scanner.nextInt();
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
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

        AdjMatrix adjMatrix = new AdjMatrix("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter01Graph_Basics/g.txt");
        System.out.print(adjMatrix);
    }

}
