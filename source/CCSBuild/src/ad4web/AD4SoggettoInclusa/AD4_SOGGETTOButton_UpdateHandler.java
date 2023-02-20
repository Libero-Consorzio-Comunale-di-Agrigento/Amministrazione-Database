//AD4_SOGGETTOButton_UpdateHandler Head @22-D1C7007F
package ad4web.AD4SoggettoInclusa;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_SOGGETTOButton_UpdateHandler implements ButtonListener {
//End AD4_SOGGETTOButton_UpdateHandler Head

//onClick Head @22-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @187-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @22-FCB6E20C
}
//End onClick Tail

//beforeShow Head @22-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_SOGGETTOButton_UpdateHandler Tail @22-F5FC18C5
    }
}
//End AD4_SOGGETTOButton_UpdateHandler Tail

