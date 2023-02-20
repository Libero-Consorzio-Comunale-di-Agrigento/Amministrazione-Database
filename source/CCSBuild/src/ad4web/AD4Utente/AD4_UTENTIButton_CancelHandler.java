//AD4_UTENTIButton_CancelHandler Head @11-D4AF0E87
package ad4web.AD4Utente;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_UTENTIButton_CancelHandler implements ButtonListener {
//End AD4_UTENTIButton_CancelHandler Head

//onClick Head @11-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @113-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @11-FCB6E20C
}
//End onClick Tail

//beforeShow Head @11-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_UTENTIButton_CancelHandler Tail @11-F5FC18C5
    }
}
//End AD4_UTENTIButton_CancelHandler Tail

