//AD4_MODULIGridHandler @6-14039607
package ad4web.AD4ModuElenco;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AD4_MODULIGridHandler implements GridListener {
//End AD4_MODULIGridHandler

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

//Set values for static controls @6-0F82168B
        e.getGrid().getLink("CaratteristicheAccesso").getParameter("TIPO_ACCESSO").setValue(( "M" ));
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

//AD4_MODULIHandler Tail @6-FCB6E20C
}
//End AD4_MODULIHandler Tail

