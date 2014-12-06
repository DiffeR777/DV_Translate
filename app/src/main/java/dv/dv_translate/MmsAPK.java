package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class MmsAPK extends SampleAPK {
    protected MmsAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("confirm_delete_conversation", R.plurals.confirm_delete_conversation);
            this.TryReplacePlural("slide_duration", R.plurals.slide_duration);
            this.TryReplacePlural("slide_audio_duration", R.plurals.slide_audio_duration);
            this.TryReplacePlural("search_results_title", R.plurals.search_results_title);
            this.TryReplacePlural("recipient_count", R.plurals.recipient_count);
            this.TryReplacePlural("message_count", R.plurals.message_count);
            LogPluralsDone();
        }
    }

}
