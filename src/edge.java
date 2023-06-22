public class edge {
  vertex startPoint;
  vertex endPoint;
  String name;

  public static int edgeNo = 1;
  public int currentNo;
  public edge(vertex a, vertex b){
    currentNo=edgeNo;
    startPoint = a;
    endPoint = b;
    name = "e" + "" + currentNo;
    edgeNo++;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof edge) {
      edge e = (edge) obj;
      return e.endPoint == endPoint && e.startPoint == startPoint;
    }else return false;
  }

  public String toStringUndirected(){
  return "(" + startPoint.currentName +"<->"+endPoint.currentName+")";
}
  @Override
  public String toString() {
    return "(" + startPoint.currentName +"->"+endPoint.currentName+")";
  }
}
