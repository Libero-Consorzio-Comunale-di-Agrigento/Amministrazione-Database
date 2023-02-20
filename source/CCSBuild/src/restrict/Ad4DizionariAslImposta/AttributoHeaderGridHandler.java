//AttributoHeaderGridHandler @44-DA5A94CC
package restrict.Ad4DizionariAslImposta;
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

// //beforeShow @44-F81417CB

//BeforeShow Head @44-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @44-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @44-F81417CB

//beforeShowRow Head @44-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @44-92320BB7
        e.getGrid().getControl("Indietro").setValue("Indietro");
        e.getGrid().getLink("Indietro").getParameter("MVPG").setValue(( "Ad4DizionariAslElenco" ));
//End Set values for static controls

//beforeShowRow Tail @44-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @44-F81417CB

//BeforeSelect Head @44-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @44-FCB6E20C
    }
//End BeforeSelect Tail

//AttributoHeaderHandler Tail @44-FCB6E20C
}
//End AttributoHeaderHandler Tail

