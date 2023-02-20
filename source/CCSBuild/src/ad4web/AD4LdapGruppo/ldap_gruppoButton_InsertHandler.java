//ldap_gruppoButton_InsertHandler Head @139-9D26F6A8
package ad4web.AD4LdapGruppo;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class ldap_gruppoButton_InsertHandler implements ButtonListener {
//End ldap_gruppoButton_InsertHandler Head

//onClick Head @139-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @143-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @139-FCB6E20C
}
//End onClick Tail

//beforeShow Head @139-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//ldap_gruppoButton_InsertHandler Tail @139-F5FC18C5
    }
}
//End ldap_gruppoButton_InsertHandler Tail

