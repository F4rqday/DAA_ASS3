import java.util.*;

public class KruskalAlgorithm {

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

        List<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges);

        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();
        for (String node : graph.nodes) {
            parent.put(node, node);
            rank.put(node, 0);
        }

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0, operations = 0;

        for (Edge e : edges) {
            operations++;
            String root1 = find(parent, e.from);
            String root2 = find(parent, e.to);
            if (!root1.equals(root2)) {
                union(parent, rank, root1, root2);
                mstEdges.add(e);
                totalCost += e.weight;
            }
            if (mstEdges.size() == graph.nodes.size() - 1) break;
        }

        double end = (System.nanoTime() - start) / 1_000_000.0;
        return new Result(mstEdges, totalCost, operations, end);
    }

    private static String find(Map<String, String> parent, String node) {
        while (!parent.get(node).equals(node))
            node = parent.get(node);
        return node;
    }

    private static void union(Map<String, String> parent, Map<String, Integer> rank, String a, String b) {
        if (rank.get(a) < rank.get(b)) parent.put(a, b);
        else if (rank.get(a) > rank.get(b)) parent.put(b, a);
        else {
            parent.put(b, a);
            rank.put(a, rank.get(a) + 1);
        }
    }
}
