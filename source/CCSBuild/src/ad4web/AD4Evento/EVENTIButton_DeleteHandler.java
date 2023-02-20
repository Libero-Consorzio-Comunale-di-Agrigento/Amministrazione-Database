//EVENTIButton_DeleteHandler Head @58-3F152425
package ad4web.AD4Evento;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class EVENTIButton_DeleteHandler implements ButtonListener {
//End EVENTIButton_DeleteHandler Head

//onClick Head @58-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @154-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @58-FCB6E20C
}
//End onClick Tail

//beforeShow Head @58-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//EVENTIButton_DeleteHandler Tail @58-F5FC18C5
    }
}
//End EVENTIButton_DeleteHandler Tail

