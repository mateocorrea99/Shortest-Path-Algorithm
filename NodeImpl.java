package a5;

import java.util.HashMap;
import java.util.Map;

public class NodeImpl implements Node {
    String name;
    //Map<String, Edge> edges_in;
    Map<String, Edge> edges_out;
    boolean handled = false;
    double distance = Double.POSITIVE_INFINITY;

    public NodeImpl(String name) {
        this.name = name;
        //this.edges_in = new HashMap<>();
        this.edges_out = new HashMap<>();
    }
    /* You will include the method signatures (return type, name, and arg types) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /*Also, any node fields you want to add for the object should go in this file.  */

    /*@Override
    public String getName() {
        return this.name;
    }*/

    @Override
    public double getDistance() {
        return this.distance;
    }

    @Override
    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public void setHandled(boolean bool) {
        this.handled = bool;
    }

    @Override
    public boolean getHandled(){
        return this.handled;
    }

    /*@Override
    public Map<String, Edge> getEdges_in() {
        return this.edges_in;
    }*/

    @Override
    public Map<String, Edge> getEdges_out() {
        return this.edges_out;
    }

    @Override
    public void NodeAddEdge(String destination, Edge edge) {
        this.edges_out.put(destination, edge);
    }

    @Override
    public boolean containsEdge(String destination){
        if (this.edges_out.containsKey(destination)) {return true;}
        else {return false;}
    }

    /*@Override
    public void receiveEdge(String source, Edge edge){
        this.edges_in.put(source, edge);
    }*/

    @Override
    public boolean deleteOutEdge(String destination){
        edges_out.remove(destination);
        return true;
    }

    /*@Override
    public boolean deleteInEdge(String source){
        edges_in.remove(source);
        return true;
    }*/

}
