public class vertex {
  public static char name = 'a';
  public static int index = 0;
  int currentIndex;
  char currentName;
  public vertex(){
    currentName = name;
    currentIndex = index;
    index++;
    name++;
  }

  @Override
  public String toString() {
    return "vertex{" +
            "currentIndex=" + currentIndex +
            ", currentName=" + currentName +
            '}';
  }
}
