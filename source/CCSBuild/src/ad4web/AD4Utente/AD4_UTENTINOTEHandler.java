//AD4_UTENTINOTEHandler Head @22-5A0A7726
package ad4web.AD4Utente;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_UTENTINOTEHandler implements ValidationListener {
//End AD4_UTENTINOTEHandler Head

//OnValidate Head @22-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//Event OnValidate Action Validate Maximum Length @55-C09E1867
        {
            VerifiableControl cntrl = (VerifiableControl)e.getSource();
            if (cntrl != null) {
                String value = cntrl.toString();
                if ( ! com.codecharge.util.StringUtils.isEmpty(value) ) {
                    if ( value.length() > 2000) cntrl.addError("Le note devono essere al più di 2000 caratteri");
                }
            }
        }
//End Event OnValidate Action Validate Maximum Length

//OnValidate Tail @22-FCB6E20C
    }
//End OnValidate Tail

//BeforeShow Head @22-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @22-FCB6E20C
    }
//End BeforeShow Tail

//AD4_UTENTINOTEHandler Tail @22-FCB6E20C
}
//End AD4_UTENTINOTEHandler Tail

