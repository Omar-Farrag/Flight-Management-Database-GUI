package DatabaseManagement.QueryGeneration;

import DatabaseManagement.Exceptions.InvalidJoinException;
import DatabaseManagement.Attribute;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.Filters;
import DatabaseManagement.QueryGeneration.Graph.Link;
import DatabaseManagement.QueryGeneration.Graph.Node;

import java.util.*;

public class QueryGenerator {

    private AttributeCollection toGet;
    private Filters toFilter;
    private Graph graph;
    private Set<Node> tables_to_join;
    private Set<Link> links;

    public QueryGenerator(AttributeCollection toGet, Filters toFilter) {
        this.toGet = toGet;
        this.toFilter = toFilter;
        graph = new Graph();
        tables_to_join = new HashSet<>();
        links = new HashSet<>();
    }

    public QueryGenerator(Filters toFilter) {
        this.toGet = new AttributeCollection();
        this.toFilter = toFilter;
        graph = new Graph();
        tables_to_join = new HashSet<>();
        links = new HashSet<>();
    }

    public QueryGenerator(AttributeCollection toGet) {
        this.toGet = toGet;
        this.toFilter = new Filters();
        graph = new Graph();
        tables_to_join = new HashSet<>();
        links = new HashSet<>();
    }

    public String getFromClause() throws InvalidJoinException {
        //identify which tables to join;
        init_tables_to_join();
        Iterator<Node> nodeIterator = tables_to_join.iterator();
        if (tables_to_join.size() == 1) {
            return nodeIterator.next().getAliasedName();
        }

        Set<Node> foundNodes = new HashSet<>();
        Node starting = nodeIterator.next();
        foundNodes.add(starting);
        tables_to_join.remove(starting);
        Iterator<Node> foundNodeIterator = foundNodes.iterator();

        Queue<Node> BFSqueue = new LinkedList<>();

        while (foundNodeIterator.hasNext()) {
            Node start = foundNodeIterator.next();

            graph.unVisitNodes();
            BFSqueue.add(start);
            start.visit();

            while (!BFSqueue.isEmpty()) {
                Node current = BFSqueue.poll();

                if (tables_to_join.contains(current)) {
                    foundNodes.add(current);
                    tables_to_join.remove(current);

                    for (Node node = current; node.getParent() != null; node = node.getParent()) {
                        links.addAll(graph.getLinksTo(node.getParent(), node));
                    }
                    if (current.getParent() == null) {
                        links.addAll(graph.getLinksTo(current, current));
                    }
                    if (tables_to_join.size() == 0) {
                        return generatedFromClause();
                    }
                }
                for (Node neighbor : graph.getNeighbors(current)) {
                    if (!neighbor.isVisited()) {
                        neighbor.visit();
                        neighbor.setParent(current);
                        BFSqueue.add(neighbor);
                    }
                }
            }
        }
        throw new InvalidJoinException(tables_to_join);
    }

    public static String getSetClause(AttributeCollection toModify) {
        String clause = "set ";
        ArrayList<String> updates = new ArrayList<>();
        for (Attribute attribute : toModify.attributes()) {
            String update = attribute.getStringName() + " = " + attribute.getStringValue();
            updates.add(update);
        }
        return clause + String.join(",", updates);

    }

    private String generatedFromClause() {

        String clause = "";

        init_tables_to_join();
        ArrayList<String> tableNames = new ArrayList<>();
        for (Node table : tables_to_join) {
            tableNames.add(table.getAliasedName());
        }
        clause += String.join(",", tableNames);

        clause += " where ";

        ArrayList<String> conditions = new ArrayList<>();
        for (Link link : links) {
            conditions.add(link.getAliasedCondition());
        }
        clause += String.join(" and ", conditions);

        return clause;
    }

    private void init_tables_to_join() {
        for (Attribute attribute : toGet.attributes()) {
            tables_to_join.add(new Node(attribute.getT()));
        }

        for (Attribute attribute : toFilter.getAttributes()) {
            tables_to_join.add(new Node(attribute.getT()));
        }
    }

//    public static void main(String[] args) {
//        AttributeCollection toGet = new AttributeCollection();
//        toGet.add(new Attribute(Attribute.Name.LEASE_NUM, Table.LEASES));
////        toGet.add(new Attribute(Attribute.Name.USER_ID, Table.USERS));
//        toGet.add(new Attribute(Attribute.Name.LOCATION_NUM, Table.LOCS));
//        toGet.add(new Attribute(Attribute.Name.UTILITY_ID, Table.BILLS));
//        toGet.add(new Attribute(Attribute.Name.BILL_NUM, Table.DISCOUNTS));
//        toGet.add(new Attribute(Attribute.Name.BILL_NUM, Table.BILLS));
//
//        QueryGenerator qg = new QueryGenerator(toGet, new Filters());
//        try {
//
//            System.out.println(qg.getFromClause());
//        } catch (InvalidJoinException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
