package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 29.01.2015.
 */
public class ThemeManagerAPK extends SampleAPK{
    protected ThemeManagerAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("theme_update_local_resource_notifaction", R.plurals.theme_update_local_resource_notifaction);
            this.TryReplacePlural("theme_batch_has_update_text", R.plurals.theme_batch_has_update_text);
            LogPluralsDone();
        }
    }
}
