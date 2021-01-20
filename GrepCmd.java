package cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import driver.Directory;
import driver.File;
import driver.Item;
import driver.JShell;
import driver.Path;
import err.InvalidArgumentException;
import err.InvalidPathException;
import err.ItemNotFoundException;

/**
 * This shell command finds the lines in a file that contain a substring that
 * matches a given regex, where regex behaves as described in
 * <code>java.util.regex.Pattern</code>, and returns the matching lines. *The
 * regex may contain whitespace characters if its given as a string (enclosed
 * with double quotes)*
 * <p>
 * This command also recursively traverses the given directory or directories if
 * -R is supplied to the command, which checks every file it finds.
 * 
 * @author Adam Wu
 */
public class GrepCmd extends ShellCommand {

  /**
   * Constructs a new shell command with the given arguments in
   * <code>command</code>, interacting with the shell <code>shell</code>.
   * 
   * @param command contains the given arguments
   * @param shell the interacting shell
   */
  public GrepCmd(Command command, JShell shell) {
    this.setCmd(command);
    this.setShell(shell);
  }

  /**
   * If -R is not supplied in the <code>Command</code> set to this shell
   * command, returns the line(s) in the given file(s) that contain the given
   * regex. The path(s) must refer to a file in this case.
   * <p>
   * If -R is supplied, then for each directory: recursively traverses every
   * file in this directory and subdirectory, and returns every line that
   * contains the given regex, preceded by the path to the file containing the
   * line and a colon; for each file given: returns the normal output as
   * described previously.
   * <p>
   * *The regex from input must not contain any whitespace characters (\\s)*
   * 
   * @return the output string of this shell command
   * @throws InvalidArgumentException if there < 2 arguments when not given -R
   *         or < 3 arguments when given -R
   * @throws InvalidPathExcpetion if at least one path argument is not a valid
   *         path or if -R is given, all the paths are not files
   * @throws ItemNotFoundException if an item does not exist in the file system
   */
  @Override
  public String execute() throws InvalidArgumentException, InvalidPathException,
      ItemNotFoundException {
    // validate the args
    try {
      this.validate();
    } catch (InvalidArgumentException e) {
      throw e;
    } catch (InvalidPathException e) {
      throw e;
    }
    // get output of the command, throwing an exception if invalid
    String ret = "";
    List<String> args = this.getCmd().getArguments();
    try {
      if (args.get(0).equals("-R")) {
        ret += this.getRecursiveOutput(args);
      } else {
        ret += this.getOutput(args);
      }
    } catch (InvalidPathException e) {
      throw e;
    } catch (ItemNotFoundException e) {
      throw e;
    }
    return ret;
  }


  private String getOutput(List<String> args)
      throws InvalidPathException, ItemNotFoundException {
    // get the regex and prepare the regex; given regex shud be a substring
    String regex = ".*" + args.get(0) + ".*";
    Pattern pattern = Pattern.compile(regex);
    // for each path listed, if its not a file then throw an exception;
    // else add the matching lines to the output
    String matches = "";
    int curr = 1;
    while (curr < args.size()) {
      Path p = new Path(args.get(curr));
      Item f;
      try {
        f = this.getShell().getFs().getItemAtPath(p);
      } catch (ItemNotFoundException e) {
        throw new ItemNotFoundException(
            "ERROR: grep: " + p + " was not found\n");
      }
      if (f instanceof Directory) {
        throw new InvalidPathException(
            "EROOR: grep: " + p + " is not a file\n");
      }
      matches += GrepCmd.getMatchingLines(pattern, (File) f);
      curr++;
    }
    // return the matching lines or null if none found
    return matches;
  }

  private static String getMatchingLines(Pattern pattern, File f) {
    // return the matching lines in file, where a line is everything up to
    // a new line character
    String matches = "";
    for (String line : f) {
      boolean match = pattern.matcher(line).matches();
      if (match) {
        matches += line + "\n";
      }
    }
    return matches;
  }

  private String getRecursiveOutput(List<String> args)
      throws ItemNotFoundException {
    // get the regex and prepare the regex; given regex shud be a substring
    String regex = ".*" + args.get(1) + ".*";
    Pattern pattern = Pattern.compile(regex);
    // if an item doesnt exist, throw the exception
    // otherwise: if its a directory, get all the files in the directory and its
    // subdirectories and then for each file, add matching lines following
    // the path to the file and a colon; else its a file, get normal output
    String matches = "";
    int curr = 2;
    while (curr < args.size()) {
      Path p = new Path(args.get(curr));
      Item item;
      try {
        item = this.getShell().getFs().getItemAtPath(p);
      } catch (ItemNotFoundException e) {
        throw new ItemNotFoundException(
            "ERROR: grep: " + p + " was not found\n");
      }
      if (item instanceof File) {
        matches += GrepCmd.getMatchingLines(pattern, (File) item);
      } else {
        Directory dir = (Directory) item;
        ArrayList<File> files = GrepCmd.getAllFilesRecursive(dir);
        for (File file : files) {
          matches += GrepCmd.getPathAndMatchingLines(pattern, file);
        }
      }
      curr++;
    }
    return matches;
  }

  private static ArrayList<File> getAllFilesRecursive(Directory dir) {
    // want the files in the order of: this directory, get all files recursive
    // for each subdirectory from left to right
    // add all the files in this directory to the return list
    ArrayList<File> ret = dir.getChildFiles();
    // for each subdirectory, get all its files and in its subdirectories, and
    // then add them into the return list
    ArrayList<Directory> subDirectories = dir.getChildDirectories();
    for (Directory subDir : subDirectories) {
      ret.addAll(GrepCmd.getAllFilesRecursive(subDir));
    }
    return ret;
  }

  // similar to getMachingLines() but with PATH: added to the front of each
  // line, where PATH is path of the file
  private static String getPathAndMatchingLines(Pattern pattern, File file) {
    // path of file
    String filePath = file.getPath().toString();
    // for each matching line, add the PATH: and the matching line
    String matches = "";
    for (String line : file) {
      boolean match = pattern.matcher(line).matches();
      if (match) {
        matches += filePath + ": " + line + "\n";
      }
    }
    return matches;
  }
  
  // throws exception if arg is incorrect
  private void validate()
      throws InvalidArgumentException, InvalidPathException {
    // min 2 args
    List<String> args = this.getCmd().getArguments();
    if (args.size() < 2) {
      throw new InvalidArgumentException(
          "ERROR: grep: needs minimum 2 arguments\n");
    }
    // in the recursive case, need a min of 3 arguments; then we check
    // the rest as normal
    int curr = 1;
    if (args.get(0).equals("-R")) {
      if (args.size() < 3) {
        throw new InvalidArgumentException(
            "ERROR: grep: missing a path argument\n");
      }
      // start checking from the path which is offset by 1 due to -R
      curr++;
    }
    // check that the rest of the arguments are all paths
    while (curr < args.size()) {
      if (!Path.isAPath(args.get(curr))) {
        throw new InvalidPathException(
            "ERROR: grep: at least one argument isn't a path\n");
      }
      curr++;
    }
  }

}
