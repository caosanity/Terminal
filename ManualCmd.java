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

import driver.JShell;
import err.JShellException;

public class ManualCmd extends ShellCommand {
  // String variables of each documentation able to be accessed in the program
  private String manDoc = "This is the manual for man, which prints "
      + "documentation of a command\nExample: man exit\nThis is the manual for "
      + "exit, which quits the current program\nThis command takes in only one "
      + "argument.";
  private String exitDoc = "This is the manual for exit, which quits the "
      + "current program\nExample: exit\n \nThis command takes in no arguments";
  private String pwdDoc = "This is the manual for pwd, which prints the full "
      + "path of the current working directory\nExample: pwd\n/(root directory)"
      + "\nThis comand takes in no arguments.";
  private String mkdirDoc = "This is the manual for mkdir, which makes new "
      + "directories at the given path\nExample: pwd\n/\nmkdir /a/\npwd\n/a/\n"
      + "This command takes in one argument.";
  private String lsDoc = "This is the manual for ls, which prints the contents "
      + "of a directory/multiple directories\nor the name of the file(s) itself"
      + "\nExample: ls /\na (if 'a' was a file in '/')\nls a\na ('a' is a file)"
      + "\nThis command takes in no arguments.";
  private String cdDoc = "This is the manual for cd, which changes the current "
      + "directory to the given directory.\nThe given directory may be "
      + "either part of the same path or a different path\nExample: cd a/ "
      + "(the current directory is '/',same path)\n(after the change the "
      + "current directory is a/)\ncd /b/(different path)\n(after the "
      + "change the current directory is /b)\nThis command takes in one "
      + "argument.";
  private String pushdDoc = "This is the manual for pushd, which pushes the "
      + "current working directory into a stack and\nchanges the current "
      + "working directory to the inputed directory\nExample: pushd / "
      + "(current directory is '/a/')\n(after push the current directory "
      + "is '/' and the dStack contains 'a/')\nThis command takes in one "
      + "argument.";
  private String popdDoc = "This is the manual for popd, which pops the top of "
      + "the directory stack and changes the\ncurrent working directory to it\n"
      + "Example: popd (the current directory is '/' and the dStack "
      + "contains 'a/')\n(after pop the current directory is '/a/' and the "
      + "dStack is empty)\nThis command takes in no arguments";
  private String historyDoc = "This is the manual for histroy, returns "
      + "previously entered commands in the order in which they occured\n"
      + "Example: history (previous commands are ls, pwd)\n1. ls\n2. pwd\n3. "
      + "history\nhistory 2\n3. history\n4. history 2\nThis command takes in "
      + "either one or no arguments.";
  private String catDoc = "This is the manual for cat, which concatanates and "
      + "prints file contents\nExample:cat f (file f contains \"hello hi\")"
      + "\nhello hi\nThis command takes in one argument.";
  private String echoDoc = "This is the manual for echo, which creates and "
      + "writes the given string to a file, appends\na string to a file(>>), "
      + "overwrites a file with a string(>) or writes a string to the "
      + "shell\nExample: echo\"me\" > f (file f contains \"hello hi\")\n"
      + "(after echo f contains \"me\")\necho \" you\" >> f\n(after echo f "
      + "contains \"me you\")\necho \"hi\"\n\"hi\"\nThis command takes in "
      + "one or three arguments.";
  private String findDoc = "This is the manual for find, which searches for "
      + "files ;in a directory hierarchy\nExample: find /a/ -type d -name "
      + "\"abc\" (finds all directories with name \"abc\" in \"a/\" directory)"
      + "\nfind /a/ -type f -name \"xyz\"(finds all files with name \"xyz\" "
      + "in \"a/\" directory)\nfind /a/ /b/ -type d -name \"def\"(finds all"
      + " dictionaries with name \"def\" within dictionary a/ and b/)"
      + "\nThis command takes three main arguments(directory/ies, type, "
      + "name), you can input\nas many dictionaries as you would like.";
  private String treeDoc = "This is the manual for tree, which lists contents "
      + "of directories in a tree format\nExample: tree (directories are the "
      + "root and its child 'a')\n/\n a\n The command takes in no arguments";
  private String mvDoc = "This is the manual for move, which moves an item "
      + "oldpath to the newpath, both of the\npaths can be either a relative "
      + "or an absolute path\nExample: mv /oldpath/ /newpath/\nThen it becomes "
      + "/newpath/oldpath/ and /oldpath/ no longer exists\nThis command takes "
      + "in two arguments.";
  private String cpDoc = "This is the manual for copy, which copies an item "
      + "oldpath to the newpath, both of the\npaths can be either a relative "
      + "or an absolute path\nExample: cp /oldpath/ /newpath/\nThen it becomes "
      + "/newpath/oldpath/ and /oldpath/ is still there\nThis command takes in "
      + "two arguments.";
  private String curlDoc = "This is the manual for curl, it gets a file from a "
      + "given url location and add it to the current working\ndirectory\n"
      + "Example: curl http://www.somewebsite/user/a.txt\nThis creates a file "
      + "a.txt in the current directory\nThis command takes in one argument.";
  private String pastDoc = "This is the manual for !, it gets a previously "
      + "specified command from the history\nExample: mkdir a\nhistory\n!2\n"
      + "this executes the history command again\nThis command takes in one "
      + "argument.";
  private String grepDoc ="This is the manual for grep, it prints all the "
      + "lines containing REGEX from the given\nfile if -R is not specified in "
      + "the call of the command. Otherwise if -R is specified\nand a "
      + "directory is given, traverse the directory and for all files which "
      + "contain\nREGEX, print the path to the file followed by the lines "
      + "containing REGEX in that file\nExample:\n1) grep /a/b (b is a file)\n"
      + "regex\n2) grep/a (a contains files b, c d)\n"
      + "/a/b: regex\n/a/d: \\d\nThis command takes in one or two arguments.";

