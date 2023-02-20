//AD4UtentiVGridHandler @142-65CCA25B
package ad4web.AD4UtentiRicerca;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AD4UtentiVGridHandler implements GridListener {
//End AD4UtentiVGridHandler

// //beforeShow @142-F81417CB

//BeforeShow Head @142-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values for static controls @142-A2BDC118
        e.getGrid().getControl("AFCNavigator").setValue(it.finmatica.jfc.ccs.view.Navigator.show(e.getPage(),e.getGrid()));
//End Set values for static controls

//BeforeShow Tail @142-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @142-F81417CB

//beforeShowRow Head @142-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//beforeShowRow Tail @142-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @142-F81417CB

//BeforeSelect Head @142-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @142-FCB6E20C
    }
//End BeforeSelect Tail

//AD4UtentiVHandler Tail @142-FCB6E20C
}
//End AD4UtentiVHandler Tail

