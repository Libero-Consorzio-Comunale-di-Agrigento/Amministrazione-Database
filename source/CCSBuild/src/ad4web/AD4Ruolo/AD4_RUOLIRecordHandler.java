//AD4_RUOLIHandler Head @23-2090FC2E
package ad4web.AD4Ruolo;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4_RUOLIRecordHandler implements RecordListener {
//End AD4_RUOLIHandler Head

//BeforeShow Head @23-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values @23-CD28536F
        if (! e.getRecord().isEditMode()) {
            e.getRecord().getControl("STATO").setDefaultValue("U");
            e.getRecord().getControl("GRUPPO_LAVORO").setDefaultValue("N");
            e.getRecord().getControl("GRUPPO_SO").setDefaultValue("N");
        }
//End Set values

//BeforeShow Tail @23-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @23-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @23-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @23-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @23-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @23-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @23-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @23-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @23-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @23-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @23-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @23-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @23-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @23-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @23-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @23-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @23-FCB6E20C
    }
//End AfterDelete Tail

//AD4_RUOLIHandler Tail @23-FCB6E20C
}
//End AD4_RUOLIHandler Tail

