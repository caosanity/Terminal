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

import java.util.List;
import java.util.ArrayList;

public class Command {
  // special string delimiters
  private static final char STRING_BEG = '"';
  private static final char STRING_END = '"';
  // declare a private string and Arraylist to carry command and arguments
  // details
  private String command = "";
  private List<String> arguments = new ArrayList<>();

  /**
   * Constructs a command that parses <code>line</code> into the the name and
   * the arguments, using whitespace as the main delimiter and ignoring any
   * trailing/leading whitespace. The name is the first substring that is parsed
   * from <code>line</code>, while subsequent parsed substrings are considered
   * arguments.
   * <p>
   * This also supports string arguments, which are enclosed by double quotes.
   * String arguments maintain every character in between the double quotes,
   * including the double quote characters at each end. If any double quote
   * isn't closed, then it is parsed until the next whitespace or the end.
   * <p>
   * If there is the character '!' present in <code>line</code>, then that is
   * parsed as it's own substring and the remaining string is parsed as normal.
   * 
   * @param line the string to parse into a command
   */
  public Command(String line) {
    // ignore trailing and leading whitespace
    String trimmedLine = line.trim();
    // start parsing from the first character until the end
    int curr = 0;
    boolean nameFound = false;
    while (curr < trimmedLine.length()) {
      char currChar = trimmedLine.charAt(curr);
      if (Character.isWhitespace(currChar)) {
        // if the current char is a whitespace, then we move the current index
        // pointer to the next character
        curr++;
      } else { 
        // if curr char is a double quote, we parse for a string; if its !
        // then we only want the ! char; else parse regularly(whitespace)
        int end;
        boolean keep = false; // to keep current index at end when parsing ! 
        if (currChar == STRING_BEG) {
          end = trimmedLine.indexOf(STRING_END, curr + 1) + 1;
        } else if (currChar == '!'){
          end = curr + 1;
          keep = true;
        } else {
          end = findWhitespace(trimmedLine, curr);
        }
        if (end == -1) {
          end = trimmedLine.length();
        }
        String substring = trimmedLine.substring(curr, end);
        // if the name hasnt been found, then the substring is the name
        if (!nameFound) {
          this.command = substring;
          nameFound = true;
        } else {
          this.arguments.add(substring);
        }
        curr = end + 1;
        if (keep) {
          keep = false;
          curr -= 1; // so we can parse the next char following '!'
        }
      }
    }
  }

  /**
   * Finds the index of the first occurrence of a whitespace as determined by
   * Character.isWhitespace() starting at index <code>from</code>. If no index
   * is found, -1 is returned instead.
   */
  private static int findWhitespace(String string, int from) {
    // search for whitespace while it hasnt been found and the index is still
    // within the string
    int found = -1;
    int curr = from;
    while (curr < string.length() && found == -1) {
      if (Character.isWhitespace(string.charAt(curr))) {
        found = curr;
      } else {
        curr++;
      }
    }
    return found;
  }

  // a getter method for the command name
  public String getName() {
    return command;
  }

  // a getter method for arguments
  public List<String> getArguments() {
    return arguments;
  }

}
