//BancheElencoGridHandler @8-B9F7DB31
package restrict.Ad4DizionariBancheElenco;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BancheElencoGridHandler implements GridListener {
//End BancheElencoGridHandler

// //beforeShow @8-F81417CB

//BeforeShow Head @8-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values for static controls @8-6C16BF3B
        e.getGrid().getControl("Aggiungi").setValue("Nuova");
        e.getGrid().getControl("AFCNavigator").setValue(it.finmatica.jfc.ccs.view.Navigator.show(e.getPage(),e.getGrid()));
        e.getGrid().getLink("Aggiungi").getParameter("MVPG").setValue(( "Ad4DizionariBancaImposta" ));
//End Set values for static controls

//BeforeShow Tail @8-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @8-F81417CB

//beforeShowRow Head @8-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @8-88CBF869
        e.getGrid().getLink("DENOMINAZIONE").getParameter("MVPG").setValue(( "Ad4DizionariBancaImposta" ));
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

//BancheElencoHandler Tail @8-FCB6E20C
}
//End BancheElencoHandler Tail

