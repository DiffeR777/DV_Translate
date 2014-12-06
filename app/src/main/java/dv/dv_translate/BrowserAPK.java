package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 21.11.2014.
 */
public class BrowserAPK extends SampleAPK{

    protected BrowserAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("v5_edit_mode_title", R.plurals.v5_edit_mode_title);
            this.TryReplacePlural("webview_matches_found", R.plurals.webview_matches_found);
            this.TryReplacePlural("webview_last_num_days", R.plurals.webview_last_num_days);

            LogPluralsDone();
        }
    }

}
