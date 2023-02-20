//AD4_RICHIESTE_ABILITAZIONERecordArchiviaHandler Head @75-41ED4570
package amvadm.AdmRichiesta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_RICHIESTE_ABILITAZIONERecordArchiviaHandler implements ButtonListener {
//End AD4_RICHIESTE_ABILITAZIONERecordArchiviaHandler Head

//onClick Head @75-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//onClick Tail @75-FCB6E20C
}
//End onClick Tail

//beforeShow Head @75-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//Event BeforeShow Action Custom Code @77-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
if (e.getPage().getRequest().getAttribute("stato").toString().equals("A") || e.getPage().getRequest().getAttribute("stato").toString().equals("R")) {
	e.getButton().setVisible( false );
}

//End Event BeforeShow Action Custom Code

//AD4_RICHIESTE_ABILITAZIONERecordArchiviaHandler Tail @75-F5FC18C5
    }
}
//End AD4_RICHIESTE_ABILITAZIONERecordArchiviaHandler Tail

