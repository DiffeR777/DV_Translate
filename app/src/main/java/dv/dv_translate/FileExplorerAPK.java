package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 25.11.2014.
 */
public class FileExplorerAPK extends SampleAPK {
    protected FileExplorerAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("delete_files_confirm", R.plurals.delete_files_confirm);
            this.TryReplacePlural("delete_folders_confirm", R.plurals.delete_folders_confirm);
            this.TryReplacePlural("directory_item_count", R.plurals.directory_item_count);
            this.TryReplacePlural("numSelectedFile", R.plurals.numSelectedFile);
            //The next two Crash application :(
            //this.TryReplacePlural("ui_bar_txt_download_progress",R.plurals.ui_bar_txt_download_progress);
            //this.TryReplacePlural("ui_bar_txt_upload_progress",R.plurals.ui_bar_txt_upload_progress);

            LogPluralsDone();
        }
    }
}
