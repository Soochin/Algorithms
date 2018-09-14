package Stanford;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MinCut {
    public static void main(String[] args) throws IOException, GraphException {
        Graph<Integer, Integer> g = readInput();
        System.out.println(countMinCut(g));
    }

    public static int countMinCut(Graph<Integer, Integer> g) throws GraphException {
        int minCut = Integer.MAX_VALUE;
        for (int i = 0; i < 50; i++) {
            int count = 0;
            while (g.vertexCount() > 2) {
                Random rand = new Random();
                ArrayList<Pair<Integer, Integer>> edges = g.edges();
                Pair<Integer, Integer> randomEdge = edges.get(rand.nextInt(edges.size()));
                g.mergeVertices(randomEdge);
                g.removeSelfLoops();
            }
            count += g.edges().size();
            minCut = Math.min(minCut, count);
        }
        return minCut;
    }


    private static Graph readInput() throws IOException, GraphException {
        BufferedReader in = new BufferedReader(new FileReader("src/Stanford/kargerMinCut.txt"));
        String read = null;
        Graph<Integer, Integer> g = new Graph<>();
        for (int i = 0; i <= 200; i++) {
            g.addVertex(i, 0);
        }
        while ((read = in.readLine()) != null) {
            String[] splitted = read.split("\\s+");
            int start = Integer.valueOf(splitted[0]);
            for (int i = 1; i < splitted.length; i++) {
                g.addEdge(start, Integer.valueOf(splitted[i]), 0);
            }
        }
        return g;
    }
}
