//FileUploadStore class @0-B1BB484F

package com.codecharge.util.multipart;

import java.io.File;
import java.util.Vector;
import java.util.Enumeration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUploadStore implements IUploadStore {

    //private byte[] content;
    private long size;
    private File file;

    public void setContent(byte[] content) {
    try {
        this.file =  File.createTempFile("ccsupolad", ".tmp");
        FileOutputStream fo = new FileOutputStream (this.file);
        fo.write(content);
        fo.close();
    } catch (IOException e1) {
        e1.printStackTrace();
    }
    


    }    
    
    public byte[] getContent() {
    byte[] c = null;
    try {
        c = new byte[(int) this.file.length()];
        FileInputStream fi = new FileInputStream (this.file);
        fi.read(c);
        fi.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
       return c;
    }
    
    public long getSize() {
        return file == null ? size : this.file.length();
    }
    
    public void setSize(long size) {
        this.size = size;
    }

    public void freeResource() {
        if (this.file.exists()) this.file.delete();
    }
    

}

//End FileUploadStore class

