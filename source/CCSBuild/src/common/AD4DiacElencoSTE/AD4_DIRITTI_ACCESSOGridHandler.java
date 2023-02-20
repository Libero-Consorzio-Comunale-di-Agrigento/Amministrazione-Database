//AD4_DIRITTI_ACCESSOGridHandler @6-C8D1D9D5
package common.AD4DiacElencoSTE;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AD4_DIRITTI_ACCESSOGridHandler implements GridListener {
//End AD4_DIRITTI_ACCESSOGridHandler

// //beforeShow @6-F81417CB

//BeforeShow Head @6-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values for static controls @6-A2BDC118
        e.getGrid().getControl("AFCNavigator").setValue(it.finmatica.jfc.ccs.view.Navigator.show(e.getPage(),e.getGrid()));
//End Set values for static controls

//BeforeShow Tail @6-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @6-F81417CB

//beforeShowRow Head @6-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @6-28A02745
        e.getGrid().getLink("SEQUENZA").getParameter("MODULO_LISTBOX").setValue(( "" ));
        e.getGrid().getLink("SEQUENZA").getParameter("ISTANZA_LISTBOX").setValue(( "" ));
        e.getGrid().getLink("DES_MODULO").getParameter("MODULO_LISTBOX").setValue(( "" ));
        e.getGrid().getLink("DES_MODULO").getParameter("ISTANZA_LISTBOX").setValue(( "" ));
        e.getGrid().getLink("DES_ISTANZA").getParameter("MODULO_LISTBOX").setValue(( "" ));
        e.getGrid().getLink("DES_ISTANZA").getParameter("ISTANZA_LISTBOX").setValue(( "" ));
//End Set values for static controls

//beforeShowRow Tail @6-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @6-F81417CB

//BeforeSelect Head @6-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @6-FCB6E20C
    }
//End BeforeSelect Tail

//AD4_DIRITTI_ACCESSOHandler Tail @6-FCB6E20C
}
//End AD4_DIRITTI_ACCESSOHandler Tail

