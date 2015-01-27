package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 25.12.2014.
 */
public class AccountAPK extends SampleAPK{
    protected AccountAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("updated_contacts", R.plurals.updated_contacts);
            this.TryReplacePlural("dirty_contacts_count", R.plurals.dirty_contacts_count);
            this.TryReplacePlural("dirty_mms_count", R.plurals.dirty_mms_count);
            this.TryReplacePlural("dirty_note_count", R.plurals.dirty_note_count);

            LogPluralsDone();
        }
    }
}
