// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: shahahm6
// UT Student #: 1004192287
// Author: Ahmad Shah
//
// Student2:
// UTORID user_name: wuadam
// UT Student #: 1004266741
// Author: Adam Wu
//
// Student3:
// UTORID user_name: muruga12
// UT Student #: 1004020999
// Author: Ramesh Murugananthan
//
// Student4:
// UTORID user_name: caojeff
// UT Student #: 1004259615
// Author: Jeff Cao
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package driver;

import err.JShellException;
import java.util.Iterator;
import java.util.List;
import java.util.Iterator;

// TODO: add missing JavaDoc's
public class File extends Item implements Iterable<String> {
  private String contents;
  private Path path;
  private Directory parent;

  public File(String n, Directory parent) {
    super(n);
    this.parent = parent;
    contents = "";
  }

  public String getContents() {
    return this.contents;
  }

  public void setContents(String c) {
    this.contents = File.replaceNewLineString(c);
  }
  
  // replaces the automatically converted backslash from input to the actual
  // escape sequence
  private static String replaceNewLineString(String str) {
    return str.replace("\\n", "\n");
  }

  public void appendContents(String c) {
    this.contents += File.replaceNewLineString(c);
  }

  public void setPath(Path p) {
    this.path = p;
  }

  public Path getPath() {
    String parent = this.parent.getPath().toString();
    if (!parent.endsWith(Path.PATH_DELIMITER)) {
      parent += Path.PATH_DELIMITER;
    }
    Path ret = new Path(parent + this.getName());
    return ret;
  }
  
  @Override
  public String toString() {
    if (parent != null) {
      return "File " + this.getName() + " with parent " + parent.getName()
          + ". ";
    } else {
      return "File " + this.getName() + " with parent " + parent + ". ";
    }
  }

  public static void validateName(String name) throws JShellException {
    // false by default. If any of the conditions are met, its set to true.
    boolean isValid = false;
    int counter = 0;

    for (char c : name.toCharArray()) {
      // we are converting to ASCII and using that to check validity
      int ascii = (int) c;
      if ((ascii >= 0) && (ascii <= 9)) {
        isValid = true;
      } else if ((ascii >= 65) && (ascii <= 90)) {
        isValid = true;
      } else if ((ascii >= 97) && (ascii <= 122)) {
        isValid = true;
      } else if ((ascii == 56)) {
        counter++;
      }

      // check if its not just periods
      if (counter == name.length() && isValid) {
        isValid = false;
      }

      if (!isValid) {
        throw new JShellException("Name has illegal characters. "
            + "Names may only have letters A-Z, a-z and numbers 0-9. Period on their own are not allowed\n");
      }
    }
  }
  
  
  /**
   * Returns an iterator for file which allows for line-by-line looping of the
   * file contents. A line is the substring from the starting character (non new
   * line) to the next new line character.
   */
  @Override
  public Iterator<String> iterator() {
    return new LineIterator<String>(this);
  }

  // for iterating the file contents line by line
  private static class LineIterator<String> implements Iterator<String> {
    private String[] lines;
    private int index;
    
    public LineIterator(File f){
      // split the contents by new line characters
      lines = (String[]) f.getContents().split("\n");
      index = 0;
    }
    
    @Override
    public boolean hasNext() {
      return index < lines.length;
    }

    @Override
    public String next() {
      String next = lines[index];
      index ++;
      return next;
    }

  }
}
