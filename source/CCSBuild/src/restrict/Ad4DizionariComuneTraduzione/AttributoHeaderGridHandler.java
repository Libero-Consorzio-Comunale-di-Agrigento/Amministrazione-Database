//AttributoHeaderGridHandler @2-A31A0970
package restrict.Ad4DizionariComuneTraduzione;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttributoHeaderGridHandler implements GridListener {
//End AttributoHeaderGridHandler

// //beforeShow @2-F81417CB

//BeforeShow Head @2-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @2-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @2-F81417CB

//beforeShowRow Head @2-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @2-02CC09F2
        e.getGrid().getControl("Indietro").setValue("Indietro");
        e.getGrid().getLink("Indietro").getParameter("MVPG").setValue(( "Ad4DizionariComuniElenco" ));
//End Set values for static controls

//beforeShowRow Tail @2-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @2-F81417CB

//BeforeSelect Head @2-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @2-FCB6E20C
    }
//End BeforeSelect Tail

//AttributoHeaderHandler Tail @2-FCB6E20C
}
//End AttributoHeaderHandler Tail

