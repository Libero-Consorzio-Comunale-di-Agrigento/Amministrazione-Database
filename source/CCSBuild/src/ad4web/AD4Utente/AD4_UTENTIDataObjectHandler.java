//AD4_UTENTIDataObjectHandler head @6-8F810F4A
package ad4web.AD4Utente;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AD4_UTENTIDataObjectHandler implements RecordDataObjectListener {
//End AD4_UTENTIDataObjectHandler head

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

//Event AfterExecuteSelect Action Save Control Value @160-BF90664D
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4UTENTE", ((com.codecharge.components.Label) ((com.codecharge.components.Record) (e.getPage().getChild( "AD4_UTENTI" ))).getChild( "UTENTE_VIS" )).getValue() );
//End Event AfterExecuteSelect Action Save Control Value

//DEL          SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4UTENTE", ((com.codecharge.components.Hidden) ((com.codecharge.components.Record) (e.getPage().getChild( "AD4_UTENTI" ))).getChild( "UTENTE" )).getValue() );


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

//Event BeforeExecuteInsert Action AD4RefreshSlaves @155-2B13205D
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
//End Event BeforeExecuteInsert Action AD4RefreshSlaves

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

//Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm @149-C405D236
    String sessionVar;
    int index;
    JDBCConnection ds = ((SPCommand)e.getCommand()).getJdbcConnection();
//End Event AfterExecuteUpdate Action BeforeSetVarSessionFromSPParm

//Event AfterExecuteUpdate Action SetVarSessionFromSPParm @150-DB27A210
    sessionVar = "AD4UTENTE";
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

//Event AfterExecuteUpdate Action AD4RefreshSlaves @153-2B13205D
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

//Event AfterExecuteDelete Action AD4RefreshSlaves @154-2B13205D
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

//afterExecuteDelete Tail @6-FCB6E20C
    }
//End afterExecuteDelete Tail

//AD4_UTENTIDataObjectHandler Tail @6-FCB6E20C
}
//End AD4_UTENTIDataObjectHandler Tail

