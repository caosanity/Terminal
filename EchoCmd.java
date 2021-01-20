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
package cmd;

import java.util.ArrayList;
import java.util.List;
import driver.Directory;
import driver.File;
import driver.Item;
import driver.JShell;
import driver.Path;
import err.ItemNotFoundException;
import err.JShellException;

public class EchoCmd extends ShellCommand {

  // constructor
  public EchoCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }
  // // a method to determine if it has proper arguments
  // public boolean hasValidArgs() {
  // // can have 2 arguments or one
  // int numArgs = cmd.getArgs().length
  // // if there is one argument
  // if (numArgs == 1):
  // this.echoWithoutFile();
  // // if there is two arguments
  // if (numArgs == 2): {
  // this.echoWithFile();
  // }
  // // return if valid args and options have been given or not
  // return (numArgs == 2) || (numArgs==1);
  // }

  private void fileOverwrite(String contents, String name, Path path) throws ItemNotFoundException {
    // holds the file name
    String fileName = name;
    File currentFile = null;

    // have parent folder directory given the path
    Directory parentFolder =
        (Directory) this.getShell().getFs().getItemAtPath(path);

    ArrayList<File> files = parentFolder.getChildFiles();
    boolean alreadyExists = false;

    for (File f : files) {
      if (f.getName().equals(fileName)) {
        alreadyExists = true;
        currentFile = f;
      }
    }

    if (alreadyExists) {
      currentFile.setContents(contents);
    } else {
      // create a new file with the filename and the directory
      File newFile = new File(fileName, parentFolder);
      // give the file the contents
      newFile.setContents(contents);
      // sets the child file
      parentFolder.setChildFile(newFile);
    }
  }

  private void fileAppend(String contents, String name, Path path) throws ItemNotFoundException {
    // holds the file name
    String fileName = name;
    File currentFile = null;

    // have parent folder directory given the path
    Directory parentFolder =
        (Directory) this.getShell().getFs().getItemAtPath(path);

    ArrayList<File> files = parentFolder.getChildFiles();
    boolean alreadyExists = false;

    for (File f : files) {
      if (f.getName().equals(fileName)) {
        alreadyExists = true;
        currentFile = f;
      }
    }

    if (alreadyExists) {
      currentFile.appendContents(contents);
    } else {
      // create a new file with the filename and the directory
      File newFile = new File(fileName, parentFolder);
      // give the file the contents
      newFile.appendContents(contents);
      // sets the child file
      parentFolder.setChildFile(newFile);
    }
  }

  @Override
  public String execute() throws JShellException, ItemNotFoundException {
    // TODO Auto-generated method stub
    List<String> arguments = this.getCmd().getArguments();
    Path parentPath;
    String fileName, contents = arguments.get(0);

    // ensure that STRING is encircled by quotation marks and grab the contents
    if (contents.charAt(0) == '"'
        && contents.charAt(contents.length() - 1) == '"') {
      contents = contents.substring(1, contents.length() - 1);
    } else {
      throw new JShellException(
          "STRING must be surrounded with quotation marks\n");
    }

    // return string; only outputs when not redirecting
    String ret = null;
    // case where no arguments are given > should print out string
    if (arguments.size() == 1) {
      ret = arguments.get(0) + "\n";
    } else {
      String pathString = arguments.get(2);
      Path currentPath = new Path(pathString);

      // if pathString isn't relative we get the place where to create the file
      // and the file name. Similar for relative paths.
      if (pathString.contains("/")) {
        parentPath = currentPath.getParentPath();
        // System.out.println(parentPath);
        fileName = currentPath.getComponent(currentPath.length() - 1);
      } else {
        parentPath = this.getShell().getFs().getCWD().getPath();
        fileName = arguments.get(2);
      }

      Item.validateName(fileName);

      // apply the appropriate helper method (append or overwrite)
      if (arguments.get(1).equals(">")) {

        this.fileOverwrite(contents, fileName, parentPath);

      } else if (arguments.get(1).equals(">>")) {

        this.fileAppend(contents, fileName, parentPath);

      } else {
        throw new JShellException(
            "Invalid format. Must be of the form String >/>> OUTFILE\n");
      }
    }
    return ret;
  }


  public boolean hasValidArgs() {
    // TODO Auto-generated method stub
    return false;
  }
}
