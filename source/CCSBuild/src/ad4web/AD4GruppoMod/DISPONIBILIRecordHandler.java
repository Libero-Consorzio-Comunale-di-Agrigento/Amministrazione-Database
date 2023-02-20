//DISPONIBILIHandler Head @52-BC65F95E
package ad4web.AD4GruppoMod;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class DISPONIBILIRecordHandler implements RecordListener {
//End DISPONIBILIHandler Head

//BeforeShow Head @52-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @52-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @52-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @52-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @52-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @52-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @52-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @52-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @52-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @52-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @52-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//Event BeforeUpdate Action AD4GruppoModAgg @106-13395876
String ProcName = "UTGR_INSERT";
String ListName = "UTENTE_D";
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

//BeforeUpdate Tail @52-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @52-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @52-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @52-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @52-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @52-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @52-FCB6E20C
    }
//End AfterDelete Tail

//DISPONIBILIHandler Tail @52-FCB6E20C
}
//End DISPONIBILIHandler Tail

