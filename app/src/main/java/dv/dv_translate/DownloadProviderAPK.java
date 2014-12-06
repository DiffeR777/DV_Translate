package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class DownloadProviderAPK extends SampleAPK {

    protected DownloadProviderAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("notif_summary_active", R.plurals.notif_summary_active);
            this.TryReplacePlural("notif_summary_waiting", R.plurals.notif_summary_waiting);
            LogPluralsDone();
        }
    }

}
