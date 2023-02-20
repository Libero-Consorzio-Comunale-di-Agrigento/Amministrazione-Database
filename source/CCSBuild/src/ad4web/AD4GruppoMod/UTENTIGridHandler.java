//UTENTIGridHandler @80-A4897606
package ad4web.AD4GruppoMod;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UTENTIGridHandler implements GridListener {
//End UTENTIGridHandler

// //beforeShow @80-F81417CB

//BeforeShow Head @80-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action Save Control Value @104-22A99F86
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4GRUPPO", ((com.codecharge.components.Hidden) ((com.codecharge.components.Grid) (e.getPage().getChild( "UTENTI" ))).getChild( "GRUPPO" )).getValue() );
//End Event BeforeShow Action Save Control Value

//BeforeShow Tail @80-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @80-F81417CB

//beforeShowRow Head @80-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//beforeShowRow Tail @80-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @80-F81417CB

//BeforeSelect Head @80-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @80-FCB6E20C
    }
//End BeforeSelect Tail

//UTENTIHandler Tail @80-FCB6E20C
}
//End UTENTIHandler Tail

