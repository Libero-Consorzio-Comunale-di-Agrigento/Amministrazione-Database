//SportelliElencoAggiungiHandler Head @9-190F08A7
package restrict.Ad4DizionariSportelliElenco;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class SportelliElencoAggiungiHandler implements ControlListener {
//End SportelliElencoAggiungiHandler Head

//BeforeShow Head @9-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action Custom Code @34-44795B7A
 e.getGrid().getControl("Aggiungi").setValue("Nuova");
 e.getGrid().getControl("AFCNavigator").setValue(it.finmatica.jfc.ccs.view.Navigator.show(e.getPage(),e.getGrid()));
 e.getGrid().getLink("Aggiungi").getParameter("MVPG").setValue(( "Ad4DizionariSportelloImposta" ));
//End Event BeforeShow Action Custom Code

//BeforeShow Tail @9-FCB6E20C
    }
//End BeforeShow Tail

//SportelliElencoAggiungiHandler Tail @9-FCB6E20C
}
//End SportelliElencoAggiungiHandler Tail

