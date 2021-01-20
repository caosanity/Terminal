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
// UT Student #: 100419227
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

import java.util.Set;
import driver.JShell;
import java.util.HashSet;

/**
 * This class provides information about shell commands.
 * <p>
 * It can be used to determine if a shell command exists, return the shell
 * command object that corresponds to a given name, check if a shell command
 * is trackable in <code>History</code>, and check if a shell command allows
 * redirection.
 * <p>
 * This class is used by <code>JShell</code> to retrieve information about shell
 * commands.
 * 
 * @author Adam Wu
 */
public class CommandCentre {
  // have a set that contains all of the command names
  private static Set<String> cmdNames = new HashSet<String>();
  // stores the names of shell commands whose history shouldnt be tracked
  private static HashSet<String> untrackableNames = new HashSet<>();
  private static HashSet<String> nonRedirectables = new HashSet<>();

  public CommandCentre() {
    // map command name to the shell command class name
    CommandCentre.initializeCommands();
    // add untrackable names to the set
    CommandCentre.initializeUntrackableNames();
    CommandCentre.initalizeNonRedirectables();
  }

  private static void initializeCommands() {
    // add all these command names to the set
    cmdNames.add("exit");
    cmdNames.add("mkdir");
    cmdNames.add("cd");
    cmdNames.add("ls");
    cmdNames.add("pwd");
    cmdNames.add("pushd");
    cmdNames.add("popd");
    cmdNames.add("history");
    cmdNames.add("cat");
    cmdNames.add("echo");
    cmdNames.add("man");
    cmdNames.add("find");
    cmdNames.add("tree");
    cmdNames.add("!");
    cmdNames.add("mv");
    cmdNames.add("cp");
    cmdNames.add("curl");
    cmdNames.add("grep");
  }

  // adds the untrackable names to the set of untrackable names
  private static void initializeUntrackableNames() {
    untrackableNames.add("!");
  }

  private static void initalizeNonRedirectables() {
    nonRedirectables.add("exit");
  }
  
  public boolean commandCheck(String command_name) {
    // return true or false whether the command is a valid command or not
    return (cmdNames.contains(command_name));
  }

  // creates the shell command
  public ShellCommand createShellCommand(Command cmd, JShell sh) {
    ShellCommand shCmd = null;
    String cmdName = cmd.getName();
    switch (cmdName) {
      case "exit":
        shCmd = new ExitCmd(cmd, sh);
        break;
      case "mkdir":
        shCmd = new MkdirCmd(cmd, sh);
        break;
      case "cd":
        shCmd = new CdCmd(cmd, sh);
        break;
      case "ls":
        shCmd = new LsCmd(cmd, sh);
        break;
      case "pwd":
        shCmd = new PwdCmd(cmd, sh);
        break;
      case "pushd":
        shCmd = new PushdCmd(cmd, sh);
        break;
      case "popd":
        shCmd = new PopdCmd(cmd, sh);
        break;
      case "history":
        shCmd = new HistoryCmd(cmd, sh);
        break;
      case "cat":
        shCmd = new CatCmd(cmd, sh);
        break;
      case "echo":
        shCmd = new EchoCmd(cmd, sh);
        break;
      case "man":
        shCmd = new ManualCmd(cmd, sh);
        break;
      case "find":
        shCmd = new FindCmd(cmd, sh);
        break;
      case "tree":
        shCmd = new TreeCmd(cmd, sh);
        break;
      case "!":
        shCmd = new PastCmd(cmd, sh);
        break;
      case "mv":
        shCmd = new MvCmd(cmd, sh);
        break;
      case "cp":
        shCmd = new CpCmd(cmd, sh);
        break;
      case "curl":
        shCmd = new CurlCmd(cmd, sh);
        break;
      case "grep":
        shCmd = new GrepCmd(cmd, sh);
        break;
      default:
        // TODO: do smth later for invalid cmd
    }
    return shCmd;
  }

  /**
   * Returns true iff <code>name</code> is the name of a shell command whose
   * history should not be tracked.
   * 
   * @param name the name to check
   * @return true if the command should not be added to history; otherwise false
   */
  public boolean isUntrackable(String name) {
    // return whether name is in the table
    return untrackableNames.contains(name);
  }
  
  /**
   * Returns true iff <code>name</code> is the name of a shell command that
   * does not allow redirection.
   * 
   * @param name name to check
   * @return true if redirection is possible, false otherwise
   */
  public boolean isNonRedirectable(String name) {
    return nonRedirectables.contains(name);
  }
}
