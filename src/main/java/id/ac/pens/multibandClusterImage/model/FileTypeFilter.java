/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.pens.multibandClusterImage.model;

import javax.swing.filechooser.*;
import java.io.File;

/**
 *
 * @author rhr
 */
public class FileTypeFilter extends FileFilter{
    private final String extension;
    private final String description;
    
    public FileTypeFilter(String extension, String description){
        this.extension = extension;
        this.description = description;
    }

    @Override
    public boolean accept(File file) {
        if(file.isDirectory()){
            return true;
        } //To change body of generated methods, choose Tools | Templates.
        return file.getName().endsWith(extension);
    }

    public String getDescription() {
        return description + String.format("(*%s)", extension);
    }
    
    
    
    
    
}
