//AD4_PROGETTIButton_UpdateHandler Head @25-CBCEA7BE
package ad4web.AD4Progetto;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_PROGETTIButton_UpdateHandler implements ButtonListener {
//End AD4_PROGETTIButton_UpdateHandler Head

//onClick Head @25-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @54-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @25-FCB6E20C
}
//End onClick Tail

//beforeShow Head @25-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_PROGETTIButton_UpdateHandler Tail @25-F5FC18C5
    }
}
//End AD4_PROGETTIButton_UpdateHandler Tail

