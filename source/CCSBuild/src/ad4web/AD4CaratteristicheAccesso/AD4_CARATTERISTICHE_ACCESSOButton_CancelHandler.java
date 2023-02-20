//AD4_CARATTERISTICHE_ACCESSOButton_CancelHandler Head @43-1C1C7394
package ad4web.AD4CaratteristicheAccesso;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_CARATTERISTICHE_ACCESSOButton_CancelHandler implements ButtonListener {
//End AD4_CARATTERISTICHE_ACCESSOButton_CancelHandler Head

//onClick Head @43-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Go to Session BackPage @190-1A056B21
String SessionBackPage = "AD4BP";
String backPage = (String) SessionStorage.getInstance(e.getPage().getRequest()).getAttribute(SessionBackPage);
// Ritorna alla pagina chiamante
e.getPage().setRedirectString(backPage);
//End Event OnClick Action Go to Session BackPage

//onClick Tail @43-FCB6E20C
}
//End onClick Tail

//beforeShow Head @43-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_CARATTERISTICHE_ACCESSOButton_CancelHandler Tail @43-F5FC18C5
    }
}
//End AD4_CARATTERISTICHE_ACCESSOButton_CancelHandler Tail

