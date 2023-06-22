import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class graph {
  boolean connected;
  ArrayList<vertex> connectedNodes = new ArrayList<>();
  ArrayList<edge> edges = new ArrayList<>();
  boolean directed;
  int numberOfVerticesOfOddDegrees;
  int[][] incidenceMatrix;
  int[][] adjacencyMatrix;
  int[][] nodesCount = nodesCount();
  vertex[] vertices;
  boolean isSymmetric;
  boolean hasEulerianPath;
  boolean isReflexive;
  boolean isTransitive;
  int n; //number of vertices
  int e; //number of edges
  public graph(int nv, int ne){
    this.n = nv;
    this.e = ne;
    adjacencyMatrix = new int[nv][nv];
    incidenceMatrix = new int[nv][ne];
    vertices = addVertices(nv);
  }
  public int[][] nodesCount(){ //counts the degree of every node
    int[][] count = new int[n][2];
    for(int i = 0; i < n; i++){
      count[i][1] = rowSum(i);
    }
    return count;
}
  public void addEdge(char a, char b){
    int aa = 0;
    int bb = 0;

    for (int i = 0; i < vertices.length; i++){
      if(vertices[i].currentName==a) aa = i;
      if(vertices[i].currentName==b) bb = i;
    }
    adjacencyMatrix[aa][bb]++;
    if(!directed) adjacencyMatrix[bb][aa]=1;

    edge e = new edge(vertices[aa],vertices[bb]);
    if(edges.isEmpty()){
      connectedNodes.add(vertices[aa]);
      connectedNodes.add(vertices[bb]);
    } else if (connectedNodes.contains(vertices[aa]) && !connectedNodes.contains(vertices[bb])) {
      connectedNodes.add(vertices[bb]);
    } else if (connectedNodes.contains(vertices[bb]) && !connectedNodes.contains(vertices[aa])) {
      connectedNodes.add(vertices[aa]);
    }
    edges.add(e);
  }
  public vertex[] addVertices(int n){
    vertex[]a = new vertex[n];
    for (int i = 0; i < n; i++){
      a[i] = new vertex();
    }
    return a;
  }
  public void eulerianPath(){
    int oddCount = 0;
    for (int[] ints : nodesCount) {
      if (ints[1] % 2 == 1) oddCount++;
    }
    numberOfVerticesOfOddDegrees = oddCount;
if ((oddCount == 2 && connected) || !isAcyclic()) hasEulerianPath = true;
  }
  public void checkGraph(){
    int loopCount = 0;
    int symmetryCount = 0;

    try {
      for (int i = 0; i < n; i++) {
        if (edges.get(i).startPoint == edges.get(i).endPoint) loopCount++;
        for (int j = 0; j < n; j++) {
          if (adjacencyMatrix[i][j] == adjacencyMatrix[j][i]) symmetryCount++;
          if (i < n - 1 && j < n - 1 && adjacencyMatrix[i][j] == 1 && i!=j) {
            for(int k = i+ 1; k < n - i; k++ ) {
              if (adjacencyMatrix[k][i] == 1 && adjacencyMatrix[k][j] == 1 && j != k) {
                isTransitive = true;
                break;
              }
            }
          }
        }
      }
      isReflexive = loopCount == n;
      isSymmetric = symmetryCount == n * n;
    } catch (NullPointerException e) {
      throw new NullPointerException(e.toString());
    }
    ArrayList<vertex> v = new ArrayList<>();
    Collections.addAll(v, vertices);
    if(connectedNodes.containsAll(v)){
      connected = true;
    }
    eulerianPath();
    for(int i = 0; i < n; i++){
      for(int j = 0; j < e; j++){
        if(edges.get(j).startPoint.equals(vertices[i])) incidenceMatrix[i][j]++;
        if(edges.get(j).endPoint.equals(vertices[i])) incidenceMatrix[i][j]++;
      }
    }
  }
public String adjacencyMatrixToString(){
    StringBuilder a = new StringBuilder();
    StringBuilder b = new StringBuilder();
    b.append("   ");
    for(vertex i : vertices){
      b.append(i.currentName);
      b.append("  ");
    }
  for (int i = 0; i < n ; i ++) {
    a.append("\n");
      a.append(vertices[i].currentName);
      a.append(" ");
      a.append(Arrays.toString(adjacencyMatrix[i]));
  }
  a.append("\n");
  return b + ""+a ;
}
public String incidenceMatrixToString(){
  StringBuilder horizontal = new StringBuilder();
  StringBuilder vertical = new StringBuilder();
  vertical.append("   ");
  for(edge i : edges){
    vertical.append(i.name);
    vertical.append(" ");
  }
  for (int i = 0; i < incidenceMatrix.length; i ++) {
    horizontal.append("\n");
    horizontal.append(vertices[i].currentName);
    horizontal.append(" ");
    horizontal.append(Arrays.toString(incidenceMatrix[i]));
  }
  horizontal.append("\n");
  return vertical + "" + horizontal;
}
public String showEdges(){

    StringBuilder sEdges = new StringBuilder();
    if(directed) sEdges.append(edges.toString());
    else {
      for(int i = 0; i < edges.size(); i++){
        for(int j = 0; j <edges.size(); j++){
          if(edges.get(i).startPoint==edges.get(j).endPoint && edges.get(i).endPoint==edges.get(j).startPoint && edges.get(i).startPoint!=edges.get(i).endPoint) edges.remove(j);
        }
        sEdges.append(edges.get(i).toStringUndirected());
      }
    }
    return String.valueOf(sEdges);
}
  @Override
  public String toString() {
    String s;
    checkGraph();
    if(directed){
      s = "directed";
      return "graph:\n" +
            "type: " + s +
            "\nnumber of vertices: " + n +
            "\nadjacency Matrix:-\n" + adjacencyMatrixToString() +
              "\nincidence Matrix:-\n" +incidenceMatrixToString() +
            "edges: \n"+showEdges()+
            "\nis symmetric: "+isSymmetric +
            "\nis reflexive: " + isReflexive +
            "\nis transitive: " + isTransitive +
            "\nconnected: " + connected +
            "\nis acyclic: " + isAcyclic() +
            "\nhas eulerian path: " + hasEulerianPath;
    }else{
      s = "undirected";
      return "graph:\n" +
              "type: " + s +
              "\nnumber of vertices: " + n +
              "\nadjacency Matrix:-\n" + adjacencyMatrixToString() +
              "\nincidence Matrix:-\n" +incidenceMatrixToString() +
              "edges: \n"+showEdges()+
              "\nis reflexive: " + isReflexive +
              "\nis transitive: " + isTransitive +
              "\nconnected: " + connected +
              "\nis acyclic: " + isAcyclic() +
              "\nhas eulerian path: " + hasEulerianPath;
    }

  }
  public int rowSum(int n){ //helping method for counting the degrees of nodes
    int sum = 0;
    for (int i = 0; i < n; i++){
      sum = sum + adjacencyMatrix[n][i];
    }
    return sum;
  }
  public boolean isAcyclic(){
    boolean status = true;
    if(connected && numberOfVerticesOfOddDegrees == 0){
      ArrayList<vertex> visitedVertices = new ArrayList<>();
      edge visitingEdge;
      for (edge edge : edges) {
        visitingEdge = edge;
        if (visitingEdge.startPoint.currentIndex == 0) {
          visitedVertices.add(visitingEdge.startPoint);
          visitedVertices.add(visitingEdge.endPoint);
        } else if (visitedVertices.contains(visitingEdge.startPoint) && !visitedVertices.contains(visitingEdge.endPoint))
          visitedVertices.add(visitingEdge.endPoint);
        if (!visitedVertices.isEmpty()&&visitedVertices.get(0).equals(visitingEdge.endPoint)) status = false;
      }
    }
    return status;
  }
}
