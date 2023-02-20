//AslComuneImpostaButton_DeleteHandler Head @16-32CA3A12
package restrict.Ad4DizionariAslComuneImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AslComuneImpostaButton_DeleteHandler implements ButtonListener {
//End AslComuneImpostaButton_DeleteHandler Head

//onClick Head @16-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @18-DE199147
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariAslImposta"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariAslImposta"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @16-FCB6E20C
}
//End onClick Tail

//beforeShow Head @16-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AslComuneImpostaButton_DeleteHandler Tail @16-F5FC18C5
    }
}
//End AslComuneImpostaButton_DeleteHandler Tail

