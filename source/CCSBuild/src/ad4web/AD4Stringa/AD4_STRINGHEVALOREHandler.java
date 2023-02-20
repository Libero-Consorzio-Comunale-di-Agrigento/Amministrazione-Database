//AD4_STRINGHEVALOREHandler Head @57-02BE67B5
package ad4web.AD4Stringa;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_STRINGHEVALOREHandler implements ValidationListener {
//End AD4_STRINGHEVALOREHandler Head

//OnValidate Head @57-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//Event OnValidate Action Validate Maximum Length @171-1B0D6048
        {
            VerifiableControl cntrl = (VerifiableControl)e.getSource();
            if (cntrl != null) {
                String value = cntrl.toString();
                if ( ! com.codecharge.util.StringUtils.isEmpty(value) ) {
                    if ( value.length() > 2000) cntrl.addError("Il valore deve essere al più di 2000 caratteri");
                }
            }
        }
//End Event OnValidate Action Validate Maximum Length

//OnValidate Tail @57-FCB6E20C
    }
//End OnValidate Tail

//BeforeShow Head @57-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @57-FCB6E20C
    }
//End BeforeShow Tail

//AD4_STRINGHEVALOREHandler Tail @57-FCB6E20C
}
//End AD4_STRINGHEVALOREHandler Tail

