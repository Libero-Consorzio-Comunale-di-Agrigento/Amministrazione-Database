//ComuneImpostaButton_UpdateHandler Head @14-79844762
package restrict.Ad4DizionariComuneBreveTraduzione;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class ComuneImpostaButton_UpdateHandler implements ButtonListener {
//End ComuneImpostaButton_UpdateHandler Head

//onClick Head @14-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @15-B76454A4
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariComuniElenco"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariComuniElenco"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @14-FCB6E20C
}
//End onClick Tail

//beforeShow Head @14-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//ComuneImpostaButton_UpdateHandler Tail @14-F5FC18C5
    }
}
//End ComuneImpostaButton_UpdateHandler Tail

