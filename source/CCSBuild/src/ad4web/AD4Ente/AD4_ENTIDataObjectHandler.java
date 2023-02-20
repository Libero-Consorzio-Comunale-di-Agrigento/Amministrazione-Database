//AD4_ENTIDataObjectHandler head @6-82A3EBD4
package ad4web.AD4Ente;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AD4_ENTIDataObjectHandler implements RecordDataObjectListener {
//End AD4_ENTIDataObjectHandler head

// //BeforeBuildSelect @6-F81417CB

//beforeBuildSelect head @6-3041BA14
    public void beforeBuildSelect( DataObjectEvent e) {
//End beforeBuildSelect head

//BeforeBuildSelect Tail @6-FCB6E20C
    }
//End BeforeBuildSelect Tail

// //beforeExecuteSelect @6-F81417CB

//beforeExecuteSelect head @6-8391D9D6
    public void beforeExecuteSelect( DataObjectEvent e) {
//End beforeExecuteSelect head

//beforeExecuteSelect Tail @6-FCB6E20C
    }
//End beforeExecuteSelect Tail

// //afterExecuteSelect @6-F81417CB

//afterExecuteSelect Head @6-0972E7FA
    public void afterExecuteSelect( DataObjectEvent e) {
//End afterExecuteSelect Head

//afterExecuteSelect Tail @6-FCB6E20C
    }
//End afterExecuteSelect Tail

// //BeforeBuildInsert @6-F81417CB

//beforeBuildInsert head @6-FD6471B0
    public void beforeBuildInsert( DataObjectEvent e) {
//End beforeBuildInsert head

//BeforeBuildInsert Tail @6-FCB6E20C
    }
//End BeforeBuildInsert Tail

// //beforeExecuteInsert @6-F81417CB

//beforeExecuteInsert head @6-4EB41272
    public void beforeExecuteInsert( DataObjectEvent e) {
//End beforeExecuteInsert head

//beforeExecuteInsert Tail @6-FCB6E20C
    }
//End beforeExecuteInsert Tail

// //afterExecuteInsert @6-F81417CB

//afterExecuteInsert Head @6-C4572C5E
    public void afterExecuteInsert( DataObjectEvent e) {
//End afterExecuteInsert Head

//afterExecuteInsert Tail @6-FCB6E20C
    }
//End afterExecuteInsert Tail

// //BeforeBuildUpdate @6-F81417CB

//beforeBuildUpdate head @6-37688606
    public void beforeBuildUpdate( DataObjectEvent e) {
//End beforeBuildUpdate head

//BeforeBuildUpdate Tail @6-FCB6E20C
    }
//End BeforeBuildUpdate Tail

// //beforeExecuteUpdate @6-F81417CB

//beforeExecuteUpdate head @6-84B8E5C4
    public void beforeExecuteUpdate( DataObjectEvent e) {
//End beforeExecuteUpdate head

//beforeExecuteUpdate Tail @6-FCB6E20C
    }
//End beforeExecuteUpdate Tail

// //afterExecuteUpdate @6-F81417CB

//afterExecuteUpdate Head @6-0E5BDBE8
    public void afterExecuteUpdate( DataObjectEvent e) {
//End afterExecuteUpdate Head

//Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm @120-C405D236
    String sessionVar;
    int index;
    JDBCConnection ds = ((SPCommand)e.getCommand()).getJdbcConnection();
//End Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm

//Event AfterExecuteUpdate Action SetVarSessionFromSPParm @121-401C9470
    sessionVar = "AD4ENTE";
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

//afterExecuteUpdate Tail @6-FCB6E20C
    }
//End afterExecuteUpdate Tail

// //BeforeBuildDelete @6-F81417CB

//beforeBuildDelete head @6-01A46505
    public void beforeBuildDelete( DataObjectEvent e) {
//End beforeBuildDelete head

//BeforeBuildDelete Tail @6-FCB6E20C
    }
//End BeforeBuildDelete Tail

// //beforeExecuteDelete @6-F81417CB

//beforeExecuteDelete head @6-B27406C7
    public void beforeExecuteDelete( DataObjectEvent e) {
//End beforeExecuteDelete head

//beforeExecuteDelete Tail @6-FCB6E20C
    }
//End beforeExecuteDelete Tail

// //afterExecuteDelete @6-F81417CB

//afterExecuteDelete Head @6-389738EB
    public void afterExecuteDelete( DataObjectEvent e) {
//End afterExecuteDelete Head

//afterExecuteDelete Tail @6-FCB6E20C
    }
//End afterExecuteDelete Tail

//AD4_ENTIDataObjectHandler Tail @6-FCB6E20C
}
//End AD4_ENTIDataObjectHandler Tail

