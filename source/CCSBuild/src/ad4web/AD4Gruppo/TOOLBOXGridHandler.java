//TOOLBOXGridHandler @97-37B5628E
package ad4web.AD4Gruppo;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TOOLBOXGridHandler implements GridListener {
//End TOOLBOXGridHandler

// //beforeShow @97-F81417CB

//BeforeShow Head @97-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action Custom Code @113-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
 e.getGrid().setVisible( false );
if (e.getPage().getRequest().getAttribute("TIPO_UTENTE").toString().equals("G")) {
	e.getGrid().setVisible( true );
}
//End Event BeforeShow Action Custom Code

//BeforeShow Tail @97-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @97-F81417CB

//beforeShowRow Head @97-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//beforeShowRow Tail @97-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @97-F81417CB

//BeforeSelect Head @97-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @97-FCB6E20C
    }
//End BeforeSelect Tail

//TOOLBOXHandler Tail @97-FCB6E20C
}
//End TOOLBOXHandler Tail

