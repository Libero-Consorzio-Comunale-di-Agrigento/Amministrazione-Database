//UploadStoreFactory class @0-612CF46B
package com.codecharge.util.multipart;

import com.codecharge.util.StringUtils;
import com.codecharge.util.CCLogger;

public class UploadStoreFactory {

    public static IUploadStore getStore() {
        IUploadStore res = null;

        String storeType = StringUtils.getSiteProperty( "com.codecharge.util.upload.storage" );
        if ("file".equalsIgnoreCase (storeType) ) 
            res = new FileUploadStore ();
        else 
            res = new MemoryUploadStore ();
        CCLogger.getInstance().debug("Upload store class "+res.getClass()+" is used.");
        return res;
    }

}

//End UploadStoreFactory class

