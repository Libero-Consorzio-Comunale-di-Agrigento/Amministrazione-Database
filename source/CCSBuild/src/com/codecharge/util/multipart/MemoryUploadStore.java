//MemoryUploadStore class @0-FF7B149B

package com.codecharge.util.multipart;

import java.io.File;
import java.util.Vector;
import java.util.Enumeration;

public class MemoryUploadStore implements IUploadStore {

    private byte[] content;
    private long size;

    public void setContent(byte[] content) {
    this.content = content;
    }    
    
    public byte[] getContent() {
       return content;
    }
    
    public long getSize() {
        return content == null ? size : content.length;
    }
    
    public void setSize(long size) {
        this.size = size;
    }

    public void freeResource() {
        content = null;
    }
    

}

//End MemoryUploadStore class

