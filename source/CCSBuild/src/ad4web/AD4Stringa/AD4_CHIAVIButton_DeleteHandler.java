//AD4_CHIAVIButton_DeleteHandler Head @115-8BB9FE0A
package ad4web.AD4Stringa;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_CHIAVIButton_DeleteHandler implements ButtonListener {
//End AD4_CHIAVIButton_DeleteHandler Head

//onClick Head @115-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @174-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @115-FCB6E20C
}
//End onClick Tail

//beforeShow Head @115-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_CHIAVIButton_DeleteHandler Tail @115-F5FC18C5
    }
}
//End AD4_CHIAVIButton_DeleteHandler Tail

