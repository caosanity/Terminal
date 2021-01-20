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
// UTORID user_name: shahahm6
// UT Student #: 1004192287
// Author: Ahmad Shah
//
// Student4:
// UTORID user_name: muruga12
// UT Student #: 1004020999
// Author: Ramesh Murugananthan
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package cmd;

import java.util.Arrays;
import java.util.List;
import driver.Directory;
import driver.Item;
import driver.JShell;
import driver.Path;
import err.ItemNotFoundException;
import err.JShellException;

public class MkdirCmd extends ShellCommand {

  public MkdirCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }


  public boolean hasValidArgs() {
    // if it has has one argument, it is valid
    boolean oneArg = this.getCmd().getArguments().size() == 1;
    // return the boolean value
    return oneArg;
  }

  @Override
  public String execute() throws JShellException, ItemNotFoundException {
    // TODO Auto-generated method stub
    // grab the arguments, and put them into a list
    List<String> pathList = this.getCmd().getArguments();
    String directoryName, parentPathStr = "";
    Path parentPath, currentPath;
    Directory parentDirectory, newDirectory;

    // go through all the arguments, these may be relative or absolute
    for (String pathString : pathList) {
      // check if absolute
      currentPath = new Path(pathString);
      boolean absolute = currentPath.isAbsolute();
      directoryName = currentPath.getComponentName(currentPath.length() - 1);
      
      Item.validateName(directoryName);

      // if it is absolute, we grab the path to the PARENT of the new directory
      // And the name of the new directory
      if (absolute) {
        parentPath = currentPath.getParentPath();
        
        // Using the path to the parent, we grab the actual directory at this
        // path.
        parentDirectory =
            (Directory) this.getShell().getFs().getDirectoryAtPath(parentPath);
      } else {
        if (currentPath.getParentPath() != null) {
          parentPathStr = (currentPath.getParentPath().toString());
        } else {
          parentPathStr = "";
        }
        parentPathStr = this.getShell().getFs().getCWD().getPath().toString()
            + parentPathStr;
        parentPath = new Path(parentPathStr);

        // System.out.println(parentPathStr);
        parentDirectory =
            (Directory) this.getShell().getFs().getDirectoryAtPath(parentPath);
      }

      for (Directory d : parentDirectory.getChildDirectories()) {
        if (directoryName.equals(d.getName())) {
          throw new JShellException(
              "ERROR. Directory with that name already exists.\n");
        }
      }


      // Now create the new directory and set it as the child of the parent
      // directory.
      newDirectory = new Directory(directoryName, parentDirectory);
      parentDirectory.setChildDirectory(newDirectory);
    }
    return null;
  }
}

