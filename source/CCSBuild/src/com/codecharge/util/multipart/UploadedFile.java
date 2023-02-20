//UploadedFile class @0-DBBC3948
package com.codecharge.util.multipart;

import java.io.File;
import java.util.Vector;
import java.util.Enumeration;

public class UploadedFile {
    private String name;
    private String originalName;
    private String temporaryName;
    private File file;
    //private byte[] content;
    //private long size;

    IUploadStore store;
    
    private Vector errors = new Vector();

    private String contentType; //This propertie is setting in RequestParser.

    public String getContentType() {
        return contentType;
    }
    public void setContentType(String type) {
        this.contentType = type;
    }
    
    public UploadedFile( String name, byte[] content ) {
        this.name = name;
        this.store = UploadStoreFactory.getStore();
        this.store.setContent(content);
    }
    
    public UploadedFile( String name, File file ) {
        this.file = file;
        this.name = name;
        this.temporaryName = file.getName();
    }
    
    public String getName() {
        return name;
    }
    
    public byte[] getContent() {
        if (temporaryName == null) {
            return this.store.getContent();
        } else {
            //TODO:
            //not implemented yet
            return null;
        }
    }
    
    public File getFile() {
        return file;
    }
    
    public String getOriginalName() {
        return originalName;
    }
    
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
    
    public long getSize() {
        return this.store == null ? this.store.getSize() : this.store.getContent().length;
    }
    
    public void setSize(long size) {
        //this.size = size;
        this.store.setSize(size);
    }
    
    public boolean hasErrors() {
        return (errors.size() > 0); 
    }
    
    public Enumeration getErrors() {
        return errors.elements();
    }
    
    public void addError(String messageKey) {
        if (messageKey!=null) {
            errors.add(messageKey);
        }
    }


    public void freeResource() {
        this.store.freeResource();
    }

}

//End UploadedFile class

