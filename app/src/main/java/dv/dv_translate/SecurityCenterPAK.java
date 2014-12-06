package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class SecurityCenterPAK extends SampleAPK {
    protected SecurityCenterPAK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }
    @Override
    public  void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("power_center_battery_day", R.plurals.power_center_battery_day);
            this.TryReplacePlural("power_center_battery_hour", R.plurals.power_center_battery_hour);
            this.TryReplacePlural("power_center_battery_minute", R.plurals.power_center_battery_minute);
            this.TryReplacePlural("hints_auto_start_enable_title", R.plurals.hints_auto_start_enable_title);
            this.TryReplacePlural("hints_auto_start_disable_title", R.plurals.hints_auto_start_disable_title);
            this.TryReplacePlural("hints_apps_perm_count", R.plurals.hints_apps_perm_count);
            this.TryReplacePlural("hints_permission_apps_count", R.plurals.hints_permission_apps_count);
            this.TryReplacePlural("hints_get_root_enable_title", R.plurals.hints_get_root_enable_title);
            this.TryReplacePlural("hints_get_root_disable_title", R.plurals.hints_get_root_disable_title);
            this.TryReplacePlural("summary_app_update", R.plurals.summary_app_update);
            LogPluralsDone();
        }
    }
}
