package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 24.11.2014.
 */
public class ClockAPK extends SampleAPK {
    protected ClockAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("timer_item_duration_display_seconds", R.plurals.timer_item_duration_display_seconds);
            this.TryReplacePlural("timer_item_duration_display_minutes", R.plurals.timer_item_duration_display_minutes);
            this.TryReplacePlural("timer_message_with_minutes", R.plurals.timer_message_with_minutes);
            this.TryReplacePlural("timer_message_with_seconds", R.plurals.timer_message_with_seconds);
            this.TryReplacePlural("snooze_message", R.plurals.snooze_message);
            LogPluralsDone();
        }
    }

    @Override
    public void CustomModuleActions(){
        this.TryReplaceDimention("tab_text_size", R.dimen.tab_text_size);
    }
}
