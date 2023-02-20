//ldapDataObjectHandler head @11-4989B41D
package ad4web.AD4LDAPGestioneAlternativi;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ldapDataObjectHandler implements RecordDataObjectListener {
//End ldapDataObjectHandler head

// //BeforeBuildSelect @11-F81417CB

//beforeBuildSelect head @11-3041BA14
    public void beforeBuildSelect( DataObjectEvent e) {
//End beforeBuildSelect head

//BeforeBuildSelect Tail @11-FCB6E20C
    }
//End BeforeBuildSelect Tail

// //beforeExecuteSelect @11-F81417CB

//beforeExecuteSelect head @11-8391D9D6
    public void beforeExecuteSelect( DataObjectEvent e) {
//End beforeExecuteSelect head

//beforeExecuteSelect Tail @11-FCB6E20C
    }
//End beforeExecuteSelect Tail

// //afterExecuteSelect @11-F81417CB

//afterExecuteSelect Head @11-0972E7FA
    public void afterExecuteSelect( DataObjectEvent e) {
//End afterExecuteSelect Head

//afterExecuteSelect Tail @11-FCB6E20C
    }
//End afterExecuteSelect Tail

// //BeforeBuildInsert @11-F81417CB

//beforeBuildInsert head @11-FD6471B0
    public void beforeBuildInsert( DataObjectEvent e) {
//End beforeBuildInsert head

//BeforeBuildInsert Tail @11-FCB6E20C
    }
//End BeforeBuildInsert Tail

// //beforeExecuteInsert @11-F81417CB

//beforeExecuteInsert head @11-4EB41272
    public void beforeExecuteInsert( DataObjectEvent e) {
//End beforeExecuteInsert head

//beforeExecuteInsert Tail @11-FCB6E20C
    }
//End beforeExecuteInsert Tail

// //afterExecuteInsert @11-F81417CB

//afterExecuteInsert Head @11-C4572C5E
    public void afterExecuteInsert( DataObjectEvent e) {
//End afterExecuteInsert Head

//afterExecuteInsert Tail @11-FCB6E20C
    }
//End afterExecuteInsert Tail

// //BeforeBuildUpdate @11-F81417CB

//beforeBuildUpdate head @11-37688606
    public void beforeBuildUpdate( DataObjectEvent e) {
//End beforeBuildUpdate head

//BeforeBuildUpdate Tail @11-FCB6E20C
    }
//End BeforeBuildUpdate Tail

// //beforeExecuteUpdate @11-F81417CB

//beforeExecuteUpdate head @11-84B8E5C4
    public void beforeExecuteUpdate( DataObjectEvent e) {
//End beforeExecuteUpdate head

//beforeExecuteUpdate Tail @11-FCB6E20C
    }
//End beforeExecuteUpdate Tail

// //afterExecuteUpdate @11-F81417CB

//afterExecuteUpdate Head @11-0E5BDBE8
    public void afterExecuteUpdate( DataObjectEvent e) {
//End afterExecuteUpdate Head

//Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm @30-C405D236
    String sessionVar;
    int index;
    JDBCConnection ds = ((SPCommand)e.getCommand()).getJdbcConnection();
//End Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm

//Event AfterExecuteUpdate Action SetVarSessionFromSPParm @29-5DC29571
    sessionVar = "AD4LDAPCA";
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

//afterExecuteUpdate Tail @11-FCB6E20C
    }
//End afterExecuteUpdate Tail

// //BeforeBuildDelete @11-F81417CB

//beforeBuildDelete head @11-01A46505
    public void beforeBuildDelete( DataObjectEvent e) {
//End beforeBuildDelete head

//BeforeBuildDelete Tail @11-FCB6E20C
    }
//End BeforeBuildDelete Tail

// //beforeExecuteDelete @11-F81417CB

//beforeExecuteDelete head @11-B27406C7
    public void beforeExecuteDelete( DataObjectEvent e) {
//End beforeExecuteDelete head

//beforeExecuteDelete Tail @11-FCB6E20C
    }
//End beforeExecuteDelete Tail

// //afterExecuteDelete @11-F81417CB

//afterExecuteDelete Head @11-389738EB
    public void afterExecuteDelete( DataObjectEvent e) {
//End afterExecuteDelete Head

//afterExecuteDelete Tail @11-FCB6E20C
    }
//End afterExecuteDelete Tail

//ldapDataObjectHandler Tail @11-FCB6E20C
}
//End ldapDataObjectHandler Tail

