import java.util.*;

public class PrimAlgorithm {

    static class Result {
        List<Edge> mstEdges;
        int totalCost;
        int operations;
        double timeMs;

        Result(List<Edge> e, int cost, int ops, double t) {
            mstEdges = e;
            totalCost = cost;
            operations = ops;
            timeMs = t;
        }
    }

    public static Result run(Graph graph) {
        long start = System.nanoTime();

        Set<String> visited = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0, operations = 0;

        Map<String, List<Edge>> adj = new HashMap<>();
        for (String node : graph.nodes) adj.put(node, new ArrayList<>());
        for (Edge e : graph.edges) {
            adj.get(e.from).add(new Edge(e.from, e.to, e.weight));
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight));
        }

        visited.add(graph.nodes.get(0));

        while (visited.size() < graph.nodes.size()) {
            Edge minEdge = null;
            int minW = Integer.MAX_VALUE;

            for (String u : visited) {
                for (Edge e : adj.get(u)) {
                    operations++;
                    if (!visited.contains(e.to) && e.weight < minW) {
                        minW = e.weight;
                        minEdge = e;
                    }
                }
            }

            if (minEdge == null) break;

            visited.add(minEdge.to);
            mstEdges.add(minEdge);
            totalCost += minEdge.weight;
        }

        double end = (System.nanoTime() - start) / 1_000_000.0;
        return new Result(mstEdges, totalCost, operations, end);
    }
}
