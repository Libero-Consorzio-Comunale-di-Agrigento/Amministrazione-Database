//AD4_AMBIENTEGridHandler @6-9D73A144
package ad4web.AD4Ambiente;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AD4_AMBIENTEGridHandler implements GridListener {
//End AD4_AMBIENTEGridHandler

// //beforeShow @6-F81417CB

//BeforeShow Head @6-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action Custom Code @14-44795B7A

class LoginTimeoutException extends Exception {

    String value;

    public String toString() {
        return "LoginTimeoutException: " + value;
    }

   public LoginTimeoutException(String errorMessage) {
        super(errorMessage);
        value = errorMessage;
    }
}

  // EXTERNAL_AUTENTICATION e' un parametro di registro che indica se autenticazione esterna quale metodo viene usato (shibboleth/cas/... - default:null => autenticazione in ad4 o ldap)
  // Se e' presente autenticazione esterna allora disabilita verifca token sa4ck
  String externalAutentication = "";
  if (e.getGrid().getAmountOfRows() > 0) {
    e.getGrid().initializeRows();
    HashMap row = e.getGrid().nextRow();
    externalAutentication = row.get("EXTERNAL_AUTENTICATION").toString();
  }
  if (externalAutentication == null || "".equals(externalAutentication)) {
    String sa4ck = (String)e.getPage().getRequest().getSession().getAttribute("sa4ck");
    try {
         if (sa4ck != null && !sa4ck.equals("")) {
             String sa4ckDecr = new it.finmatica.sa4.crypto.SanCrypter().decryptB64(sa4ck);
             long before = Long.parseLong(sa4ckDecr.substring(sa4ckDecr.indexOf("#") + 1));
             
             long age = (System.currentTimeMillis() - before) / 1000; //durata in secondi
             if (age > (120)) {
                 throw new LoginTimeoutException("Timeout nel login: "+age+" "+before+" "+System.currentTimeMillis());
             }
         } else {
            throw new Exception("ck null");
         }
         //e.getPage().getRequest().getSession().removeAttribute("sa4ck");
         //response.sendRedirect(request.getContextPath() + "/restrict/AmvPortalApplications.do");
     } catch (LoginTimeoutException to) {
         e.getPage().getRequest().getSession().invalidate();
        try{ e.getPage().getResponse().sendRedirect(e.getPage().getRequest().getContextPath() + "/common/Sa4Error.jsp?e=" + to.toString() ); }catch(Exception e1){}
     } catch (Exception tt) {
         //out.println(tt);
         e.getPage().getRequest().getSession().invalidate();
         try{ e.getPage().getResponse().sendRedirect(e.getPage().getRequest().getContextPath() + "/common/Sa4Error.jsp?e=Rilevato attacco CSRF (" + sa4ck + ") "+tt.getMessage());}catch(Exception e1){}
     }
  }

//End Event BeforeShow Action Custom Code

//BeforeShow Tail @6-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @6-F81417CB

//beforeShowRow Head @6-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//beforeShowRow Tail @6-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @6-F81417CB

//BeforeSelect Head @6-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @6-FCB6E20C
    }
//End BeforeSelect Tail

//AD4_AMBIENTEHandler Tail @6-FCB6E20C
}
//End AD4_AMBIENTEHandler Tail

