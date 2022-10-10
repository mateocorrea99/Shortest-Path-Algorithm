package a5;

public class EdgeImpl implements Edge {
    String source;
    String destination;
    double weight;

    public EdgeImpl(String source, String destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    /* You will include the implementations for any edge methods you need in this file. */

    /*Hint: Make sure you update the Edge interface in Edge.java when you add a new method implementation
    in EdgeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /*Also, any edge fields you want to add for the object should go in this file.  */

    @Override
    public String getSource(){
        return this.source;
    }

    @Override
    public String getDestination(){
        return this.destination;
    }

    @Override
    public double getWeight(){
        return this.weight;
    }
}
