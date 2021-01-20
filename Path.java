// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: caojeff
// UT Student #: 1004259615
// Author: Jeff Cao
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
// UTORID user_name: shahahm6
// UT Student #: 1004192287
// Author: Ahmad Shah
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package driver;

import java.util.ArrayList;

// TODO: class JavaDoc
public class Path {
  public static final String ROOT = "/";
  public static final String PATH_DELIMITER = "/";
  public static final String PARENT_DIRECTORY_STRING = "..";
  public static final String CURRENT_DIRECTORY_STRING = ".";
  private String pathString;
  private ArrayList<String> pathArray;

  // TODO: document valid characters
  /**
   * Creates a path representing the given the string representation of a path.
   * The <code>PATH_DELIMETER</code> is used to separate the components of the
   * path.
   * <p>
   * A path is valid if: <code>Path.isAPath(path)</code> is true
   * 
   * @param path the valid string representation of a path
   */
  public Path(String path) {
    // TODO: check for valid path
    // initialize
    this.pathString = path;
    // split the path string on the right of each path delimiter
    this.pathArray = Path.splitRight(path, PATH_DELIMITER);
  }

  /**
   * Splits the string to the right of each delimiter into an array list,
   * keeping the delimiter.
   */
  private static ArrayList<String> splitRight(String s, String delimiter) {
    ArrayList<String> splittedArray = new ArrayList<>();
    int start = 0;
    // for every delimiter found, we add everything from the start to the
    // delimiter and including the delimiter to the path array; if there is
    // no delimiter left from start, then we add the remaining string
    while (start < s.length()) {
      int delimiterIndex = s.indexOf(delimiter, start);
      int end = delimiterIndex + delimiter.length();
      if (delimiterIndex == -1) {
        end = s.length();
      }
      splittedArray.add(s.substring(start, end));
      start = end;
    }
    return splittedArray;
  }

  /**
   * Returns true iff <code>string</code> can be made into a <code>Path</code>.
   * A Path can contain any characters (except whitespace) and extra delimiters.
   * 
   * @param string the string to check without any whitespace
   * @return whether the string can be made into a path
   */
  public static boolean isAPath(String string) {
    ArrayList<String> path = Path.splitRight(string, PATH_DELIMITER);
    boolean isPath = true;
    // TODO: check for extra delimiters
    // extra delimiters would be separated into their own components
    int curr = 1;
    while (curr < path.size() && isPath) {
      if (path.get(curr).equals(PATH_DELIMITER)) {
        isPath = false;
      } else {
        curr++;
      }
    }
    return isPath;
  }

  @Override
  /**
   * Returns the string identical to the one given at instantiation.
   * 
   * @return the string representation of this path
   */
  public String toString() {
    return pathString;
  }

  @Override
  /**
   * Returns true iff <code>obj</code> is a Path and has the same string
   * representation except 0 or 1 omitted path delimiter (/) at the end. For
   * example, the paths represented by "/a/" and "/a" are equal.
   * 
   * @return whether this Path and the object are equal
   */
  public boolean equals(Object obj) {
    // two paths are equal as described in the JavaDoc
    boolean sameType = obj instanceof Path;
    String thisString = this.toString();
    String objString = obj.toString();
    boolean sameRepr = thisString.equals(objString);
    // if they arent the same string, then we are left with the case of
    // one string must be 1 path delimiter symbol bigger than the other
    if (!sameRepr) {
      String longer = thisString;
      String shorter = objString;
      if (longer.length() < shorter.length()) {
        String temp = longer;
        longer = shorter;
        shorter = temp;
      }
      if (longer.endsWith(PATH_DELIMITER)) {
        sameRepr =
            longer.substring(0, longer.length() - PATH_DELIMITER.length())
                .equals(shorter);
      }
    }
    return sameType && sameRepr;
  }

  /**
   * Returns true if the path is absolute, that is it starts with the root
   * 
   * @return whether the path is absolute
   */
  public boolean isAbsolute() {
    return pathArray.get(0).equals(ROOT);
  }

  /**
   * Returns true if the path is relative, that is it does not begin at the
   * root.
   * 
   * @return whether the path is relative
   */
  public boolean isRelative() {
    // a path is either relative or absolute
    return !this.isAbsolute();
  }

  /**
   * Returns the number of items this path has. For example, the root path "/"
   * has length 1, the path "a/b/" has length 2, and the path "/a/b/c" has
   * length 4.
   * 
   * @return length of the path
   */
  public int length() {
    return this.pathArray.size();
  }

  /**
   * Returns the parent path of this path if it exists; otherwise null. For
   * example, <code>(new Path("/a/b/")).getParentPath().toString()</code>
   * returns <code>"/a/"</code>.
   * 
   * @return an absolute path is this is absolute and not the root path, or a
   *         relative path if this is relative and has a parent; otherwise null
   */
  public Path getParentPath() {
    // if the path has only one component, then it does not have a parent;
    // otherwise it must have a parent which is all the components except the
    // last one concatenated together
    Path parentPath = null;
    if (pathArray.size() > 1) {
      String parentPathString = "";
      for (int i = 0; i < pathArray.size() - 1; i++) {
        parentPathString += pathArray.get(i);
      }
      parentPath = new Path(parentPathString);
    }
    return parentPath;
  }

  /**
   * Returns the component of the path at the specified index, with the root at
   * the 0th index if the path is absolute. For example,
   * <code>(new Path("/a/b/")).getComponent(2)</code> returns "b/".
   * 
   * @param index the index of the component 0 <= index < this.length()
   * @return the name of the item at the specified index
   * @throws IndexOutOfBoundsException if <code>index</code> &lt; 0 or
   *         <code>index</code> &ge; this.length()
   */
  public String getComponent(int index) throws IndexOutOfBoundsException {
    // throw the exception if index is not within the range
    if (index < 0) {
      throw new IndexOutOfBoundsException("negative indices are invalid");
    } else if (index >= this.pathArray.size()) {
      throw new IndexOutOfBoundsException("index " + index + " is too large");
    }
    return this.pathArray.get(index);
  }

  /**
   * Gets the name of component at index i
   */
  public String getComponentName(int i) {
    String componentName = this.getComponent(i);
    if ((componentName.charAt(componentName.length() - 1) == '/')
        && (componentName.length() > 1)) {
      componentName = componentName.substring(0, componentName.length() - 1);
    }
    return componentName;
  }

  /**
   * Returns true iff component is <code>PARENT_DIRECTORY_STRING</code> or is
   * <code>PARENT_DIRECTORY_STRING + PATH_DELIMITER</code>.
   * 
   * @param component the string to check against, usually a component of a path
   * @return whether the string matches the special parent directory string
   */
  public static boolean isParentDirectory(String component) {
    return component.equals(PARENT_DIRECTORY_STRING)
        || component.equals(PARENT_DIRECTORY_STRING + PATH_DELIMITER);
  }

  /**
   * Returns true iff component is <code>CURRENT_DIRECTORY_STRING</code> or is
   * <code>CURRENT_DIRECTORY_STRING + PATH_DELIMITER</code>.
   * 
   * @param component the string to check against, usually a component of a path
   * @return whether the string matches the special current directory string
   */
  public static boolean isCurrentDirectory(String component) {
    return component.equals(CURRENT_DIRECTORY_STRING)
        || component.equals(CURRENT_DIRECTORY_STRING + PATH_DELIMITER);
  }
  
  /**
   * Adds the given path to the end of the current Path
   */
  
  public Path add(Path p) {
    return new Path(this.pathString + p.toString());
  }

}
