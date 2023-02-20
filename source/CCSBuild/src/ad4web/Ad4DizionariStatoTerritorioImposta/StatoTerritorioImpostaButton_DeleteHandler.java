//StatoTerritorioImpostaButton_DeleteHandler Head @16-72AB3D1D
package ad4web.Ad4DizionariStatoTerritorioImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class StatoTerritorioImpostaButton_DeleteHandler implements ButtonListener {
//End StatoTerritorioImpostaButton_DeleteHandler Head

//onClick Head @16-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @18-798668F4
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariStatiTerritoriElenco"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariStatiTerritoriElenco"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @16-FCB6E20C
}
//End onClick Tail

//beforeShow Head @16-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//StatoTerritorioImpostaButton_DeleteHandler Tail @16-F5FC18C5
    }
}
//End StatoTerritorioImpostaButton_DeleteHandler Tail

