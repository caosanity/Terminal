// **********************************************************
// Assignment2:
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
// Student4: caojeff
// UTORID user_name: 1004259615
// UT Student #: caojeff
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package cmd;

import java.util.List;
import driver.Directory;
import driver.File;
import driver.Item;
import driver.JShell;
import driver.Path;
import err.InvalidArgumentException;
import err.InvalidPathException;
import err.ItemNotFoundException;

/**
 * A shell command that prints the contents of specified files when executed.
 * 
 * @author Adam Wu
 */
public class CatCmd extends ShellCommand {

  /**
   * The separator (three new line characters) used when concatenating multiple
   * file contents.
   */
  public static final String SEPARATOR = "\n\n\n";

  /**
   * Creates a shell command that interacts with <code>cmd</code> and
   * <code>sh</code>.
   * 
   * @param cmd object with arguments
   * @param sh the shell object
   */
  public CatCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }

  /**
   * Returns the contents of each file argument given, separated by three line
   * breaks. If a path is invalid, an error message replaces the position where
   * its file contents would go.
   * 
   * @return the contents of each specified file
   * @throws InvalidArgumentException if no arguments are given
   * @throws InvalidPathException if any argument is not a path
   */
  @Override
  public String execute()
      throws InvalidArgumentException, InvalidPathException {
    // check the command has correct arguments
    if (!hasCorrectNumberOfArgs()) {
      throw new InvalidArgumentException(
          "ERROR: cat: must have at least 1 path to a file\n");
    }
    if (!hasCorrectArgTypes()) {
      throw new InvalidPathException(
          "ERROR: cat: at least one argument isn't a path\n");
    }
    // get the file contents for every file, or an error message when its
    // invalid
    String ret = "";
    for (String pathString : this.getCmd().getArguments()) {
      try {
        String contents = this.getFileContents(pathString);
        ret += (contents + SEPARATOR);
      } catch (ItemNotFoundException e) {
        ret += e.getMessage();
      } catch (InvalidPathException e) {
        ret += e.getMessage();
      }
    }
    // remove extra separator if there is one
    if (ret.endsWith(SEPARATOR)) {
      ret = ret.substring(0, ret.length() - SEPARATOR.length());
    }
    // add new line
    ret += "\n";
    return ret;
  }

  /**
   * Gets the contents stored in the file at the given path.
   * 
   * @param path the string representation of the path to a file; must be a path
   *        according to <code>Path.isAPath</code>
   * @return the file contents
   * @throws ItemNotFoundException if the item at this path does not exist
   * @throws InvalidPathException if the path points to a directory
   */
  public String getFileContents(String path)
      throws ItemNotFoundException, InvalidPathException {
    // validate before adding onto contents, throwing an error where invalid
    String contents = "";
    Path p = new Path(path);
    Item item;
    // an item must exist in the system
    try {
      item = this.getShell().getFs().getItemAtPath(p);
    } catch (ItemNotFoundException e) {
      throw new ItemNotFoundException(
          "the file " + p + " does not exist in the file system\n");
    }
    // an item must be a file
    if (item instanceof Directory) {
      throw new InvalidPathException("the item " + p + " is a directory\n");
    }
    contents += ((File) item).getContents();
    return contents;
  }

  /**
   * Returns true iff the number of arguments is > 0 and all of the arguments
   * are a path.
   * 
   * @return whether the arguments are valid
   */
  public boolean hasValidArgs() {
    return hasCorrectNumberOfArgs() && hasCorrectArgTypes();
  }

  private boolean hasCorrectNumberOfArgs() {
    return this.getCmd().getArguments().size() > 0;
  }

  private boolean hasCorrectArgTypes() {
    // all arguments must be a path
    boolean allPaths = true;
    int curr = 0;
    List<String> arguments = this.getCmd().getArguments();
    while (curr < arguments.size() && allPaths) {
      if (!Path.isAPath(arguments.get(curr))) {
        allPaths = false;
      } else {
        curr++;
      }
    }
    return allPaths;
  }

}
