package com.mpls.v2.dro;

import java.util.List;

public class DriveFile {
    private String id;
    private String name;
    private boolean isFolder;
    private List<String> parents;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isFolder() {
        return isFolder;
    }
    
    public void setFolder(boolean isFolder) {
        this.isFolder = isFolder;
    }
    
    public List<String> getParents() {
        return parents;
    }
    
    public void setParents(List<String> parents) {
        this.parents = parents;
    }    

}