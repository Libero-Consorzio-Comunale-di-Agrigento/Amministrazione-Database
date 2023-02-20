//AccessiDettaglioGridHandler @22-1E0AACCF
package ad4web.AD4AccessiTree;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AccessiDettaglioGridHandler implements GridListener {
//End AccessiDettaglioGridHandler

// //beforeShow @22-F81417CB

//BeforeShow Head @22-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values for static controls @22-A2BDC118
        e.getGrid().getControl("AFCNavigator").setValue(it.finmatica.jfc.ccs.view.Navigator.show(e.getPage(),e.getGrid()));
//End Set values for static controls

//Event BeforeShow Action Custom Code @27-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
//End Event BeforeShow Action Custom Code

//BeforeShow Tail @22-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @22-F81417CB

//beforeShowRow Head @22-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//beforeShowRow Tail @22-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @22-F81417CB

//BeforeSelect Head @22-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @22-FCB6E20C
    }
//End BeforeSelect Tail

//AccessiDettaglioHandler Tail @22-FCB6E20C
}
//End AccessiDettaglioHandler Tail

