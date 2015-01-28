package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class MiuiGalleryPAK extends SampleAPK{
    protected MiuiGalleryPAK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            this.TryReplacePlural("confirm_delete_has_cloud_image", R.plurals.confirm_delete_has_cloud_image);
            this.TryReplacePlural("confirm_delete_has_local_image", R.plurals.confirm_delete_has_local_image);
            this.TryReplacePlural("confirm_delete_has_cloud_video", R.plurals.confirm_delete_has_cloud_video);
            this.TryReplacePlural("confirm_delete_has_local_video", R.plurals.confirm_delete_has_local_video);
            this.TryReplacePlural("confirm_delete_has_cloud_local_image", R.plurals.confirm_delete_has_cloud_local_image);
            this.TryReplacePlural("confirm_delete_has_cloud_local_video", R.plurals.confirm_delete_has_cloud_local_video);
            this.TryReplacePlural("number_of_items_selected", R.plurals.number_of_items_selected);
            this.TryReplacePlural("number_of_pictures", R.plurals.number_of_pictures);
            this.TryReplacePlural("how_many_pics", R.plurals.how_many_pics);
            this.TryReplacePlural("how_many_videos", R.plurals.how_many_videos);
            this.TryReplacePlural("how_many_sub_folders", R.plurals.how_many_sub_folders);
            this.TryReplacePlural("message_no_delete_access_with_count", R.plurals.message_no_delete_access_with_count);
            this.TryReplacePlural("pick_with_limited_upper_bound_title", R.plurals.pick_with_limited_upper_bound_title);
            this.TryReplacePlural("number_of_images", R.plurals.number_of_images);
            this.TryReplacePlural("number_of_items", R.plurals.number_of_items);
            LogPluralsDone();
        }
    }
}
