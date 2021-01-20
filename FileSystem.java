// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: shahahm6
// UT Student #: 1004192287
// Author: Ahmad Shah
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

package driver;

import java.util.ArrayList;
import java.util.Arrays;
import err.ItemNotFoundException;
import err.JShellException;

public class FileSystem {

  private static Directory cwd;
  private static Directory root;
  private static FileSystem ref = null;

  private FileSystem() {
    // will contain all the files
    root = new Directory("/", null);
    cwd = root;
    // System.out.println(root);
  }

  public static FileSystem getFileSystem() {
    if (ref == null) {
      ref = new FileSystem();
    }
    return ref;
  }

  /**
   * When given a String name, changes the current working directory to that
   * directory
   * 
   * @param name name of directory to be changed to.
   * @throws ItemNotFoundException
   */
  private void changeDirectoryByName(String name) throws ItemNotFoundException {

    int currChildIndex = 0;
    Directory currentChild = null;
    ArrayList<Directory> cwdChildren = cwd.getChildDirectories();
    if (cwdChildren.size() > 0) {
      currentChild = cwdChildren.get(currChildIndex);
    } else {
      throw new ItemNotFoundException("");
    }

    while (!(name.equals(currentChild.getName()))) {
      if (currChildIndex < cwdChildren.size()) {
        currentChild = cwdChildren.get(currChildIndex++);
      } else {
        throw new ItemNotFoundException("");
      }
    }
    cwd = currentChild;
  }

  /**
   * When given a string name and a directory, goes to the child directory with
   * the given name in directory d. For example if the root directory has
   * directories d1 and d2, goToDirectoryByName(d1, root) will go to d1. The CWD
   * will not be changed.
   * 
   * @param name name of the child directory
   * @param d the directory where to look for directory NAME
   * @return directory with name NAME
   * @throws ItemNotFoundException
   */
  private static Directory goToDirectoryByName(String name, Directory d)
      throws ItemNotFoundException {

    int currChildIndex = 0;
    Directory currentChild = null;
    ArrayList<Directory> currChildren = d.getChildDirectories();
    if (currChildren.size() > 0) {
      currentChild = currChildren.get(currChildIndex);
    } else {
      throw new ItemNotFoundException("");
    }

    // loop through the children until the directory with name NAME is found
    while (!(name.equals(currentChild.getName()))) {
      if (currChildIndex < currChildren.size()) {
        currentChild = currChildren.get(currChildIndex++);
      } else {
        throw new ItemNotFoundException("");
      }
    }
    return currentChild;
  }

  // TODO: refactor using path class

  /**
   * Changes cwd to directory at path PATH. This path may be absolute or
   * relative.
   * 
   * @param path path of the directory to be changed to
   * @throws ItemNotFoundException
   */
  public void changeDirectory(Path path) throws ItemNotFoundException {
    boolean absolute = path.isAbsolute();

    // of the path is the root, just change to the root
    if (path.equals(root.getPath())) {
      cwd = root;
    } else if (absolute) {
      // otherwise, go through each component of the path and change
      // individually
      int curr_index = 1;
      // since its absolute, we initally set the cwd to root
      cwd = root;
      while (curr_index < path.length()) {
        // if we get the .. relative path name, go back to parent directory
        if (path.getComponentName(curr_index).equals("..")) {
          if (!cwd.getPath().equals(root.getPath())) {
            cwd = cwd.getParent();
          }
        } else if (path.getComponent(curr_index).equals(".")) {
          // do nothing
        } else {
          changeDirectoryByName(path.getComponentName(curr_index));
        }
        curr_index++;
      }
    } else {
      // Similar
      if (path.toString().equals("..")) {
        if (!cwd.getPath().equals(root.getPath())) {
          cwd = cwd.getParent();
        }
      } else if (path.toString().equals(".")) {
        // do nothing
      } else {
        int curr_index = 0;
        while (curr_index < path.length()) {
          if (path.getComponentName(curr_index).equals("..")) {
            if (!cwd.getPath().equals(root.getPath())) {
              cwd = cwd.getParent();
            }
          } else if (path.getComponentName(curr_index).equals(".")) {
            // do nothing
          } else {
            changeDirectoryByName(path.getComponentName(curr_index));
          }
          curr_index++;
        }
      }
    }
  }

