package dv.dv_translate;


import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий В. on 20.11.2014.
 */
public class SettingsAPK extends SampleAPK{

    protected SettingsAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("show_dev_countdown", R.plurals.show_dev_countdown);
            this.TryReplacePlural("lockpassword_password_requires_letters", R.plurals.lockpassword_password_requires_letters);
            this.TryReplacePlural("lockpassword_password_requires_lowercase", R.plurals.lockpassword_password_requires_lowercase);
            this.TryReplacePlural("lockpassword_password_requires_uppercase", R.plurals.lockpassword_password_requires_uppercase);
            this.TryReplacePlural("lockpassword_password_requires_numeric", R.plurals.lockpassword_password_requires_numeric);
            this.TryReplacePlural("lockpassword_password_requires_symbols", R.plurals.lockpassword_password_requires_symbols);
            this.TryReplacePlural("lockpassword_password_requires_nonletter", R.plurals.lockpassword_password_requires_nonletter);
            this.TryReplacePlural("manage_notification_access_summary_nonzero", R.plurals.manage_notification_access_summary_nonzero);
            this.TryReplacePlural("wrong_pin_code", R.plurals.wrong_pin_code);
            this.TryReplacePlural("print_search_result_count_utterance", R.plurals.print_search_result_count_utterance);
            this.TryReplacePlural("show_pho_countdown", R.plurals.show_pho_countdown);
            this.TryReplacePlural("show_cit_countdown", R.plurals.show_cit_countdown);
            this.TryReplacePlural("show_rep_countdown", R.plurals.show_rep_countdown);
            LogPluralsDone();
        }
    }

}
