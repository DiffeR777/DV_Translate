package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class YellowPageAPK extends SampleAPK {
    protected YellowPageAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("more_coupons_count", R.plurals.more_coupons_count);
            this.TryReplacePlural("review_count", R.plurals.review_count);
            LogPluralsDone();
        }
    }
}
