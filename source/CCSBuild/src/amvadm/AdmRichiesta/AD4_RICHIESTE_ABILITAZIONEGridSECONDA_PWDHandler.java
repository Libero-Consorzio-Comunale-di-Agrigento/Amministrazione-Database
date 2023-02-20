//AD4_RICHIESTE_ABILITAZIONEGridSECONDA_PWDHandler Head @69-A8541C04
package amvadm.AdmRichiesta;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_RICHIESTE_ABILITAZIONEGridSECONDA_PWDHandler implements ControlListener {
//End AD4_RICHIESTE_ABILITAZIONEGridSECONDA_PWDHandler Head

//BeforeShow Head @69-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Event BeforeShow Action Custom Code @70-44795B7A
/* -------------------------- *
 *  write your own code here  *
 * -------------------------- */
 if ((e.getControl().getValue() != null)) {
 	if ((e.getControl().getValue().toString().length() >7) && (e.getControl().getValue().toString().indexOf("PWDUTENTE=") == 0)) {
		String tmp = e.getControl().getValue().toString();
	 	String mezzapassword = it.finmatica.jfc.authentication.Cryptable.decryptPasswd(tmp.substring(tmp.length()-4,tmp.length()));
	 	e.getControl().setValue(mezzapassword);
	} //else e.getControl().setValue("Password di registrazione modificata");
 }
//End Event BeforeShow Action Custom Code

//BeforeShow Tail @69-FCB6E20C
    }
//End BeforeShow Tail

//AD4_RICHIESTE_ABILITAZIONEGridSECONDA_PWDHandler Tail @69-FCB6E20C
}
//End AD4_RICHIESTE_ABILITAZIONEGridSECONDA_PWDHandler Tail

