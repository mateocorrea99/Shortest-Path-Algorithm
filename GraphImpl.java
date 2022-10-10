package a5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphImpl implements Graph {
    Map<String, Node> nodes;//Do not delete.  Use this field to store your nodes.
                             // key: name of node. value: a5.Node object associated with name
    int num_nodes=0;
    int num_edges=0;
    double pLen = 0;

    public GraphImpl() {
        nodes = new HashMap<>();
    }

    @Override
    public boolean addNode(String name) {
        if (nodes.containsKey(name) || name.equals("")) {return false;}
        Node node = new NodeImpl(name);
        this.num_nodes ++;
        nodes.put(name, node);
        return true;
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        if (weight < 0 || !nodes.containsKey(dest) || !nodes.containsKey(src)) {return false;}
        Node src_node = nodes.get(src);
        //Node dest_node = nodes.get(dest);
        if (src_node.containsEdge(dest)) {return false;}
        Edge edge = new EdgeImpl(src, dest, weight);
        src_node.NodeAddEdge(dest, edge);
        num_edges++;
        //dest_node.receiveEdge(src, edge);
        return true;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public boolean deleteNode(String name) {
        //Hint: Do you need to remove edges when you delete a node?
        if (!nodes.containsKey(name)) {return false;}

        Node node = nodes.get(name);
        Map<String, Edge> edges_out = node.getEdges_out();
        //Map<String, Edge> edges_in = node.getEdges_in();

        edges_out.forEach((key, value) -> {
            node.deleteOutEdge(name);
            num_edges--;
        });

        nodes.forEach((key, value) -> {
            value.getEdges_out().forEach((dest, edge) -> {
                if (dest == name) {
                    value.deleteOutEdge(name);
                    num_edges--;
                }
            });

        });

        nodes.remove(name);
        num_nodes--;
        return true;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        if (nodes.containsKey(src) == false || nodes.containsKey(dest) == false) {return false;}
        Node src_node = nodes.get(src);
        if (!src_node.getEdges_out().containsKey(dest)) {return false;}
        //Node dest_node = nodes.get(dest);
        src_node.deleteOutEdge(dest);
        //dest_node.deleteInEdge(src);
        num_edges--;
        return true;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public int numNodes() {
        return num_nodes;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public int numEdges() {
        return num_edges;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public void resetpLen() {
        this.pLen = 0;
    }

    /*@Override
    public void resetDistances(){
        nodes.forEach((key, value) -> {
            value.setDistance(100000);
        });
    }*/

    @Override
    public void resetHandled(){
        nodes.forEach((key, value) -> {
            value.setHandled(false);
        });
    }

    /*@Override
    public Map<String, Double> dijkstra(String start) {

        Comparator<ShortestPathQueueObject> compare = Comparator.comparingDouble(o -> o.distance);
        PriorityQueue<ShortestPathQueueObject> pq = new PriorityQueue<>(compare);
        resetpLen();
        resetDistances();
        resetHandled();
        Map <String, Double> results = new HashMap<>();
        ShortestPathQueueObject starting = new ShortestPathQueueObject(start, 0);
        pq.add(starting);

        while (pq.size() > 0) {
            ShortestPathQueueObject current_spqo = pq.poll();
            Node current = nodes.get(current_spqo.label);
            if (current.getHandled()) {continue;}

            pLen += current.getDistance();
            current.setHandled(true);
            Map<String, Edge> curr_out_edges = current.getEdges_out();
            curr_out_edges.forEach((destination, edge) -> {
                if (!nodes.get(destination).getHandled()) {
                    Node current_dest = nodes.get(destination);
                    double aux = pLen + edge.getWeight();
                    if (aux < current_dest.getDistance()){
                        current_dest.setDistance(aux);
                        ShortestPathQueueObject current_dest_spqo = new ShortestPathQueueObject(destination, aux);
                        pq.add(current_dest_spqo);
                    }

                }
            });
        }

        nodes.forEach((name, node) -> {
            results.put(name, node.getDistance());
        });

        System.out.println("Shortest path lengths from node: " + start);
        results.forEach((node, distance) -> {
            System.out.println("from " + node + ": " + distance);
        });

        return results;  //Dummy return value.  Remove when you implement!
    } */

    @Override
    public Map<String, Double> dijkstra(String start) {

        Comparator<Node> compare = Comparator.comparingDouble(o -> o.getDistance());
        PriorityQueue<Node> pq = new PriorityQueue<>(compare);
        nodes.get(start).setDistance(0);
        Map <String, Double> results = new HashMap<>();
        pq.add(nodes.get(start));

        while (pq.size() > 0) {
            Node current = pq.poll();
            if (current.getHandled()) {continue;}

            current.setHandled(true);
            Map<String, Edge> curr_out_edges = current.getEdges_out();
            curr_out_edges.forEach((destination, edge) -> {
                if (!nodes.get(destination).getHandled()) {
                    Node current_dest = nodes.get(destination);
                    pLen = current.getDistance() + edge.getWeight();
                    if (pLen < current_dest.getDistance()){
                        current_dest.setDistance(pLen);
                        pq.add(current_dest);
                    }

                }
            });
        }

        nodes.forEach((name, node) -> {
            if (node.getDistance() != Double.POSITIVE_INFINITY) {results.put(name, node.getDistance());}
        });

        /* System.out.println("Shortest path lengths from node: " + start);
        results.forEach((node, distance) -> {
            System.out.println("from " + node + ": " + distance);
        }); */

        return results;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public void graphVisualize(){
        nodes.forEach((key, value) -> {
            System.out.println("Node: " + key);
            Map<String, Edge> edges = value.getEdges_out();
            System.out.print("Edges: ");
            edges.forEach((e_key, e_value) -> {
                System.out.println("("+e_value.getDestination()+","+e_value.getWeight()+")");
            });
            System.out.println();
        });
    }
}
