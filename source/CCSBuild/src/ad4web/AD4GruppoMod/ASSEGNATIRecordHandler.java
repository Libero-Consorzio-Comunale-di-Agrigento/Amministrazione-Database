//ASSEGNATIHandler Head @63-3DC87D1F
package ad4web.AD4GruppoMod;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class ASSEGNATIRecordHandler implements RecordListener {
//End ASSEGNATIHandler Head

//BeforeShow Head @63-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @63-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @63-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @63-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @63-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @63-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @63-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @63-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @63-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @63-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @63-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//Event BeforeUpdate Action AD4GruppoModAgg @107-DAAD35D6
String ProcName = "UTGR_DELETE";
String ListName = "UTENTE_A";
java.sql.Connection con = null;
try {
    Properties siteProps = new Properties();
    siteProps = (Properties)ContextStorage.getInstance().getAttribute(Names.SITE_PROPERTIES_KEY);
    String connName = siteProps.getProperty("cn.url");
    connName = connName.substring(connName.indexOf("jdbc"));
    String charEncoding = (new CCSLocale()).getCharacterEncoding();
    
    javax.naming.Context initContext = new javax.naming.InitialContext();
    javax.naming.Context envContext = (javax.naming.Context)initContext.lookup("java:comp/env/");
    
    javax.sql.DataSource ds = (javax.sql.DataSource)envContext.lookup(connName);
    con = ds.getConnection();
    
    java.sql.CallableStatement cstmt = con.prepareCall("{call AD4WEB."+ ProcName +"(?, ?, ?)}");
    cstmt.setString(2, SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("AD4GRUPPO").toString());
    cstmt.setString(3, SessionStorage.getInstance(e.getPage().getRequest()).getAttribute("Utente").toString());
    String ErrMsg = "";
    String depErrMsg = "";
    int iUpper = e.getPage().getRequest().getParameterValues(ListName).length;
    for(int i=0;i<iUpper;i++) {
        try {
            cstmt.setString(1, e.getPage().getRequest().getParameterValues(ListName)[i]);
            cstmt.execute();
        }
        catch (java.sql.SQLException sqlexc) {
            depErrMsg = sqlexc.getMessage();
            ErrMsg += depErrMsg.substring(0,depErrMsg.indexOf("ORA-",5) - 1)+"<br>";
        }
    }
    cstmt.close();
    con.close();
    if (ErrMsg != "") {
        e.getRecord().addError(ErrMsg);
    }
}
catch (Exception exc) {
    try {
        exc.printStackTrace();
        con.close();
    }
    catch (Exception e1){
        e1.printStackTrace();
    }
    exc.printStackTrace();
}
//End Event BeforeUpdate Action AD4GruppoModAgg

//BeforeUpdate Tail @63-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @63-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @63-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @63-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @63-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @63-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @63-FCB6E20C
    }
//End AfterDelete Tail

//ASSEGNATIHandler Tail @63-FCB6E20C
}
//End ASSEGNATIHandler Tail

