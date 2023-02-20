//AslComuneImpostaButton_UpdateHandler Head @14-7E55CB0C
package restrict.Ad4DizionariAslComuneImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AslComuneImpostaButton_UpdateHandler implements ButtonListener {
//End AslComuneImpostaButton_UpdateHandler Head

//onClick Head @14-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @15-DE199147
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariAslImposta"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariAslImposta"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @14-FCB6E20C
}
//End onClick Tail

//beforeShow Head @14-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AslComuneImpostaButton_UpdateHandler Tail @14-F5FC18C5
    }
}
//End AslComuneImpostaButton_UpdateHandler Tail