  public static Directory getDirectoryAtPath(Path path)
      throws ItemNotFoundException {
    // System.out.println("b" + path);
    Directory currDirect = new Directory();

    if (path.equals(root.getPath())) {
      currDirect = root;
    } else {
      boolean absolute = path.isAbsolute();

      if (absolute) {
        int curr_index = 1;
        currDirect = root;
        while (curr_index < path.length()) {
          if (path.getComponentName(curr_index).equals("..")) {
            if (!cwd.getPath().equals(root.getPath())) {
              cwd = cwd.getParent();
            }
          } else if (path.getComponentName(curr_index).equals(("."))) {
            // nothing happens
          } else {
            // System.out.println(path.getComponent(curr_index));
            currDirect = goToDirectoryByName(path.getComponentName(curr_index),
                currDirect);
          }
          curr_index++;
        }
      } else {
        currDirect = cwd;
        if (path.toString().equals("..")) {
          if (!cwd.getPath().equals(root.getPath())) {
            cwd = cwd.getParent();
          }
        } else if (path.toString().equals(".")) {
          // currDirect should remain same so no effect
        } else {
          int curr_index = 0;
          while (curr_index < path.length()) {
            if (path.getComponentName(curr_index).equals("..")) {
              if (!cwd.getPath().equals(root.getPath())) {
                cwd = cwd.getParent();
              }
            } else if (path.getComponentName(curr_index).equals(".")) {
              // nothing happens if we use . >> currDirect remains the same
            } else {
              // System.out.println("path " + path.getComponent(curr_index));
              currDirect = goToDirectoryByName(
                  path.getComponentName(curr_index), currDirect);
            }
            curr_index++;
          }
        }
      }
    }

    if (currDirect.getName() == null) {
      currDirect = null;
    }

    return currDirect;
  }


  public static File getFileAtPath(Path p) throws ItemNotFoundException {
    Path parentPath;
    String parentString;
    if (p.isAbsolute()) {
      parentPath = p.getParentPath();
    } else {
      if (p.length() > 1) {
        parentString = cwd.getPath().toString() + p.getParentPath().toString();
      } else {
        parentString = cwd.getPath().toString();
      }
      parentPath = new Path(parentString);
    }

    Directory parentDirectory = getDirectoryAtPath(parentPath);
    ArrayList<File> f = parentDirectory.getChildFiles();
    String fileName = p.getComponentName(p.length() - 1);
    File currFile = null;
    
//    //og code
//    File file = new File(fileName, parentDirectory);
//    File currFile = new File("", null);
//    int index = 0;
//    if (f.contains(file)) {
//      while (!(fileName.equals(f.get(index).getName()))) {
//        index += 1;
//      }
//      currFile = f.get(index);
//    } else {
//      throw new ItemNotFoundException("");
//    }
//    // end of og
    
    // find the file with the same name
    boolean found = false;
    int curr = 0;
    while (curr < f.size() && !found) {
      if (f.get(curr).getName().equals(fileName)) {
        found = true;
        currFile = f.get(curr);
      } else {
        curr++;
      }
    }
    if (!found) {
      throw new ItemNotFoundException("file " + p + " does not exist\n");
    }
    return currFile;
  }

  public Item getItemAtPath(Path p) throws ItemNotFoundException {
    Item ret = new Item();
    try {
      ret = getDirectoryAtPath(p);
    } catch (ItemNotFoundException e) {
      ret = getFileAtPath(p);
    }
    return ret;
  }

  public void setItemAtPath(Item i, Path p) throws ItemNotFoundException {
    if (i instanceof File) {
      if (p.toString().equals(root.toString())) {
        root.setChildFile((File) i);
      } else {
        getDirectoryAtPath(p.getParentPath()).setChildFile((File) i);
      }
    } else {
      if (p.toString().equals(root.toString())) {
        root.setChildDirectory((Directory) i);
      } else {
        getDirectoryAtPath(p.getParentPath()).setChildDirectory((Directory) i);
      }
    }
  }

  /**
   * Helper method that recursively changes the old paths of the directories in
   * terms of the new paths.
   * 
   * @param oldDirectory
   * @param newDirectory
   */
  private void fixSubpaths(Directory oldDirectory, Directory newDirectory) {
    // System.out.println("in");
    String newPathString = newDirectory.getPath().toString();
    ArrayList<Directory> oldChildren = oldDirectory.getChildDirectories();
    Path oldPath = oldDirectory.getPath();

    if (oldDirectory.getChildDirectories().size() == 0) {
      String newString = newPathString.substring(0, newPathString.length() - 1)
          + oldPath.toString();
      // System.out.println(oldDirectory);
      oldDirectory.setPath(new Path(newString));
    } else {
      for (Directory oldChild : oldChildren) {
        fixPaths(oldChild, newDirectory);
      }
    }
  }

  public void fixPaths(Directory oldDirectory, Directory newDirectory) {
    fixSubpaths(oldDirectory, newDirectory);

    if (newDirectory.getPath().isRelative()) {
      String newPathString = newDirectory.getPath().toString();
      Path oldPath = oldDirectory.getPath();
      String newString = newPathString.substring(0, newPathString.length() - 1)
          + oldPath.toString();
      oldDirectory.setPath(new Path(newString));
    }
  }

  public static Directory getCWD() {
    return cwd;
  }

  public static Directory getRoot() {
    return root;
  }
}
