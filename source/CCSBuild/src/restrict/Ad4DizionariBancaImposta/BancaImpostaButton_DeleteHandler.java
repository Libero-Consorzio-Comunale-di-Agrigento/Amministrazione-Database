//BancaImpostaButton_DeleteHandler Head @16-600E7EC6
package restrict.Ad4DizionariBancaImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class BancaImpostaButton_DeleteHandler implements ButtonListener {
//End BancaImpostaButton_DeleteHandler Head

//onClick Head @16-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @18-BDA5590B
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariBancheElenco"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariBancheElenco"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @16-FCB6E20C
}
//End onClick Tail

//beforeShow Head @16-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//BancaImpostaButton_DeleteHandler Tail @16-F5FC18C5
    }
}
//End BancaImpostaButton_DeleteHandler Tail

