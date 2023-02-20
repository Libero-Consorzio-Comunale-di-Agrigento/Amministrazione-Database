//ldap_gruppoButton_UpdateHandler Head @140-CF42EA79
package ad4web.AD4LdapGruppo;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class ldap_gruppoButton_UpdateHandler implements ButtonListener {
//End ldap_gruppoButton_UpdateHandler Head

//onClick Head @140-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @144-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @140-FCB6E20C
}
//End onClick Tail

//beforeShow Head @140-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//ldap_gruppoButton_UpdateHandler Tail @140-F5FC18C5
    }
}
//End ldap_gruppoButton_UpdateHandler Tail

