//AD4_UTENTE_ALIMENTAREButton_UpdateHandler Head @111-FDAF9036
package ad4web.AD4UnificaProfilo;
import java.util.*;
import com.codecharge.events.*;
import com.codecharge.components.*;
import com.codecharge.validation.*;
import com.codecharge.db.*;
import com.codecharge.util.*;
public class AD4_UTENTE_ALIMENTAREButton_UpdateHandler implements ButtonListener {
//End AD4_UTENTE_ALIMENTAREButton_UpdateHandler Head

//onClick Head @111-A9885EEC
    public void onClick(Event e) {
//End onClick Head

//Event OnClick Action Save Control Value @112-4CF27C45
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4UTENTE", ((com.codecharge.components.Label) ((com.codecharge.components.Record) (e.getPage().getChild( "AD4_UTENTE_ALIMENTARE" ))).getChild( "UTENTE_ALIMENTARE" )).getValue() );
//End Event OnClick Action Save Control Value

//onClick Tail @111-FCB6E20C
}
//End onClick Tail

//beforeShow Head @111-46046458
    public void beforeShow(Event e) {
//End beforeShow Head

//AD4_UTENTE_ALIMENTAREButton_UpdateHandler Tail @111-F5FC18C5
    }
}
//End AD4_UTENTE_ALIMENTAREButton_UpdateHandler Tail

