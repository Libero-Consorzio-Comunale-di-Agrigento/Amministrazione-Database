//RegioneImpostaButton_UpdateHandler Head @14-9AD21692
package restrict.Ad4DizionariRegioneImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class RegioneImpostaButton_UpdateHandler implements ButtonListener {
//End RegioneImpostaButton_UpdateHandler Head

//onClick Head @14-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @15-E570A0D2
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariRegioniElenco"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariRegioniElenco"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @14-FCB6E20C
}
//End onClick Tail

//beforeShow Head @14-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//RegioneImpostaButton_UpdateHandler Tail @14-F5FC18C5
    }
}
//End RegioneImpostaButton_UpdateHandler Tail

