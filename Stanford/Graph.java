package Stanford;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


class GraphException extends Exception {
    public GraphException(String s) {
        super(s);
    }
}

class Edge <EdgeInfo> {
    int fromVertex;
    int toVertex;
    EdgeInfo einfo;

    Edge(int fromVertex, int toVertex, EdgeInfo einfo) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.einfo = einfo;
    }
}


class Vertex <VertexInfo, EdgeInfo> {
    VertexInfo vinfo;
    LinkedList<Edge<EdgeInfo>> edges;

    Vertex(VertexInfo vinfo) {
        this.vinfo = vinfo;
        this.edges = new LinkedList<>();
    }
}


public class Graph <VertexInfo, EdgeInfo> {
    private HashMap<Integer, Vertex<VertexInfo, EdgeInfo>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    // return an ArrayList containing the vertex numbers of
    // every vertex in this graph
    public final ArrayList<Integer> vertices() {
        ArrayList<Integer> v = new ArrayList<>();
        for (Integer key : graph.keySet()) {
            v.add(key);
        }
        return v;
    }


    // return an ArrayList of Pairs, in which each Pair contains <from, to>
    // vertex number of an edge in this graph. All edges are included.
    public final ArrayList<Pair<Integer, Integer>> edges() {
        ArrayList<Pair<Integer, Integer>> e = new ArrayList<>();
        for (Vertex<VertexInfo, EdgeInfo> vertex: graph.values()) {
            for (Edge<EdgeInfo> edge : vertex.edges) {
                e.add(new Pair<>(edge.fromVertex, edge.toVertex));
            }
        }
        return e;
    }


    public final ArrayList<Pair<Integer, Integer>> edges(int vertex) throws GraphException {
        ArrayList<Pair<Integer, Integer>> e = new ArrayList<>();

        if (!graph.containsKey(vertex))
            throw new GraphException("Vertex " + vertex + " does not exist.");

        for (Edge<EdgeInfo> edgeinfo: graph.get(vertex).edges) {
            e.add(new Pair<>(edgeinfo.fromVertex, edgeinfo.toVertex));
        }

        return e;
    }


    public final VertexInfo vertexInfo(int vertex) throws GraphException {
        if (!graph.containsKey(vertex))
            throw new GraphException("Given vertex " + vertex + " not found");

        return graph.get(vertex).vinfo;
    }


    public final EdgeInfo edgeInfo(int fromVertex, int toVertex) throws GraphException {
        if (!graph.containsKey(fromVertex))
            throw new GraphException("fromVertex " + fromVertex + " not found");
        if (!graph.containsKey(toVertex))
            throw new GraphException("toVertex " + toVertex + " not found");

        for (Edge<EdgeInfo> edge : graph.get(fromVertex).edges) {
            if (edge.toVertex == toVertex) {
                return edge.einfo;
            }
        }

        throw new GraphException("Edge with fromVertex: " + fromVertex + "and toVertex: " +
                                  toVertex + " does not exist in this graph.");
    }


    public void addVertex(int vertex, VertexInfo vinfo) throws GraphException {
        if (graph.containsKey(vertex))
            throw new GraphException("Vertex " + vertex + " already exists in the graph.");

        graph.put(vertex, new Vertex<>(vinfo));
    }


    public void addEdge(int fromVertex, int toVertex, EdgeInfo einfo) throws GraphException {
        if (!graph.containsKey(fromVertex))
            throw new GraphException("fromVertex " + fromVertex + " does not exist.");
        else if (!graph.containsKey(toVertex))
            throw new GraphException("toVertex " + toVertex + " does not exist.");
        else {
            ArrayList<Pair<Integer, Integer>> curEdges = edges(fromVertex);
            if (curEdges.contains(new Pair<>(fromVertex, toVertex))) {
                throw new GraphException("Edge with fromVertex: " + fromVertex +
                                         " and toVertex: " + toVertex + " already exist in the graph.");
            }

            graph.get(fromVertex).edges.add(new Edge<> (fromVertex, toVertex, einfo));
        }
    }


    public void removeVertex(int vertex) throws GraphException {
        if (!graph.containsKey(vertex))
            throw new GraphException("Vertex " + vertex + " does not exist.");

        graph.remove(vertex);

        for (Vertex<VertexInfo, EdgeInfo> vertices : graph.values()) {
            LinkedList<Edge<EdgeInfo>> edges = vertices.edges;
            for (int i = 0; i < edges.size(); i++)
                if (edges.get(i).fromVertex == vertex) {
                    vertices.edges.remove(i);
                    i--;
                }
            }
        }



    public final int vertexCount() {
        return graph.size();
    }


    public final int edgeCount() {
        return edges().size();
    }


    public boolean containsEdge(int startVertex, int endVertex) throws GraphException {
        for (Pair<Integer, Integer> edge : edges(startVertex)) {
            if (edge.getKey() == startVertex && edge.getValue() == endVertex)
                return true;
        }

        for (Pair<Integer, Integer> edge : edges(endVertex)) {
            if (edge.getKey() == endVertex && edge.getValue() == startVertex)
                return true;
        }

        return false;
    }


    public void mergeVertices(Pair<Integer, Integer> edgeChosen) throws GraphException {
        int startVertex = edgeChosen.getKey();
        int endVertex = edgeChosen.getValue();
        if (graph.get(endVertex) == null || graph.get(startVertex) == null)
            throw new GraphException("Vertices not present.");

        for (Edge<EdgeInfo> edge : graph.get(endVertex).edges) {
            if (!containsEdge(startVertex, endVertex))
                addEdge(startVertex, edge.toVertex, null);
        }
        removeVertex(endVertex);
    }


    public void removeSelfLoops() {
        for (Vertex<VertexInfo, EdgeInfo> vertex: graph.values()) {
            for (Edge<EdgeInfo> edge : vertex.edges) {
                if (edge.fromVertex == edge.toVertex) {
                    vertex.edges.remove(edge);
                }
            }
        }
    }
}