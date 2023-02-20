//ASSEGNATIDataObjectHandler head @63-FF8FCF73
package ad4web.AD4GruppoMod;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ASSEGNATIDataObjectHandler implements RecordDataObjectListener {
//End ASSEGNATIDataObjectHandler head

// //BeforeBuildSelect @63-F81417CB

//beforeBuildSelect head @63-3041BA14
    public void beforeBuildSelect( DataObjectEvent e) {
//End beforeBuildSelect head

//BeforeBuildSelect Tail @63-FCB6E20C
    }
//End BeforeBuildSelect Tail

// //beforeExecuteSelect @63-F81417CB

//beforeExecuteSelect head @63-8391D9D6
    public void beforeExecuteSelect( DataObjectEvent e) {
//End beforeExecuteSelect head

//beforeExecuteSelect Tail @63-FCB6E20C
    }
//End beforeExecuteSelect Tail

// //afterExecuteSelect @63-F81417CB

//afterExecuteSelect Head @63-0972E7FA
    public void afterExecuteSelect( DataObjectEvent e) {
//End afterExecuteSelect Head

//afterExecuteSelect Tail @63-FCB6E20C
    }
//End afterExecuteSelect Tail

// //BeforeBuildInsert @63-F81417CB

//beforeBuildInsert head @63-FD6471B0
    public void beforeBuildInsert( DataObjectEvent e) {
//End beforeBuildInsert head

//BeforeBuildInsert Tail @63-FCB6E20C
    }
//End BeforeBuildInsert Tail

// //beforeExecuteInsert @63-F81417CB

//beforeExecuteInsert head @63-4EB41272
    public void beforeExecuteInsert( DataObjectEvent e) {
//End beforeExecuteInsert head

//beforeExecuteInsert Tail @63-FCB6E20C
    }
//End beforeExecuteInsert Tail

// //afterExecuteInsert @63-F81417CB

//afterExecuteInsert Head @63-C4572C5E
    public void afterExecuteInsert( DataObjectEvent e) {
//End afterExecuteInsert Head

//afterExecuteInsert Tail @63-FCB6E20C
    }
//End afterExecuteInsert Tail

// //BeforeBuildUpdate @63-F81417CB

//beforeBuildUpdate head @63-37688606
    public void beforeBuildUpdate( DataObjectEvent e) {
//End beforeBuildUpdate head

//BeforeBuildUpdate Tail @63-FCB6E20C
    }
//End BeforeBuildUpdate Tail

// //beforeExecuteUpdate @63-F81417CB

//beforeExecuteUpdate head @63-84B8E5C4
    public void beforeExecuteUpdate( DataObjectEvent e) {
//End beforeExecuteUpdate head

//beforeExecuteUpdate Tail @63-FCB6E20C
    }
//End beforeExecuteUpdate Tail

// //afterExecuteUpdate @63-F81417CB

//afterExecuteUpdate Head @63-0E5BDBE8
    public void afterExecuteUpdate( DataObjectEvent e) {
//End afterExecuteUpdate Head

//Event AfterExecuteUpdate Action AD4RefreshSlaves @112-2B13205D
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

//afterExecuteUpdate Tail @63-FCB6E20C
    }
//End afterExecuteUpdate Tail

// //BeforeBuildDelete @63-F81417CB

//beforeBuildDelete head @63-01A46505
    public void beforeBuildDelete( DataObjectEvent e) {
//End beforeBuildDelete head

//BeforeBuildDelete Tail @63-FCB6E20C
    }
//End BeforeBuildDelete Tail

// //beforeExecuteDelete @63-F81417CB

//beforeExecuteDelete head @63-B27406C7
    public void beforeExecuteDelete( DataObjectEvent e) {
//End beforeExecuteDelete head

//beforeExecuteDelete Tail @63-FCB6E20C
    }
//End beforeExecuteDelete Tail

// //afterExecuteDelete @63-F81417CB

//afterExecuteDelete Head @63-389738EB
    public void afterExecuteDelete( DataObjectEvent e) {
//End afterExecuteDelete Head

//Event AfterExecuteDelete Action AD4RefreshSlaves @113-2B13205D
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

//afterExecuteDelete Tail @63-FCB6E20C
    }
//End afterExecuteDelete Tail

//ASSEGNATIDataObjectHandler Tail @63-FCB6E20C
}
//End ASSEGNATIDataObjectHandler Tail

