//AD4_SERVIZIGridHandler @6-9E621998
package ad4web.AD4Servizio;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AD4_SERVIZIGridHandler implements GridListener {
//End AD4_SERVIZIGridHandler

// //beforeShow @6-F81417CB

//BeforeShow Head @6-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values for static controls @6-48CBC1D1
        e.getGrid().getLink("AD4_DIRITTI_ACCESSO_Insert").getParameter("B").setValue(( "#Servizio" ));
//End Set values for static controls

//BeforeShow Tail @6-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @6-F81417CB

//beforeShowRow Head @6-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @6-34665742
        if ((e.getGrid().getCurrentRowNumber()%2) == 0 ) {
            e.getGrid().getLink("ID_SERVIZIO").getParameter("B").setValue(( "#Servizio" ));
            e.getGrid().getLink("DES_MODULO").getParameter("B").setValue(( "#Servizio" ));
            e.getGrid().getLink("DES_ISTANZA").getParameter("B").setValue(( "#Servizio" ));
        } else {
            e.getGrid().getLink("Alt_ID_SERVIZIO").getParameter("B").setValue(( "#Servizio" ));
            e.getGrid().getLink("Alt_DES_MODULO").getParameter("B").setValue(( "#Servizio" ));
            e.getGrid().getLink("Alt_DES_ISTANZA").getParameter("B").setValue(( "#Servizio" ));
        }
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

//AD4_SERVIZIHandler Tail @6-FCB6E20C
}
//End AD4_SERVIZIHandler Tail

