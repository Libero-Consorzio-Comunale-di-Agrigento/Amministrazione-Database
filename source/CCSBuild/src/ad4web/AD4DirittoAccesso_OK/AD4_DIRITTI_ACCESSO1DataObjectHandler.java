//AD4_DIRITTI_ACCESSO1DataObjectHandler head @38-CCE309D5
package ad4web.AD4DirittoAccesso_OK;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AD4_DIRITTI_ACCESSO1DataObjectHandler implements RecordDataObjectListener {
//End AD4_DIRITTI_ACCESSO1DataObjectHandler head

// //BeforeBuildSelect @38-F81417CB

//beforeBuildSelect head @38-3041BA14
    public void beforeBuildSelect( DataObjectEvent e) {
//End beforeBuildSelect head

//BeforeBuildSelect Tail @38-FCB6E20C
    }
//End BeforeBuildSelect Tail

// //beforeExecuteSelect @38-F81417CB

//beforeExecuteSelect head @38-8391D9D6
    public void beforeExecuteSelect( DataObjectEvent e) {
//End beforeExecuteSelect head

//beforeExecuteSelect Tail @38-FCB6E20C
    }
//End beforeExecuteSelect Tail

// //afterExecuteSelect @38-F81417CB

//afterExecuteSelect Head @38-0972E7FA
    public void afterExecuteSelect( DataObjectEvent e) {
//End afterExecuteSelect Head

//afterExecuteSelect Tail @38-FCB6E20C
    }
//End afterExecuteSelect Tail

// //BeforeBuildInsert @38-F81417CB

//beforeBuildInsert head @38-FD6471B0
    public void beforeBuildInsert( DataObjectEvent e) {
//End beforeBuildInsert head

//BeforeBuildInsert Tail @38-FCB6E20C
    }
//End BeforeBuildInsert Tail

// //beforeExecuteInsert @38-F81417CB

//beforeExecuteInsert head @38-4EB41272
    public void beforeExecuteInsert( DataObjectEvent e) {
//End beforeExecuteInsert head

//beforeExecuteInsert Tail @38-FCB6E20C
    }
//End beforeExecuteInsert Tail

// //afterExecuteInsert @38-F81417CB

//afterExecuteInsert Head @38-C4572C5E
    public void afterExecuteInsert( DataObjectEvent e) {
//End afterExecuteInsert Head

//Event AfterExecuteInsert Action AD4RefreshSlaves @168-2B13205D
if(!e.getRecord().hasErrors()){
    String retValue = "";
    String istanza = (String)SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("Istanza");
    JDBCConnection conn = JDBCConnectionFactory.getJDBCConnection("cn");
    java.sql.CallableStatement cstmtAccesso = null;
    cstmtAccesso = conn.createCallableStatement("{? = call ADMIN_AD4.REFRESH_SLAVES_JOB(?,?)}");
    try {
        cstmtAccesso.registerOutParameter(1, java.sql.Types.VARCHAR);
        cstmtAccesso.setString(2, "UTENTIG");
        cstmtAccesso.setString(3, istanza);
        cstmtAccesso.execute();
        retValue = cstmtAccesso.getString(1);
        if(retValue == null || "".equals(retValue)) {
            retValue = "NO";
        }
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4REFRESHSLAVES", retValue);
        cstmtAccesso.close();
    }catch (java.sql.SQLException sqle){
        sqle.printStackTrace();
    }
    conn.closeConnection();
}
//End Event AfterExecuteInsert Action AD4RefreshSlaves

//afterExecuteInsert Tail @38-FCB6E20C
    }
//End afterExecuteInsert Tail

// //BeforeBuildUpdate @38-F81417CB

//beforeBuildUpdate head @38-37688606
    public void beforeBuildUpdate( DataObjectEvent e) {
//End beforeBuildUpdate head

//BeforeBuildUpdate Tail @38-FCB6E20C
    }
//End BeforeBuildUpdate Tail

// //beforeExecuteUpdate @38-F81417CB

//beforeExecuteUpdate head @38-84B8E5C4
    public void beforeExecuteUpdate( DataObjectEvent e) {
//End beforeExecuteUpdate head

//beforeExecuteUpdate Tail @38-FCB6E20C
    }
//End beforeExecuteUpdate Tail

// //afterExecuteUpdate @38-F81417CB

//afterExecuteUpdate Head @38-0E5BDBE8
    public void afterExecuteUpdate( DataObjectEvent e) {
//End afterExecuteUpdate Head

//Event AfterExecuteUpdate Action AD4RefreshSlaves @167-2B13205D
if(!e.getRecord().hasErrors()){
    String retValue = "";
    String istanza = (String)SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("Istanza");
    JDBCConnection conn = JDBCConnectionFactory.getJDBCConnection("cn");
    java.sql.CallableStatement cstmtAccesso = null;
    cstmtAccesso = conn.createCallableStatement("{? = call ADMIN_AD4.REFRESH_SLAVES_JOB(?,?)}");
    try {
        cstmtAccesso.registerOutParameter(1, java.sql.Types.VARCHAR);
        cstmtAccesso.setString(2, "UTENTIG");
        cstmtAccesso.setString(3, istanza);
        cstmtAccesso.execute();
        retValue = cstmtAccesso.getString(1);
        if(retValue == null || "".equals(retValue)) {
            retValue = "NO";
        }
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4REFRESHSLAVES", retValue);
        cstmtAccesso.close();
    }catch (java.sql.SQLException sqle){
        sqle.printStackTrace();
    }
    conn.closeConnection();
}
//End Event AfterExecuteUpdate Action AD4RefreshSlaves

//afterExecuteUpdate Tail @38-FCB6E20C
    }
//End afterExecuteUpdate Tail

// //BeforeBuildDelete @38-F81417CB

//beforeBuildDelete head @38-01A46505
    public void beforeBuildDelete( DataObjectEvent e) {
//End beforeBuildDelete head

//BeforeBuildDelete Tail @38-FCB6E20C
    }
//End BeforeBuildDelete Tail

// //beforeExecuteDelete @38-F81417CB

//beforeExecuteDelete head @38-B27406C7
    public void beforeExecuteDelete( DataObjectEvent e) {
//End beforeExecuteDelete head

//beforeExecuteDelete Tail @38-FCB6E20C
    }
//End beforeExecuteDelete Tail

// //afterExecuteDelete @38-F81417CB

//afterExecuteDelete Head @38-389738EB
    public void afterExecuteDelete( DataObjectEvent e) {
//End afterExecuteDelete Head

//Event AfterExecuteDelete Action AD4RefreshSlaves @169-2B13205D
if(!e.getRecord().hasErrors()){
    String retValue = "";
    String istanza = (String)SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("Istanza");
    JDBCConnection conn = JDBCConnectionFactory.getJDBCConnection("cn");
    java.sql.CallableStatement cstmtAccesso = null;
    cstmtAccesso = conn.createCallableStatement("{? = call ADMIN_AD4.REFRESH_SLAVES_JOB(?,?)}");
    try {
        cstmtAccesso.registerOutParameter(1, java.sql.Types.VARCHAR);
        cstmtAccesso.setString(2, "UTENTIG");
        cstmtAccesso.setString(3, istanza);
        cstmtAccesso.execute();
        retValue = cstmtAccesso.getString(1);
        if(retValue == null || "".equals(retValue)) {
            retValue = "NO";
        }
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4REFRESHSLAVES", retValue);
        cstmtAccesso.close();
    }catch (java.sql.SQLException sqle){
        sqle.printStackTrace();
    }
    conn.closeConnection();
}
//End Event AfterExecuteDelete Action AD4RefreshSlaves

//afterExecuteDelete Tail @38-FCB6E20C
    }
//End afterExecuteDelete Tail

//AD4_DIRITTI_ACCESSO1DataObjectHandler Tail @38-FCB6E20C
}
//End AD4_DIRITTI_ACCESSO1DataObjectHandler Tail

