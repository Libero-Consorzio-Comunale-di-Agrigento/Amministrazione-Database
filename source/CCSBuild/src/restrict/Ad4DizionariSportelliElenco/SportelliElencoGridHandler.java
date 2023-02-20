//SportelliElencoGridHandler @8-24871BC8
package restrict.Ad4DizionariSportelliElenco;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SportelliElencoGridHandler implements GridListener {
//End SportelliElencoGridHandler

// //beforeShow @8-F81417CB

//BeforeShow Head @8-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values for static controls @8-D909EA65
        e.getGrid().getControl("Aggiungi").setValue("Nuova");
        e.getGrid().getControl("AFCNavigator").setValue(it.finmatica.jfc.ccs.view.Navigator.show(e.getPage(),e.getGrid()));
        e.getGrid().getLink("Aggiungi").getParameter("MVPG").setValue(( "Ad4DizionariSportelloImposta" ));
//End Set values for static controls

//BeforeShow Tail @8-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @8-F81417CB

//beforeShowRow Head @8-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @8-230D0E75
        e.getGrid().getLink("CAB").getParameter("MVPG").setValue(( "Ad4DizionariSportelloImposta" ));
//End Set values for static controls

//beforeShowRow Tail @8-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @8-F81417CB

//BeforeSelect Head @8-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @8-FCB6E20C
    }
//End BeforeSelect Tail

//SportelliElencoHandler Tail @8-FCB6E20C
}
//End SportelliElencoHandler Tail

