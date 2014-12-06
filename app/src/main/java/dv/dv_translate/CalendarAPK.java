package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 21.11.2014.
 */
public class CalendarAPK extends SampleAPK{

    protected CalendarAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("Nminutes", R.plurals.Nminutes);
            this.TryReplacePlural("Nmins", R.plurals.Nmins);
            this.TryReplacePlural("Nhours", R.plurals.Nhours);
            this.TryReplacePlural("Ndays", R.plurals.Ndays);
            this.TryReplacePlural("weekN", R.plurals.weekN);
            this.TryReplacePlural("month_more_events", R.plurals.month_more_events);
            this.TryReplacePlural("Nevents", R.plurals.Nevents);
            this.TryReplacePlural("N_remaining_events", R.plurals.N_remaining_events);

            LogPluralsDone();
        }
    }

}
