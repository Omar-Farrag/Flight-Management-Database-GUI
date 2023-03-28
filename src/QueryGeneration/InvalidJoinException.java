package QueryGeneration;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import QueryGeneration.Graph.Node;

public class InvalidJoinException extends Exception {
    private ArrayList<Node> tables;

    public InvalidJoinException(ArrayList<Graph.Node> tables) {
        this.tables = tables;
    }

    @Override
    public String getMessage() {
        return "The attributes you're retrieving, in tables " +
                tables.stream().map(Node::getAliasedName).collect(Collectors.joining(",")) +
                ", that cannot be joined";
    }
}