//StatoTerritorioImpostaButton_InsertHandler Head @12-6C50D0D2
package ad4web.Ad4DizionariStatoTerritorioImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class StatoTerritorioImpostaButton_InsertHandler implements ButtonListener {
//End StatoTerritorioImpostaButton_InsertHandler Head

//onClick Head @12-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @13-798668F4
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariStatiTerritoriElenco"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariStatiTerritoriElenco"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @12-FCB6E20C
}
//End onClick Tail

//beforeShow Head @12-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//StatoTerritorioImpostaButton_InsertHandler Tail @12-F5FC18C5
    }
}
//End StatoTerritorioImpostaButton_InsertHandler Tail

