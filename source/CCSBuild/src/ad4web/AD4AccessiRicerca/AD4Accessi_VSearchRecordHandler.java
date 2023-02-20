//AD4Accessi_VSearchHandler Head @358-81B62CBC
package ad4web.AD4AccessiRicerca;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4Accessi_VSearchRecordHandler implements RecordListener {
//End AD4Accessi_VSearchHandler Head

//BeforeShow Head @358-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values @358-D5E29AA2
        if (! e.getRecord().isEditMode()) {
            e.getRecord().getControl("s_TIPO").setDefaultValue("ERROR");
            e.getRecord().getControl("s_RICERCA").setDefaultValue("N");
        }
//End Set values

//BeforeShow Tail @358-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @358-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @358-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @358-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @358-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @358-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @358-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @358-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @358-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @358-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @358-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @358-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @358-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @358-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @358-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @358-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @358-FCB6E20C
    }
//End AfterDelete Tail

//AD4Accessi_VSearchHandler Tail @358-FCB6E20C
}
//End AD4Accessi_VSearchHandler Tail

