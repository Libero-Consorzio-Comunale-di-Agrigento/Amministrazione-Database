//AD4_SERVIZIButton_DeleteHandler Head @41-754CFC39
package ad4web.AD4Servizio;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_SERVIZIButton_DeleteHandler implements ButtonListener {
//End AD4_SERVIZIButton_DeleteHandler Head

//onClick Head @41-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @183-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @41-FCB6E20C
}
//End onClick Tail

//beforeShow Head @41-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_SERVIZIButton_DeleteHandler Tail @41-F5FC18C5
    }
}
//End AD4_SERVIZIButton_DeleteHandler Tail

