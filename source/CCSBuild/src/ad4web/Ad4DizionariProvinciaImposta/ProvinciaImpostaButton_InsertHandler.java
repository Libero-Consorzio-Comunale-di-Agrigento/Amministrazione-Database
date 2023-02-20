//ProvinciaImpostaButton_InsertHandler Head @12-D035DC48
package ad4web.Ad4DizionariProvinciaImposta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class ProvinciaImpostaButton_InsertHandler implements ButtonListener {
//End ProvinciaImpostaButton_InsertHandler Head

//onClick Head @12-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Add Get Parameter @13-4A1D1109
if (e.getPage().getRedirectString().indexOf("?") > 0) {
    char ampersand = 38;
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat(ampersand+"MVPG=" + "Ad4DizionariProvinceElenco"));
} else {
    e.getPage().setRedirectString(e.getPage().getRedirectString().concat("?MVPG=" + "Ad4DizionariProvinceElenco"));
}
//End Event OnClick Action Add Get Parameter

//onClick Tail @12-FCB6E20C
}
//End onClick Tail

//beforeShow Head @12-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//ProvinciaImpostaButton_InsertHandler Tail @12-F5FC18C5
    }
}
//End ProvinciaImpostaButton_InsertHandler Tail

