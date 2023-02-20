//AslComuneImpostaButton_CancelHandler Head @19-2CA51D26
package restrict.Ad4DizionariAslComuneImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AslComuneImpostaButton_CancelHandler implements ButtonListener {
//End AslComuneImpostaButton_CancelHandler Head

//onClick Head @19-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @20-DE199147
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariAslImposta"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariAslImposta"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @19-FCB6E20C
}
//End onClick Tail

//beforeShow Head @19-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AslComuneImpostaButton_CancelHandler Tail @19-F5FC18C5
    }
}
//End AslComuneImpostaButton_CancelHandler Tail

