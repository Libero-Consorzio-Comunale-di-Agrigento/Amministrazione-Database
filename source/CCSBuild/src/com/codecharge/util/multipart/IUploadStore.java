//IUploadedStore Interface @0-7702EA1F
package com.codecharge.util.multipart;

public interface IUploadStore {

    void setContent(byte[] content);

    byte[] getContent();

    void setSize(long size);

    long getSize();

    void freeResource();

}

//End IUploadedStore Interface

