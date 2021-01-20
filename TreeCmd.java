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
// Author: jeff cao
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
import driver.*;

public class TreeCmd extends ShellCommand {

  public TreeCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }

  @Override
  public String execute() {
    Directory root = this.getShell().getFs().getRoot();
    String outString = treeMaker(root, " ");
    return outString;
  }

  /**
   * Private method for implementing trees
   * 
   * @param d input directory (can assume will always be root)
   * @param indent level of indentation
   * @return finished tree
   */
  private String treeMaker(Directory d, String indent) {
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
          outString += (indent + treeMaker((Directory) child, indent + " "));
        } else {
          outString += (indent + child.getName() + "\n");
        }
      }
    }
    return outString;
  }

  public boolean hasValidArgs() {
    // if it has has zero argument, it is valid
    boolean oneArg = this.getCmd().getArguments().size() == 0;
    // return the boolean value
    return oneArg;
  }

}
