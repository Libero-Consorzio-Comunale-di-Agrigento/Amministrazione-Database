//AD4_SOGGETTOButton_CancelHandler Head @23-8337D655
package ad4web.AD4SoggettoInclusa;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_SOGGETTOButton_CancelHandler implements ButtonListener {
//End AD4_SOGGETTOButton_CancelHandler Head

//onClick Head @23-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @70-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @23-FCB6E20C
}
//End onClick Tail

//beforeShow Head @23-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_SOGGETTOButton_CancelHandler Tail @23-F5FC18C5
    }
}
//End AD4_SOGGETTOButton_CancelHandler Tail

