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

//TODO: ADD MISSING JAVADOC'S
public class Directory extends Item {
  // TODO: remove instance var name b/c of redundancy
  private String name;
  private Directory parent;
  private Path path;
  // TODO: the 2 below shud be private
  private ArrayList<File> fileHolder = new ArrayList<File>();
  private ArrayList<Directory> directoryHolder = new ArrayList<Directory>();

  public Directory(String name, Directory p) {
    super(name);
    this.parent = p;
    if (parent != null) {
      this.setPath(new Path(parent.getPath().toString() + name + "/")); 
    } else {
      this.setPath(new Path("/"));
    }
  }

  // TODO: explain what this method does in a JavaDoc
  public Directory() {
    super("");
  }
  
  // TODO: add a JavaDoc that includes what the valid characters are
  public void rename(String name) {
    // calls the parent class', Item, rename method
    
  }
  
  @Override
  public Path getPath() {
    return path;
  }
  
  public void setPath(Path p) {
    this.path = p;
  }
  
  
  public void setParent(Directory p) {
    this.parent = p;
  }

  public Directory getParent() {
    return this.parent;
  } 

  public ArrayList<Directory> getChildDirectories() {
    return this.directoryHolder;
  }
  
  public ArrayList<File> getChildFiles() {
    return this.fileHolder;
  }
  
  public ArrayList<Item> getChildren() {
    ArrayList<Item> i = new ArrayList<Item>();
    for (Directory d : this.directoryHolder) {
      i.add(d);
    }
    
    for (File f : this.fileHolder) {
      i.add(f);
    }
    
    return i;
    
  }
  
  public void removeChild(Item i) {
    for (Directory d : this.directoryHolder) {
      if (d.getName().equals(i.getName())) {
        this.directoryHolder.remove(d);
        break;
      }
    }
    
    for (File f : this.fileHolder) {
      if (f.getName().equals(i.getName())) {
        this.fileHolder.remove(f);
        break;
      }
    }
  }

  // TODO: remove since it is redundant since the superclass has this method
//  public String getName() {
//    return this.name; 
//  }
  
  public String getChildrenNames() {
    String outputStr = "";
    for (Directory d : directoryHolder) {
      outputStr += d.getName() + "\n";
    }
    
    for (File f : fileHolder) {
      outputStr += f.getName() + "\n";
    }
    
    return outputStr;
  }
  
  public void setChildDirectory(Directory d) {
    directoryHolder.add(d);
  }
  
  public void setChildFile(File f) {
    fileHolder.add(f);
  }
  
  @Override
  public String toString() {
    if (parent != null) {
      return "Directory " + this.getName() + " with parent " + parent.getName() + ". ";
    } else {
      return "Directory " + this.getName() + " with parent " + parent + ". ";
    }
  }
}