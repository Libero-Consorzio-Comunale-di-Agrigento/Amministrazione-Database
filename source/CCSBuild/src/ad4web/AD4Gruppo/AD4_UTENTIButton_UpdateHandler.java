//AD4_UTENTIButton_UpdateHandler Head @25-E2C83F0C
package ad4web.AD4Gruppo;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_UTENTIButton_UpdateHandler implements ButtonListener {
//End AD4_UTENTIButton_UpdateHandler Head

//onClick Head @25-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//onClick Tail @25-FCB6E20C
}
//End onClick Tail

//beforeShow Head @25-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//Event BeforeShow Action Custom Code @111-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
e.getButton().setVisible( false );
if (e.getPage().getRequest().getAttribute("TIPO_UTENTE").toString().equals("G")) {
	e.getButton().setVisible( true );
}
//End Event BeforeShow Action Custom Code

//AD4_UTENTIButton_UpdateHandler Tail @25-F5FC18C5
    }
}
//End AD4_UTENTIButton_UpdateHandler Tail

