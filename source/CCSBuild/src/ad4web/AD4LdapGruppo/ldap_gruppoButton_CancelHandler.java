//ldap_gruppoButton_CancelHandler Head @146-9DB23C53
package ad4web.AD4LdapGruppo;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class ldap_gruppoButton_CancelHandler implements ButtonListener {
//End ldap_gruppoButton_CancelHandler Head

//onClick Head @146-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @147-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @146-FCB6E20C
}
//End onClick Tail

//beforeShow Head @146-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//ldap_gruppoButton_CancelHandler Tail @146-F5FC18C5
    }
}
//End ldap_gruppoButton_CancelHandler Tail

