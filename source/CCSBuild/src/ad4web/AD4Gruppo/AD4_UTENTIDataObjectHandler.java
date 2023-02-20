//AD4_UTENTIDataObjectHandler head @23-88BA069C
package ad4web.AD4Gruppo;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AD4_UTENTIDataObjectHandler implements RecordDataObjectListener {
//End AD4_UTENTIDataObjectHandler head

// //BeforeBuildSelect @23-F81417CB

//beforeBuildSelect head @23-3041BA14
    public void beforeBuildSelect( DataObjectEvent e) {
//End beforeBuildSelect head

//BeforeBuildSelect Tail @23-FCB6E20C
    }
//End BeforeBuildSelect Tail

// //beforeExecuteSelect @23-F81417CB

//beforeExecuteSelect head @23-8391D9D6
    public void beforeExecuteSelect( DataObjectEvent e) {
//End beforeExecuteSelect head

//beforeExecuteSelect Tail @23-FCB6E20C
    }
//End beforeExecuteSelect Tail

// //afterExecuteSelect @23-F81417CB

//afterExecuteSelect Head @23-0972E7FA
    public void afterExecuteSelect( DataObjectEvent e) {
//End afterExecuteSelect Head

//afterExecuteSelect Tail @23-FCB6E20C
    }
//End afterExecuteSelect Tail

// //BeforeBuildInsert @23-F81417CB

//beforeBuildInsert head @23-FD6471B0
    public void beforeBuildInsert( DataObjectEvent e) {
//End beforeBuildInsert head

//BeforeBuildInsert Tail @23-FCB6E20C
    }
//End BeforeBuildInsert Tail

// //beforeExecuteInsert @23-F81417CB

//beforeExecuteInsert head @23-4EB41272
    public void beforeExecuteInsert( DataObjectEvent e) {
//End beforeExecuteInsert head

//beforeExecuteInsert Tail @23-FCB6E20C
    }
//End beforeExecuteInsert Tail

// //afterExecuteInsert @23-F81417CB

//afterExecuteInsert Head @23-C4572C5E
    public void afterExecuteInsert( DataObjectEvent e) {
//End afterExecuteInsert Head

//afterExecuteInsert Tail @23-FCB6E20C
    }
//End afterExecuteInsert Tail

// //BeforeBuildUpdate @23-F81417CB

//beforeBuildUpdate head @23-37688606
    public void beforeBuildUpdate( DataObjectEvent e) {
//End beforeBuildUpdate head

//BeforeBuildUpdate Tail @23-FCB6E20C
    }
//End BeforeBuildUpdate Tail

// //beforeExecuteUpdate @23-F81417CB

//beforeExecuteUpdate head @23-84B8E5C4
    public void beforeExecuteUpdate( DataObjectEvent e) {
//End beforeExecuteUpdate head

//beforeExecuteUpdate Tail @23-FCB6E20C
    }
//End beforeExecuteUpdate Tail

// //afterExecuteUpdate @23-F81417CB

//afterExecuteUpdate Head @23-0E5BDBE8
    public void afterExecuteUpdate( DataObjectEvent e) {
//End afterExecuteUpdate Head

//Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm @107-C405D236
    String sessionVar;
    int index;
    JDBCConnection ds = ((SPCommand)e.getCommand()).getJdbcConnection();
//End Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm

//Event AfterExecuteUpdate Action SetVarSessionFromSPParm @108-DF9B7B8B
    sessionVar = "AD4GRUPPO";
    index = Integer.parseInt("0");
    boolean isErrors = ds.hasErrors();
    if ( !isErrors ) {
        java.util.Collection cParam=null;
        cParam = ((SPCommand)e.getCommand()).getParameters();
        Object params[] = cParam.toArray();
        if ( params[index] != null ) {
            SessionStorage.getInstance(e.getPage().getRequest()).setAttribute(sessionVar, ((Parameter)params[index]).getObjectValue());
        }
        else {
            SessionStorage.getInstance(e.getPage().getRequest()).setAttribute(sessionVar, "");
        }
    }
    else {
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute(sessionVar, "");
    }
//End Event AfterExecuteUpdate Action SetVarSessionFromSPParm

//afterExecuteUpdate Tail @23-FCB6E20C
    }
//End afterExecuteUpdate Tail

// //BeforeBuildDelete @23-F81417CB

//beforeBuildDelete head @23-01A46505
    public void beforeBuildDelete( DataObjectEvent e) {
//End beforeBuildDelete head

//BeforeBuildDelete Tail @23-FCB6E20C
    }
//End BeforeBuildDelete Tail

// //beforeExecuteDelete @23-F81417CB

//beforeExecuteDelete head @23-B27406C7
    public void beforeExecuteDelete( DataObjectEvent e) {
//End beforeExecuteDelete head

//beforeExecuteDelete Tail @23-FCB6E20C
    }
//End beforeExecuteDelete Tail

// //afterExecuteDelete @23-F81417CB

//afterExecuteDelete Head @23-389738EB
    public void afterExecuteDelete( DataObjectEvent e) {
//End afterExecuteDelete Head

//afterExecuteDelete Tail @23-FCB6E20C
    }
//End afterExecuteDelete Tail

//AD4_UTENTIDataObjectHandler Tail @23-FCB6E20C
}
//End AD4_UTENTIDataObjectHandler Tail

