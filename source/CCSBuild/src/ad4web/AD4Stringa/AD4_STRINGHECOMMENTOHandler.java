//AD4_STRINGHECOMMENTOHandler Head @60-12DFEB54
package ad4web.AD4Stringa;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_STRINGHECOMMENTOHandler implements ValidationListener {
//End AD4_STRINGHECOMMENTOHandler Head

//OnValidate Head @60-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//Event OnValidate Action Validate Maximum Length @172-3A92C715
        {
            VerifiableControl cntrl = (VerifiableControl)e.getSource();
            if (cntrl != null) {
                String value = cntrl.toString();
                if ( ! com.codecharge.util.StringUtils.isEmpty(value) ) {
                    if ( value.length() > 2000) cntrl.addError("Il commento deve essere al più di 2000 caratteri");
                }
            }
        }
//End Event OnValidate Action Validate Maximum Length

//OnValidate Tail @60-FCB6E20C
    }
//End OnValidate Tail

//BeforeShow Head @60-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @60-FCB6E20C
    }
//End BeforeShow Tail

//AD4_STRINGHECOMMENTOHandler Tail @60-FCB6E20C
}
//End AD4_STRINGHECOMMENTOHandler Tail

