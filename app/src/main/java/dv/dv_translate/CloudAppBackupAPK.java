package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 21.11.2014.
 */
public class CloudAppBackupAPK extends SampleAPK{


    protected CloudAppBackupAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("edit_mode_title", R.plurals.edit_mode_title);
            LogPluralsDone();
        }
    }

}
