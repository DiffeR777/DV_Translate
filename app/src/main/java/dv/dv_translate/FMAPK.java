package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 25.11.2014.
 */
public class FMAPK extends SampleAPK {
    protected FMAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("toast_channel_searched", R.plurals.toast_channel_searched);
            LogPluralsDone();
        }
    }
}
