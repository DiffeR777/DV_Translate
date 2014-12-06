package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class WeatherAPK extends SampleAPK {
    protected WeatherAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("activity_main_update_time_in_one_hour", R.plurals.activity_main_update_time_in_one_hour);
            this.TryReplacePlural("activity_main_update_time_in_one_day", R.plurals.activity_main_update_time_in_one_day);
            this.TryReplacePlural("activity_main_update_time_one_day_ago", R.plurals.activity_main_update_time_one_day_ago);
            LogPluralsDone();
        }
    }
}
