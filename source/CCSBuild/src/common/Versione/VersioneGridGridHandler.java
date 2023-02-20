//VersioneGridGridHandler @3-A9652DDA
package common.Versione;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class VersioneGridGridHandler implements GridListener {
//End VersioneGridGridHandler

// //beforeShow @3-F81417CB

//BeforeShow Head @3-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//BeforeShow Tail @3-FCB6E20C
    }
//End BeforeShow Tail

// //beforeShowRow @3-F81417CB

//beforeShowRow Head @3-BDFD38FC
    public void beforeShowRow(Event e) {
//End beforeShowRow Head

//Set values for static controls @3-566DDB3F
        e.getGrid().getControl("VERSIONE").setValue("V4.5.19");
//End Set values for static controls

//beforeShowRow Tail @3-FCB6E20C
    }
//End beforeShowRow Tail

// //beforeSelect @3-F81417CB

//BeforeSelect Head @3-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @3-FCB6E20C
    }
//End BeforeSelect Tail

//VersioneGridHandler Tail @3-FCB6E20C
}
//End VersioneGridHandler Tail

