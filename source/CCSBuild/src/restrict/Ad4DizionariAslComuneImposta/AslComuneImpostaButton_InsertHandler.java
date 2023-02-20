//AslComuneImpostaButton_InsertHandler Head @12-2C31D7DD
package restrict.Ad4DizionariAslComuneImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AslComuneImpostaButton_InsertHandler implements ButtonListener {
//End AslComuneImpostaButton_InsertHandler Head

//onClick Head @12-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @13-DE199147
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariAslImposta"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariAslImposta"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @12-FCB6E20C
}
//End onClick Tail

//beforeShow Head @12-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AslComuneImpostaButton_InsertHandler Tail @12-F5FC18C5
    }
}
//End AslComuneImpostaButton_InsertHandler Tail

