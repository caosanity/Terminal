// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: muruga12
// UT Student #: 1004020999
// Author: Ramesh Murugananthan
//
// Student2:
// UTORID user_name: wuadam
// UT Student #: 1004266741
// Author: Adam Wu
//
// Student3: 
// UTORID user_name: caojeff
// UT Student #: 1004259615
// Author: jeff cao
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

package cmd;

import driver.JShell;
import driver.Path;
import err.ItemNotFoundException;

public class CdCmd extends ShellCommand{
  private String dir;
  public CdCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }
  public String findDirectory(String directory) {
    if (directory == "") {
        dir = "";
    }
    else {
      return "ERROR MESSAGE";
    }
    return dir;
  }
  public void changeDirectory(String directory) {
    directory = findDirectory(directory);
  }
  @Override
  public String execute() throws ItemNotFoundException {
    Path path = new Path(this.getCmd().getArguments().get(0));
    this.getShell().getFs().changeDirectory(path);
    return null;
  }
  
}
