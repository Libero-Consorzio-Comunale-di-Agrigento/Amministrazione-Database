//ProvinceFiltroHandler Head @3-CAD7CF84
package restrict.Ad4DizionariProvinceElenco;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class ProvinceFiltroRecordHandler implements RecordListener {
//End ProvinceFiltroHandler Head

//BeforeShow Head @3-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values @3-CED9ED32
        if (! e.getRecord().isEditMode()) {
            e.getRecord().getControl("s_STATI_ESTERI").setDefaultValue(0);
        }
//End Set values

//BeforeShow Tail @3-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @3-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @3-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @3-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @3-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @3-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @3-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @3-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @3-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @3-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @3-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @3-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @3-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @3-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @3-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @3-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @3-FCB6E20C
    }
//End AfterDelete Tail

//ProvinceFiltroHandler Tail @3-FCB6E20C
}
//End ProvinceFiltroHandler Tail

