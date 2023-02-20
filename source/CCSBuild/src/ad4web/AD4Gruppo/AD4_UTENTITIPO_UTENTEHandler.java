//AD4_UTENTITIPO_UTENTEHandler Head @109-84DC865F
package ad4web.AD4Gruppo;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_UTENTITIPO_UTENTEHandler implements ValidationListener {
//End AD4_UTENTITIPO_UTENTEHandler Head

//OnValidate Head @109-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @109-FCB6E20C
    }
//End OnValidate Tail

//BeforeShow Head @109-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action Custom Code @110-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
 e.getPage().getRequest().setAttribute("TIPO_UTENTE", e.getControl().getValue());
//End Event BeforeShow Action Custom Code

//BeforeShow Tail @109-FCB6E20C
    }
//End BeforeShow Tail

//AD4_UTENTITIPO_UTENTEHandler Tail @109-FCB6E20C
}
//End AD4_UTENTITIPO_UTENTEHandler Tail

