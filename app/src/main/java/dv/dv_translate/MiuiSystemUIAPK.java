package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class MiuiSystemUIAPK extends SampleAPK{

    protected MiuiSystemUIAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("status_bar_accessibility_recent_apps", R.plurals.status_bar_accessibility_recent_apps);
            LogPluralsDone();
        }
    }
}
