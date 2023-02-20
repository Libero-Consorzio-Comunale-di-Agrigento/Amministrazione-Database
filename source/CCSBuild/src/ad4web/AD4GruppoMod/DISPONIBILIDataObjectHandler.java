//DISPONIBILIDataObjectHandler head @52-37DCC325
package ad4web.AD4GruppoMod;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DISPONIBILIDataObjectHandler implements RecordDataObjectListener {
//End DISPONIBILIDataObjectHandler head

// //BeforeBuildSelect @52-F81417CB

//beforeBuildSelect head @52-3041BA14
    public void beforeBuildSelect( DataObjectEvent e) {
//End beforeBuildSelect head

//BeforeBuildSelect Tail @52-FCB6E20C
    }
//End BeforeBuildSelect Tail

// //beforeExecuteSelect @52-F81417CB

//beforeExecuteSelect head @52-8391D9D6
    public void beforeExecuteSelect( DataObjectEvent e) {
//End beforeExecuteSelect head

//beforeExecuteSelect Tail @52-FCB6E20C
    }
//End beforeExecuteSelect Tail

// //afterExecuteSelect @52-F81417CB

//afterExecuteSelect Head @52-0972E7FA
    public void afterExecuteSelect( DataObjectEvent e) {
//End afterExecuteSelect Head

//afterExecuteSelect Tail @52-FCB6E20C
    }
//End afterExecuteSelect Tail

// //BeforeBuildInsert @52-F81417CB

//beforeBuildInsert head @52-FD6471B0
    public void beforeBuildInsert( DataObjectEvent e) {
//End beforeBuildInsert head

//BeforeBuildInsert Tail @52-FCB6E20C
    }
//End BeforeBuildInsert Tail

// //beforeExecuteInsert @52-F81417CB

//beforeExecuteInsert head @52-4EB41272
    public void beforeExecuteInsert( DataObjectEvent e) {
//End beforeExecuteInsert head

//beforeExecuteInsert Tail @52-FCB6E20C
    }
//End beforeExecuteInsert Tail

// //afterExecuteInsert @52-F81417CB

//afterExecuteInsert Head @52-C4572C5E
    public void afterExecuteInsert( DataObjectEvent e) {
//End afterExecuteInsert Head

//afterExecuteInsert Tail @52-FCB6E20C
    }
//End afterExecuteInsert Tail

// //BeforeBuildUpdate @52-F81417CB

//beforeBuildUpdate head @52-37688606
    public void beforeBuildUpdate( DataObjectEvent e) {
//End beforeBuildUpdate head

//BeforeBuildUpdate Tail @52-FCB6E20C
    }
//End BeforeBuildUpdate Tail

// //beforeExecuteUpdate @52-F81417CB

//beforeExecuteUpdate head @52-84B8E5C4
    public void beforeExecuteUpdate( DataObjectEvent e) {
//End beforeExecuteUpdate head

//beforeExecuteUpdate Tail @52-FCB6E20C
    }
//End beforeExecuteUpdate Tail

// //afterExecuteUpdate @52-F81417CB

//afterExecuteUpdate Head @52-0E5BDBE8
    public void afterExecuteUpdate( DataObjectEvent e) {
//End afterExecuteUpdate Head

//Event AfterExecuteUpdate Action AD4RefreshSlaves @110-2B13205D
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

//afterExecuteUpdate Tail @52-FCB6E20C
    }
//End afterExecuteUpdate Tail

// //BeforeBuildDelete @52-F81417CB

//beforeBuildDelete head @52-01A46505
    public void beforeBuildDelete( DataObjectEvent e) {
//End beforeBuildDelete head

//BeforeBuildDelete Tail @52-FCB6E20C
    }
//End BeforeBuildDelete Tail

// //beforeExecuteDelete @52-F81417CB

//beforeExecuteDelete head @52-B27406C7
    public void beforeExecuteDelete( DataObjectEvent e) {
//End beforeExecuteDelete head

//beforeExecuteDelete Tail @52-FCB6E20C
    }
//End beforeExecuteDelete Tail

// //afterExecuteDelete @52-F81417CB

//afterExecuteDelete Head @52-389738EB
    public void afterExecuteDelete( DataObjectEvent e) {
//End afterExecuteDelete Head

//Event AfterExecuteDelete Action AD4RefreshSlaves @111-2B13205D
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

//afterExecuteDelete Tail @52-FCB6E20C
    }
//End afterExecuteDelete Tail

//DISPONIBILIDataObjectHandler Tail @52-FCB6E20C
}
//End DISPONIBILIDataObjectHandler Tail

