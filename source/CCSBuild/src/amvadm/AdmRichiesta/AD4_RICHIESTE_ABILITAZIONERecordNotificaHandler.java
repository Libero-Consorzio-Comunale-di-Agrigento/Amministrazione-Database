//AD4_RICHIESTE_ABILITAZIONERecordNotificaHandler Head @61-7B89C278
package amvadm.AdmRichiesta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_RICHIESTE_ABILITAZIONERecordNotificaHandler implements ButtonListener {
//End AD4_RICHIESTE_ABILITAZIONERecordNotificaHandler Head

//onClick Head @61-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//onClick Tail @61-FCB6E20C
}
//End onClick Tail

//beforeShow Head @61-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//Event BeforeShow Action Custom Code @62-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
 e.getButton().setVisible(false);
 if (e.getPage().getRequest().getAttribute("stato").toString().equals("A") || e.getPage().getRequest().getAttribute("stato").toString().equals("R") || e.getPage().getRequest().getAttribute("stato").toString().equals("C")) {
//   if (e.getPage().getRequest().getAttribute("notificata").toString().equals("N")||e.getPage().getRequest().getAttribute("notificata").toString().equals("F")) {
		e.getButton().setVisible(true);
//   } 
}

//End Event BeforeShow Action Custom Code

//AD4_RICHIESTE_ABILITAZIONERecordNotificaHandler Tail @61-F5FC18C5
    }
}
//End AD4_RICHIESTE_ABILITAZIONERecordNotificaHandler Tail

