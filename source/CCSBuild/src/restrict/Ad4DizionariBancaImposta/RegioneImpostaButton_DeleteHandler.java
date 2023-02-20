//RegioneImpostaButton_DeleteHandler Head @16-0B0E10E0
package restrict.Ad4DizionariBancaImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class RegioneImpostaButton_DeleteHandler implements ButtonListener {
//End RegioneImpostaButton_DeleteHandler Head

//onClick Head @16-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @18-E570A0D2
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariRegioniElenco"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariRegioniElenco"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @16-FCB6E20C
}
//End onClick Tail

//beforeShow Head @16-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//RegioneImpostaButton_DeleteHandler Tail @16-F5FC18C5
    }
}
//End RegioneImpostaButton_DeleteHandler Tail

