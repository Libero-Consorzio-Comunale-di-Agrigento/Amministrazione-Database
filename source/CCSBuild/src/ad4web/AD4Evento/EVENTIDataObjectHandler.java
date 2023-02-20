//EVENTIDataObjectHandler head @56-8D9689E9
package ad4web.AD4Evento;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EVENTIDataObjectHandler implements RecordDataObjectListener {
//End EVENTIDataObjectHandler head

// //BeforeBuildSelect @56-F81417CB

//beforeBuildSelect head @56-3041BA14
    public void beforeBuildSelect( DataObjectEvent e) {
//End beforeBuildSelect head

//BeforeBuildSelect Tail @56-FCB6E20C
    }
//End BeforeBuildSelect Tail

// //beforeExecuteSelect @56-F81417CB

//beforeExecuteSelect head @56-8391D9D6
    public void beforeExecuteSelect( DataObjectEvent e) {
//End beforeExecuteSelect head

//beforeExecuteSelect Tail @56-FCB6E20C
    }
//End beforeExecuteSelect Tail

// //afterExecuteSelect @56-F81417CB

//afterExecuteSelect Head @56-0972E7FA
    public void afterExecuteSelect( DataObjectEvent e) {
//End afterExecuteSelect Head

//afterExecuteSelect Tail @56-FCB6E20C
    }
//End afterExecuteSelect Tail

// //BeforeBuildInsert @56-F81417CB

//beforeBuildInsert head @56-FD6471B0
    public void beforeBuildInsert( DataObjectEvent e) {
//End beforeBuildInsert head

//BeforeBuildInsert Tail @56-FCB6E20C
    }
//End BeforeBuildInsert Tail

// //beforeExecuteInsert @56-F81417CB

//beforeExecuteInsert head @56-4EB41272
    public void beforeExecuteInsert( DataObjectEvent e) {
//End beforeExecuteInsert head

//beforeExecuteInsert Tail @56-FCB6E20C
    }
//End beforeExecuteInsert Tail

// //afterExecuteInsert @56-F81417CB

//afterExecuteInsert Head @56-C4572C5E
    public void afterExecuteInsert( DataObjectEvent e) {
//End afterExecuteInsert Head

//afterExecuteInsert Tail @56-FCB6E20C
    }
//End afterExecuteInsert Tail

// //BeforeBuildUpdate @56-F81417CB

//beforeBuildUpdate head @56-37688606
    public void beforeBuildUpdate( DataObjectEvent e) {
//End beforeBuildUpdate head

//BeforeBuildUpdate Tail @56-FCB6E20C
    }
//End BeforeBuildUpdate Tail

// //beforeExecuteUpdate @56-F81417CB

//beforeExecuteUpdate head @56-84B8E5C4
    public void beforeExecuteUpdate( DataObjectEvent e) {
//End beforeExecuteUpdate head

//beforeExecuteUpdate Tail @56-FCB6E20C
    }
//End beforeExecuteUpdate Tail

// //afterExecuteUpdate @56-F81417CB

//afterExecuteUpdate Head @56-0E5BDBE8
    public void afterExecuteUpdate( DataObjectEvent e) {
//End afterExecuteUpdate Head

//Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm @190-C405D236
    String sessionVar;
    int index;
    JDBCConnection ds = ((SPCommand)e.getCommand()).getJdbcConnection();
//End Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm

//Event AfterExecuteUpdate Action SetVarSessionFromSPParm @191-1D4B1790
    sessionVar = "AD4ACCERIPR";
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

//afterExecuteUpdate Tail @56-FCB6E20C
    }
//End afterExecuteUpdate Tail

// //BeforeBuildDelete @56-F81417CB

//beforeBuildDelete head @56-01A46505
    public void beforeBuildDelete( DataObjectEvent e) {
//End beforeBuildDelete head

//BeforeBuildDelete Tail @56-FCB6E20C
    }
//End BeforeBuildDelete Tail

// //beforeExecuteDelete @56-F81417CB

//beforeExecuteDelete head @56-B27406C7
    public void beforeExecuteDelete( DataObjectEvent e) {
//End beforeExecuteDelete head

//beforeExecuteDelete Tail @56-FCB6E20C
    }
//End beforeExecuteDelete Tail

// //afterExecuteDelete @56-F81417CB

//afterExecuteDelete Head @56-389738EB
    public void afterExecuteDelete( DataObjectEvent e) {
//End afterExecuteDelete Head

//afterExecuteDelete Tail @56-FCB6E20C
    }
//End afterExecuteDelete Tail

//EVENTIDataObjectHandler Tail @56-FCB6E20C
}
//End EVENTIDataObjectHandler Tail

