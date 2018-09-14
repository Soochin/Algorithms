package Stanford;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class kargerMinCut {
    private static Integer[] chooseEdge(Map<Integer, List<Integer>> graph) {
        Random rand = new Random();
        Object[] keys = graph.keySet().toArray();
        Integer fromVertex = (Integer) keys[rand.nextInt(keys.length)];
        List<Integer> vertices = graph.get(fromVertex);
        Integer toVertex = vertices.get(rand.nextInt(vertices.size()));
        return new Integer[]{fromVertex, toVertex};
    }


    public static int kargerMinCut(Map<Integer, List<Integer>> graph) {
        while(graph.size() > 2) {
            Integer[] edge = chooseEdge(graph);
            Integer fromVertex = edge[0];
            Integer toVertex = edge[1];
            List<Integer> vertices = graph.get(toVertex);
            for (int i = 0; i < vertices.size(); i++) {
                Integer cur = vertices.get(i);
                graph.get(fromVertex).add(cur);
                graph.get(cur).remove(toVertex);
                graph.get(cur).add(fromVertex);
            }
            graph.get(fromVertex).removeAll(Collections.singleton(fromVertex));
            graph.remove(toVertex);
        }

        return graph.entrySet().iterator().next().getValue().size();
    }


    public static int operate(int n, Map<Integer, List<Integer>> graph) {
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Map<Integer, List<Integer>> g = new HashMap<>(graph);
            int minCut = kargerMinCut(g);
            if (minCut < count)
                count = minCut;
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/Stanford/kargerMinCut.txt"));
        Map<Integer, List<Integer>> graph = new HashMap<>();
        while (in.hasNextLine()) {
            List<Integer> vertices = new ArrayList<>();
            String line = in.nextLine();
            String[] arr = line.split("\\s+");
            for (int i = 1; i < arr.length; i++) {
                vertices.add(Integer.parseInt(arr[i]));
            }
            graph.put(Integer.parseInt(arr[0]), vertices);
        }
        operate(50, graph);
    }
}