//AD4Gruppi_VSearchHandler Head @6-CBA710A3
package ad4web.AD4GruppiRicerca;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4Gruppi_VSearchRecordHandler implements RecordListener {
//End AD4Gruppi_VSearchHandler Head

//BeforeShow Head @6-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values @6-5B532056
        e.getRecord().getLink("Nuovo").getParameter("SE_NUOVO").setValue(( "Y" ));
        e.getRecord().getLink("Nuovo").getParameter("MVVC").setValue(( "AD4GRUPA" ));
//End Set values

//BeforeShow Tail @6-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @6-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @6-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @6-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @6-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @6-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @6-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @6-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @6-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @6-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @6-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @6-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @6-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @6-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @6-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @6-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @6-FCB6E20C
    }
//End AfterDelete Tail

//AD4Gruppi_VSearchHandler Tail @6-FCB6E20C
}
//End AD4Gruppi_VSearchHandler Tail
