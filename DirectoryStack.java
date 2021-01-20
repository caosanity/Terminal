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
// Student1:
// UTORID user_name: wuadam
// UT Student #: 1004266741
// Author: Adam Wu
//
// Student2:
// UTORID user_name: muruga12
// UT Student #: 1004020999
// Author: Ramesh Murugananthan
//
// Student3:
// UTORID user_name: shahahm6
// UT Student #: 1004192287
// Author: Ahmad Shah
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

import java.util.ArrayDeque;

// for storing absolute paths to directories in a LIFO fashion
public class DirectoryStack {
  // stack of absolute paths to directories
  // TODO: might change string to path
  private ArrayDeque<Path> dStack;

  /**
   * Creates an empty stack that will hold absolute paths to directories.
   */
  public DirectoryStack() {
    dStack = new ArrayDeque<>();
  }

  /**
   * Returns the size of this stack.
   * 
   * @return the number of absolute paths stored
   */
  public int size() {
    return this.dStack.size();
  }

  // TODO: handle relative path case
  /**
   * Adds <code>path</code> to the top of the stack.
   * 
   * @param path the absolute path to a directory
   */
  public void push(Path path) {
    this.dStack.push(path);
  }

  // TODO: handle empty stack later
  /**
   * Returns the most recently pushed path stored in this stack.
   * 
   * @return the absolute path to a directory
   */
  public Path pop() {
    return this.dStack.pop();
  }

  /** Removes all the paths stored in this stack */
  public void clear() {
    dStack.clear();
  }
}
