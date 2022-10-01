// CS224 Fall 2022
// Aidan Brown and Aiden McCormack

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.event.ChangeEvent;

import java.util.Queue;
import java.util.LinkedList;


public class Graph {
  ArrayList<Node> nodes;

  // List of nodes already discovered
  public Graph() {
    nodes = new ArrayList<Node>();
  }


  public void addNode(Node node) {
    for (Node n: this.nodes) {
      if (n == node) {
        System.out.println("ERROR: graph already has a node " + n.name);
        assert false;
      }
    }
    this.nodes.add(node);
  }

  
  public void addEdge(Node n1, Node n2) {
    // outgoing edge
    int idx1 = this.nodes.indexOf(n1);
    if (idx1 < 0) {
      System.out.println("ERROR: node " + n1.name + " not found in graph");
      assert false;
    }

    int idx2 = this.nodes.indexOf(n2);
    if (idx2 < 0) {
      System.out.println("ERROR: node " + n2.name + " not found in graph");
      assert false;
    }

    n1.addEdge(n2);
  }

  /**
   * Need to ensure that the if statement and foreach is correct
   * @param s
   * @return
   */
  public ArrayList<Node> DFS(Node s) {
    boolean[] visited = new  boolean[nodes.size()];
    Stack<Node> stack = new Stack<Node>();
    stack.push(s); 
    System.out.print(s);

    // While the stack is not empty, pop a node from the stack
    while (!stack.isEmpty()) {
      Node u = stack.pop();
      if(!visited[u.name]){ // if explored[u] = false then
          visited[u.name] = true;
      }
  
      for (Node v : nodes) {
        stack.add(v);
      }
    }
    System.out.print(nodes);
    return nodes;

  } // DFS()

  public void findConnectedComponents() {
    ArrayList<Node> firstNode;
    ArrayList<Node> aNode;
    ArrayList<Node> bNode;
    ArrayList<Node> changeList = new ArrayList<>();
    ArrayList<Node> returnList = new ArrayList<>();


    for(Node a : this.nodes){
      firstNode = DFS(a);
      changeList.add(a);
      System.out.print("DFS("+a.name+")\n");

      for(Node b : this.nodes){
        if(firstNode.contains(b)){
          aNode = DFS(b);

          for(int i = 0; i < aNode.size();i++){
            if(!changeList.contains(aNode)){
              changeList.add(aNode.get(i));
              bNode = DFS(aNode.get(i));

              for(int q = 0; q<bNode.size(); q++){
                changeList.add(bNode.get(q));
              }
            }
          }
        }
      }
      for(Node e: changeList){
        if(!returnList.contains(e)){
          returnList.add(e);
       }
      }
      for(int i = 0; i<returnList.size(); i++){
        for(int l = i; l<returnList.size(); l++){
          if(returnList.get(i).name > returnList.get(l).name){
            returnList.set(i,returnList.get(i));
            returnList.set(l, returnList.get(l));
          }
        }
      }
      System.out.print(returnList + "\n");
      changeList.removeAll(changeList);
      returnList.removeAll(returnList);

    }
  }

} // class Graph
