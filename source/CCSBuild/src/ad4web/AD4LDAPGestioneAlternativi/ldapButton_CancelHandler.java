//ldapButton_CancelHandler Head @14-7B3661A3
package ad4web.AD4LDAPGestioneAlternativi;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class ldapButton_CancelHandler implements ButtonListener {
//End ldapButton_CancelHandler Head

//onClick Head @14-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @27-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @14-FCB6E20C
}
//End onClick Tail

//beforeShow Head @14-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//ldapButton_CancelHandler Tail @14-F5FC18C5
    }
}
//End ldapButton_CancelHandler Tail

