//ldap_gruppoButton_DeleteHandler Head @141-83DD1B67
package ad4web.AD4LdapGruppo;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class ldap_gruppoButton_DeleteHandler implements ButtonListener {
//End ldap_gruppoButton_DeleteHandler Head

//onClick Head @141-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @145-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @141-FCB6E20C
}
//End onClick Tail

//beforeShow Head @141-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//ldap_gruppoButton_DeleteHandler Tail @141-F5FC18C5
    }
}
//End ldap_gruppoButton_DeleteHandler Tail

