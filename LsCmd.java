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
package cmd;

import java.util.ArrayList;
import java.util.List;
import driver.Directory;
import driver.Item;
import driver.JShell;
import driver.Path;
import err.ItemNotFoundException;
import err.JShellException;

public class LsCmd extends ShellCommand {

  public LsCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }

  @Override
  public String execute() throws JShellException, ItemNotFoundException {
    List<String> arguments = this.getCmd().getArguments();
    String ret = "";
    try {
      // if its size 0, just print the cwd
      if (arguments.size() > 0) {
        // check if we need to recursively list the directories
        boolean recursive = arguments.get(0).equals("-R");
        if (recursive) {
          for (int i = 1; i < arguments.size(); i++) {
            // grab the current path
            String currPathString = arguments.get(i);
            Path currPath = new Path(currPathString);
            // grab the item at this path
            Item currItem = this.getShell().getFs().getItemAtPath(currPath);
            // Check if the current item is a directory or file.
            // we will return different things based on what the type is, as per
            // requirements.
            if (currItem instanceof Directory) {
              if (currPath.isRelative()) {
                // add the current path + colon like you're supposed to
                ret += this.getShell().getFs().getCWD().getPath().toString()
                    + currPathString + ":\n";
              } else {
                ret += currPathString + ":\n";
              }
              // add the file contents
              ret += getAllSubdirectories((Directory) currItem, " ") + "\n";
              // if its a file we need to just print the path
            } else {
              ret += this.getShell().getFs().getCWD().getPath().toString()
                  + currPathString + "\n";
            }
          }
          // if its not recursive we repeat but display the contents differently
          // the major change is the for loop
        } else {
          for (String currPathString : arguments) {
            // code is almost identical to one above
            // I will only comment on the differences
            Path currPath = new Path(currPathString);
            Item currItem = this.getShell().getFs().getItemAtPath(currPath);
            if (currItem instanceof Directory) {
              if (currPath.isRelative()) {
                ret += this.getShell().getFs().getCWD().getPath().toString()
                    + currPathString + ":\n";
              } else {
                ret += currPathString + ":\n";
              }
              // we use the getChildrenNames method here instead
              ret += ((Directory) currItem).getChildrenNames() + "\n";
            } else {
              ret += this.getShell().getFs().getCWD().getPath().toString()
                  + currPathString + "\n";
            }
          }
        }
        // if no arguments were given just display contents of the CWD
      } else {
        ret += this.getShell().getFs().getCWD().getChildrenNames() + "\n";
      }
    } catch (ItemNotFoundException e) {
      throw new JShellException("Path not found");
    }
    return ret;
  }

  /**
   * Private method for implementing trees
   * 
   * @param d input directory (can assume will always be root)
   * @param indent level of indentation
   * @return finished tree
   */
  private String getAllSubdirectories(Directory d, String indent) {
    // will use recursion to build tree
    String outString = "";
    // base case > when no children, return directory name
    if (d.getChildren().size() == 0) {
      outString += d.getName() + "\n";
      // otherwise go through each child and grab their name and recursively
      // call function till task is donee.
    } else {
      outString = d.getName() + "\n";
      ArrayList<Item> children = d.getChildren();
      for (Item child : children) {
        if (child instanceof Directory) {
          outString +=
              (indent + getAllSubdirectories((Directory) child, indent + " "));
        } else {
          outString += (indent + child.getName() + "\n");
        }
      }
    }
    return outString;
  }


  /**
   * Returns true iff every argument is a path.
   */

  public boolean hasValidArgs() {
    boolean allPaths = true;
    List<String> args = this.getCmd().getArguments();
    int curr = 0;
    while (curr < args.size() && allPaths) {
      if (!Path.isAPath(args.get(curr))) {
        allPaths = false;
      } else {
        curr++;
      }
    }
    return allPaths;
  }

  private void validate() throws JShellException {
    // there shouldnt be > 0 arguments
    if (!this.hasValidArgs()) {
      throw new JShellException(
          "ERROR: ls: at least one of the arguments isn't a path\n");
    }
  }

}
