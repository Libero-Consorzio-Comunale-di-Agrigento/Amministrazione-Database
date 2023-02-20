//UTENTI_GRUPPOGridHandler @78-7207EB44
package ad4web.AD4Gruppo;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UTENTI_GRUPPOGridHandler implements GridListener {
//End UTENTI_GRUPPOGridHandler

// //beforeShow @78-F81417CB

//BeforeShow Head @78-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values for static controls @78-A2BDC118
        e.getGrid().getControl("AFCNavigator").setValue(it.finmatica.jfc.ccs.view.Navigator.show(e.getPage(),e.getGrid()));
//End Set values for static controls

//BeforeShow Tail @78-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @78-F81417CB

//beforeShowRow Head @78-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//beforeShowRow Tail @78-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @78-F81417CB

//BeforeSelect Head @78-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @78-FCB6E20C
    }
//End BeforeSelect Tail

//UTENTI_GRUPPOHandler Tail @78-FCB6E20C
}
//End UTENTI_GRUPPOHandler Tail

