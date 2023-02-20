//EVENTIButton_CancelHandler Head @60-217A0311
package ad4web.AD4Evento;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class EVENTIButton_CancelHandler implements ButtonListener {
//End EVENTIButton_CancelHandler Head

//onClick Head @60-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @155-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @60-FCB6E20C
}
//End onClick Tail

//beforeShow Head @60-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//EVENTIButton_CancelHandler Tail @60-F5FC18C5
    }
}
//End EVENTIButton_CancelHandler Tail

