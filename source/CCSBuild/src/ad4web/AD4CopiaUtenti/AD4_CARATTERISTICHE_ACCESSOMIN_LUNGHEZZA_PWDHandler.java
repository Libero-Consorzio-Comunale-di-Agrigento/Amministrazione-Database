//AD4_CARATTERISTICHE_ACCESSOMIN_LUNGHEZZA_PWDHandler Head @210-93D3E38E
package ad4web.AD4CopiaUtenti;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_CARATTERISTICHE_ACCESSOMIN_LUNGHEZZA_PWDHandler implements ValidationListener {
//End AD4_CARATTERISTICHE_ACCESSOMIN_LUNGHEZZA_PWDHandler Head

//OnValidate Head @210-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//Event OnValidate Action Validate Minimum Value @211-1F6196EA
        {
            VerifiableControl cntrl = (VerifiableControl)e.getSource();
            if (cntrl != null) {
                Object value = cntrl.getValue();
                boolean invalidValue = false;
                if (value != null) {
                    if (value instanceof Long) {
                        if (((Long)value).doubleValue() < 0) cntrl.addError("Iil valore deve essere un numero positivo ( >= 0 ) e minore di 9!");
                    } else if (value instanceof Double) {
                        if (((Double)value).doubleValue() < 0) cntrl.addError("Iil valore deve essere un numero positivo ( >= 0 ) e minore di 9!");
                    } else if (value instanceof String) {
                        try {
                            if ((new Double((String) value)).doubleValue() < 0) cntrl.addError("Iil valore deve essere un numero positivo ( >= 0 ) e minore di 9!");
                        } catch ( NumberFormatException nfe ) {
                            invalidValue = true;
                        }
                    } else {
                        invalidValue = true;
                    }
                }
                if ( invalidValue ) {
                    if ( cntrl.hasErrorByType( ControlErrorTypes.getErrorType( ControlErrorTypes.FORMAT_ERROR )) ) {
                        cntrl.removeErrorByType( ControlErrorTypes.getErrorType( ControlErrorTypes.FORMAT_ERROR ) );
                    }
                    cntrl.addError("The value in field " + cntrl.getCaption() + " is not valid.");
                }
            }
        }
//End Event OnValidate Action Validate Minimum Value

//Event OnValidate Action Validate Maximum Value @212-C541BB00
        {
            VerifiableControl cntrl = (VerifiableControl)e.getSource();
            if (cntrl != null) {
                Object value = cntrl.getValue();
                boolean invalidValue = false;
                if (value != null) {
                    if (value instanceof Number) {
                        if (((Number)value).doubleValue() > 8) cntrl.addError("Iil valore deve essere un numero positivo ( >= 0 ) e minore di 9!");
                    } else if (value instanceof String) {
                        try {
                            if ((new Double((String) value)).doubleValue() > 8) cntrl.addError("Iil valore deve essere un numero positivo ( >= 0 ) e minore di 9!");
                        } catch ( NumberFormatException nfe ) {
                            invalidValue = true;
                        }
                    } else {
                        invalidValue = true;
                    }
                }
                if ( invalidValue ) {
                    if ( cntrl.hasErrorByType( ControlErrorTypes.getErrorType( ControlErrorTypes.FORMAT_ERROR )) ) {
                        cntrl.removeErrorByType( ControlErrorTypes.getErrorType( ControlErrorTypes.FORMAT_ERROR ) );
                    }
                    cntrl.addError( "The value in field " + cntrl.getCaption() + " is not valid." );
                }
            }
        }
//End Event OnValidate Action Validate Maximum Value

//OnValidate Tail @210-FCB6E20C
    }
//End OnValidate Tail

//BeforeShow Head @210-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @210-FCB6E20C
    }
//End BeforeShow Tail

//AD4_CARATTERISTICHE_ACCESSOMIN_LUNGHEZZA_PWDHandler Tail @210-FCB6E20C
}
//End AD4_CARATTERISTICHE_ACCESSOMIN_LUNGHEZZA_PWDHandler Tail
