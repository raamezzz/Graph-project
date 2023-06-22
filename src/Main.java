import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

   readGraph();

  }

  public static void readGraph(){
    Scanner input = new Scanner(System.in);
    System.out.println("type of the graph: \n d: for directed, u: for undirected:- ");
    char type = input.next().charAt(0);
    System.out.print("how many points and edges?");
    graph g = new graph(input.nextInt(), input.nextInt());
    System.out.println("fill the matrix:  ");
    System.out.print("   ");
    for (vertex vertex : g.vertices) System.out.print(vertex.currentName + " ");
    System.out.println(" ");
    for(int i = 0; i < g.n; i++){
      System.out.print(g.vertices[i].currentName+": ");
      for(int j = 0; j < g.n; j++){
        byte aa = input.nextByte();
        if (aa==0||aa==1) {
          g.adjacencyMatrix[i][j] = aa;
          if(aa==1) {
            g.addEdge(g.vertices[i].currentName,g.vertices[j].currentName);
          }
        }
        else{
          System.out.println("wrong input, please try again");
        }
      }
    }
    switch (type){
      case 'd','D' -> g.directed = true;
      case 'u','U' -> g.directed = false;
    }
    System.out.println(g);
  }
}