  /**
   * Constructs the Manual command with inputs of a Command and JShell.
   * 
   * @param cmd The given command
   * @param sh The given shell
   */
  public ManualCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }

  /**
   * This method returns a string of the documentation for each command
   * available for this assignment.
   * 
   * @param cmd A string representation of any and all the commands
   * 
   * @return documentation A string representation of the cmd's doc-string
   */
  public String getManual(String cmd) {
    String documentation = "";
    // Check if the command is one from the assignment, and get its
    // documentation if it is
    if (cmd.equals("man")) {
      documentation = manDoc;
    } else if (cmd.equals("exit")) {
      documentation = exitDoc;
    } else if (cmd.equals("pwd")) {
      documentation = pwdDoc;
    } else if (cmd.equals("mkdir")) {
      documentation = mkdirDoc;
    } else if (cmd.equals("ls")) {
      documentation = lsDoc;
    } else if (cmd.equals("cd")) {
      documentation = cdDoc;
    } else if (cmd.equals("pushd")) {
      documentation = pushdDoc;
    } else if (cmd.equals("popd")) {
      documentation = popdDoc;
    } else if (cmd.equals("history")) {
      documentation = historyDoc;
    } else if (cmd.equals("cat")) {
      documentation = catDoc;
    } else if (cmd.equals("echo")) {
      documentation = echoDoc;
    } else if (cmd.equals("find")) {
      documentation = findDoc;
    } else if (cmd.equals("tree")) {
      documentation = treeDoc;
    } else if (cmd.equals("mv")) {
      documentation = mvDoc;
    } else if (cmd.equals("cp")) {
      documentation = cpDoc;
    } else if (cmd.equals("curl")) {
      documentation = curlDoc;
    } else if (cmd.equals("!")) {
      documentation = pastDoc;
    } else if (cmd.equals("grep")) {
      documentation = grepDoc;
    }
    // Otherwise return a statement of unavailable command
    else {
      documentation += "There is no manual for the given command";
    }
    return documentation;
  }

  /**
   * The purpose of this method is to execute the manual command.
   * 
   * @return the string representation of the commands manual   
   *
   * @throws JShellException The general class for exceptions
   */
  @Override
  public String execute() throws JShellException {
    // Check for errors
    try {
      this.validate();
    } catch (JShellException e) {
      throw e;
    }
    // Return the command manual
    String ret = "";
    String cmdName = this.getCmd().getArguments().get(0);
    ret += this.getManual(cmdName) + "\n";
    return ret;
  }


  /**
   * Checks that the number of arguments are correct
   * 
   * @throws JShellException The general class for exceptions
   */
  private void validate() throws JShellException {
    // command should have one argument otherwise error
    if (!this.hasValidArgs()) {
      throw new JShellException(
          "ERROR: man: There is an incorrect number of " + "arguments\n");
    }
  }

  /**
   * The purpose of this method is to check if the manual method has the correct
   * number of arguments
   * 
   * @return whether the arguments are valid
   */
  public boolean hasValidArgs() {
    // Get if manual has one argument
    boolean oneArg = this.getCmd().getArguments().size() == 1;
    return oneArg;
  }
}
