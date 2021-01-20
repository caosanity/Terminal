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

import java.util.List;
import driver.Directory;
import driver.File;
import driver.FileSystem;
import driver.JShell;
import driver.Path;
import err.ItemNotFoundException;
import err.JShellException;

public class FindCmd extends ShellCommand {

  public FindCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }
  
  /**
   * The purpose of this method is to execute the find command.
   * @return 
   * 
   * @throws JShellException The general class for exceptions
   */
  @Override
  public String execute() throws JShellException, ItemNotFoundException {
    // Check for errors
    try {
      this.validate();
    } catch (JShellException e) {
      throw e;
    }
    List<String> args = this.getCmd().getArguments();
    String message = "";
    this.getShell();
    this.getShell().getFs();
    for (int i = 0; i < (args.size() - 4); i++) {
      Path path = getFullPath(new Path(args.get(i)));
      // Check if it is a file
        if (args.get(args.size() - 3).equals("f")) {
          // Get the file name, make the file as a path
          String fileChecker = args.get(args.size() - 1);
          fileChecker = fileChecker.substring(1, fileChecker.length() - 1);
          Path f = new Path(rootCheck(args.get(i)) + fileChecker);
          File file = new File("", null);
          // Get the file from the given path if it exists, otherwise error
          try {
            file = FileSystem.getFileAtPath(path.add(f));
          } catch (ItemNotFoundException e) {
            //TODO: incorporate the error message into the output msg instead
            this.getShell().getErrorOut().printErrorMsg("Error: The file does not exist at this path\n");
          }
          // Check if the file given is equal to the file retrieved, and return
          // the appropriate message
            if (fileChecker.equals(file.getName())) {
              message += "The file was found at the path: " + args.get(i) + "\n";
            }
          }
        // Otherwise check if it is a directory
        else if (args.get(args.size() - 3).equals("d")) {
          // Get the directory name, make the directory as a path
          String directoryChecker = args.get(args.size() - 1);
          directoryChecker = directoryChecker.substring(1, directoryChecker.length() - 1);
          Path d = new Path(rootCheck(args.get(i)) + directoryChecker);
          Directory dir = new Directory("", null);
          // Get the directory from the given path, otherwise error
          try {
            dir = FileSystem.getDirectoryAtPath(path.add(d));
            } catch (ItemNotFoundException e) {
              //TODO: incorporate the error message into the output msg instead
              this.getShell().getErrorOut().printErrorMsg("Error: The directory does not exist at this path\n");
            }
          
          // Check if the directory given is equal to the directory retrieved,
          // and return the appropriate message
          if (directoryChecker.equals(dir.getName())) {
            message += "The directory was found at the path: " + args.get(i) + "\n";
          }
        }
      }
    // Return the appropriate messages
    return message;
  }
  /**
   * The purpose of this method is to check if a path is absolute, and if not,
   * get and return the absolute path.
   * 
   * @param path A path entered by the user
   * 
   * @return The absolute path (all directories leading up to the last
   *         directory/file, starting from he root)
   */
  public Path getFullPath(Path path) {
    if (path.isRelative()) {
      // check if path is absolute or relative and change all relative paths to
      // absolute paths
      if (FileSystem.getCWD().getPath().getParentPath() != null) {
        path = FileSystem.getCWD().getPath().getParentPath().add(path);
        }
      else {
        // If the parent of the relative directory is the root, we just add the
        // "/" before the relative path
        path = FileSystem.getRoot().getPath().add(path);
      }
    }
    return path;
  }
  /**
   * Takes the string of a path and checks if the last symbol is the root 
   * symbol, then returns either an empty string or the root.
   * 
   * @param path The string representation of a path
   * 
   * @return An empty string or the root representation
   */
  public String rootCheck(String path) {
    // Check if the last index of the string is a "/" and return it if not
    if (path.charAt(path.length() - 1) == '/') {
      return "";
    }
    else {
      return "/";
    }
    
  }
  
  //TODO: explain what the exception is being thrown for
  /**
   * Checks that the number of arguments are correct and the arguments given
   * are of proper format and type
   * 
   * @throws JShellException The general class for exceptions
   */
  private void validate() throws JShellException {
    List<String> args = this.getCmd().getArguments();
    // Command should have greater than 4 arguments
    if (!this.hasValidArgs()) {
      throw new JShellException("ERROR: find: There is an "
          + "incorrect number of " + "arguments\n");
    }
    // Command should have the proper format
    if (!(args.get(args.size() - 4).equals("-type")) ||
        !(args.get(args.size() - 2).equals("-name"))) {
      throw new JShellException("ERROR: find: The arguments were entered in an "
          + "improper format\n");
    }
    // Command should have proper type
    if (!(args.get(args.size() - 3).equals("d")) &&
        !(args.get(args.size() - 3).equals("f"))) {
      throw new JShellException("ERROR: find: The type entered is incorrect\n");
    }
  }
  

  /**
   * The purpose of this method is to check if the find method has the
   * correct number of arguments
   * 
   * @return Whether the arguments are valid or not
   */
  public boolean hasValidArgs() {
    // if it has greater than 4 arguments
    boolean onePath = this.getCmd().getArguments().size() >= 5;
    // return boolean value 
    return onePath;
    }
}