import org.graphstream.graph.Node;

import java.util.*;

/**
 * Created by seal on 7/1/16.
 */
public class Graph {

    public void fillNodeGraph(org.graphstream.graph.Graph graph,
                              Node[] nodes,
                              int size) {
        for (int i = 0; i < size; i++) {
            nodes[i] = graph.addNode(String.valueOf(i));
        }
    }

    public void makeGraph(org.graphstream.graph.Graph graph,
                          Node[] nodes,
                          int size) {
        Random rand = new Random();
        saningTree(graph, nodes, size);
        int edge = size + (rand.nextInt(size / 2) + (size / 2));
        int avgEdge = (edge / size) + 1;
        sparsGraph(graph, nodes, edge, avgEdge);
    }

    boolean[][] relation;
    private void saningTree(org.graphstream.graph.Graph graph, Node[]nodes, int size) {
        this.relation = new boolean[size][size];
        List<Integer> list = makeIntList(size);
        List<String> stringList = new ArrayList<>(size);
        Random rand = new Random();

        for (int i = 0; list.size() > 1; i++) {
            if (i >= list.size())
                i = 0;

            int randIndex = rand.nextInt(list.size());
            int randIndexValue = list.get(randIndex);
            int iIndexValue = list.get(rand.nextInt(list.size()));
            if (randIndexValue == iIndexValue)
                continue;

            Collections.swap(list, randIndex, list.size() - 1);
            if(relation[iIndexValue][randIndexValue])
                continue;
            else if (relation[randIndexValue][iIndexValue])
                continue;

            String id = makeString(iIndexValue, randIndexValue);
            graph.addEdge(id, nodes[iIndexValue], nodes[randIndexValue]);
            stringList.add(id);
            makeRelation(iIndexValue, randIndexValue);
            list.remove(list.size() - 1);
        }
    }

    private void sparsGraph(org.graphstream.graph.Graph graph,
                            Node[] nodes,
                            int edge, int avgEdge) {
        Random rand = new Random();
        int size = nodes.length;
        for (int i = 0, t = edge * 3; i < edge && t > 0; i++, t--) {
            int prev = i;
            int u = rand.nextInt(size);
            int v = rand.nextInt(size);
            if (u == v) { i = prev; continue; }
            else if (relation[u][v] || relation[v][u]) { i = prev; continue; }
            else if (nodes[u].getDegree() > avgEdge &&
                    nodes[v].getDegree() > avgEdge) { i = prev; continue; }
            String id = makeString(u, v);
            graph.addEdge(id, nodes[u], nodes[v]);
            makeRelation(u, v);
        }
    }

    private void makeRelation(int i, int j) {
        this.relation[i][j] = true;
        this.relation[j][i] = true;
    }

    private String makeString(int i, int j) {
        return new StringBuilder().append(i).append(j).toString();
    }
    private List<Integer> makeIntList(int size) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

}
