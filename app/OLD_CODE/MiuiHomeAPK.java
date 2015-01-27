package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class MiuiHomeAPK extends SampleAPK {
    protected MiuiHomeAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("editing_mode_title", R.plurals.editing_mode_title);
            this.TryReplacePlural("uninstall_dialog_title_format", R.plurals.uninstall_dialog_title_format);
            this.TryReplacePlural("uninstall_result_succeeded", R.plurals.uninstall_result_succeeded);
            this.TryReplacePlural("uninstall_result_failed", R.plurals.uninstall_result_failed);
            LogPluralsDone();
        }
    }
}
