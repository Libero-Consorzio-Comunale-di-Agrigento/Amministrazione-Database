//TitoloGridHandler @40-50B6E47B
package restrict.Ad4DizionariAslComuniElenco;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TitoloGridHandler implements GridListener {
//End TitoloGridHandler

// //beforeShow @40-F81417CB

//BeforeShow Head @40-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @40-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @40-F81417CB

//beforeShowRow Head @40-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @40-E8F103CE
        e.getGrid().getControl("Aggiungi").setValue("Nuovo");
        e.getGrid().getLink("Aggiungi").getParameter("MVPG").setValue(( "Ad4DizionariAslComuneImposta" ));
//End Set values for static controls

//beforeShowRow Tail @40-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @40-F81417CB

//BeforeSelect Head @40-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @40-FCB6E20C
    }
//End BeforeSelect Tail

//TitoloHandler Tail @40-FCB6E20C
}
//End TitoloHandler Tail

