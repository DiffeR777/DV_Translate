package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 26.11.2014.
 */
public class NotesAPK extends SampleAPK {
    protected NotesAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("note_count_in_folder", R.plurals.note_count_in_folder);
            this.TryReplacePlural("search_results_title", R.plurals.search_results_title);
            LogPluralsDone();
        }
    }
}
