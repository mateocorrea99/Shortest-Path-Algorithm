package a5;

import java.util.Map;

public interface Node {

     /* You will include the method signatures (return type, name, and arg types) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

     /**
      * @return the name of the node
      */
     //String getName();

     void NodeAddEdge(String destination, Edge edge);

     boolean containsEdge(String destination);

     //void receiveEdge(String source, Edge edge);

     boolean deleteOutEdge(String destination);

     //boolean deleteInEdge(String source);

     //Map<String, Edge> getEdges_in();

     Map<String, Edge> getEdges_out();

     double getDistance();

     void setDistance(double distance);

     void setHandled(boolean bool);

     boolean getHandled();

}
