//AD4_ISTANZEButton_UpdateHandler Head @57-DB01CA90
package ad4web.AD4Istanza;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_ISTANZEButton_UpdateHandler implements ButtonListener {
//End AD4_ISTANZEButton_UpdateHandler Head

//onClick Head @57-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @153-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @57-FCB6E20C
}
//End onClick Tail

//beforeShow Head @57-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_ISTANZEButton_UpdateHandler Tail @57-F5FC18C5
    }
}
//End AD4_ISTANZEButton_UpdateHandler Tail

