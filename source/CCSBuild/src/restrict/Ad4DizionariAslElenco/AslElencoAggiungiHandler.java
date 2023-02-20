//AslElencoAggiungiHandler Head @9-46B432B4
package restrict.Ad4DizionariAslElenco;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AslElencoAggiungiHandler implements ControlListener {
//End AslElencoAggiungiHandler Head

//BeforeShow Head @9-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action Custom Code @31-44795B7A
 e.getGrid().getControl("Aggiungi").setValue("Nuova");
 e.getGrid().getControl("AFCNavigator").setValue(it.finmatica.jfc.ccs.view.Navigator.show(e.getPage(),e.getGrid()));
 e.getGrid().getLink("Aggiungi").getParameter("MVPG").setValue(( "Ad4DizionariAslImposta" ));
//End Event BeforeShow Action Custom Code

//BeforeShow Tail @9-FCB6E20C
    }
//End BeforeShow Tail

//AslElencoAggiungiHandler Tail @9-FCB6E20C
}
//End AslElencoAggiungiHandler Tail

