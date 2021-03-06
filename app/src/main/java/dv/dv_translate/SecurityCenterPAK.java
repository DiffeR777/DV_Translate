package dv.dv_translate;

import android.widget.Button;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class SecurityCenterPAK extends SampleAPK {
    private XC_InitPackageResources.InitPackageResourcesParam resparam;

    protected SecurityCenterPAK(XC_InitPackageResources.InitPackageResourcesParam Resparam, String module_path) {
        super(Resparam, module_path);
        this.resparam=Resparam;
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

    @Override
    public void CustomModuleActions() {
        /*resparam.res.hookLayout("com.miui.securitycenter", "layout", "pm_activity_root_apply",
            new XC_LayoutInflated() {
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable
            {
                liparam.view.findViewById(
                        liparam.res.getIdentifier("accept", "id", "com.miui.securitycenter")).setEnabled(true);

            }});*/
    }
}
