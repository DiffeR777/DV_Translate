package dv.dv_translate;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 25.11.2014.
 */
public class InCallUIAPK extends SampleAPK {
    protected InCallUIAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("phone_in_ecm_notification_time", R.plurals.phone_in_ecm_notification_time);
            this.TryReplacePlural("alert_dialog_exit_ecm", R.plurals.alert_dialog_exit_ecm);
            this.TryReplacePlural("alert_dialog_not_avaialble_in_ecm", R.plurals.alert_dialog_not_avaialble_in_ecm);
            LogPluralsDone();
        }
    }
}
