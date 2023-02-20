//ad4AuthenticateDataObjectHandler head @11-C736E882
package ad4web.AD4AutenticazioneAd4Utente;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ad4AuthenticateDataObjectHandler implements RecordDataObjectListener {
//End ad4AuthenticateDataObjectHandler head

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

//Event BeforeExecuteUpdate Action AD4RefreshSlaves @19-2B13205D
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
//End Event BeforeExecuteUpdate Action AD4RefreshSlaves

//beforeExecuteUpdate Tail @11-FCB6E20C
    }
//End beforeExecuteUpdate Tail

// //afterExecuteUpdate @11-F81417CB

//afterExecuteUpdate Head @11-0E5BDBE8
    public void afterExecuteUpdate( DataObjectEvent e) {
//End afterExecuteUpdate Head

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

//ad4AuthenticateDataObjectHandler Tail @11-FCB6E20C
}
//End ad4AuthenticateDataObjectHandler Tail

