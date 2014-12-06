package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 27.11.2014.
 */
public class PrintSpoolerAPK extends SampleAPK {
    protected PrintSpoolerAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("print_search_result_count_utterance", R.plurals.print_search_result_count_utterance);
            this.TryReplacePlural("composite_notification_title_template", R.plurals.composite_notification_title_template);
            LogPluralsDone();
        }
    }
}
