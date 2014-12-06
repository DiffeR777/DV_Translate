package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 23.11.2014.
 */
public class MusicAPK extends SampleAPK {
    protected MusicAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        {
            this.TryReplacePlural("Ntracks_search_result", R.plurals.Ntracks_search_result);
            this.TryReplacePlural("Nexpired_remind", R.plurals.Nexpired_remind);
            this.TryReplacePlural("NTrackstoDownload", R.plurals.NTrackstoDownload);
            this.TryReplacePlural("Ntracks_count", R.plurals.Ntracks_count);
            this.TryReplacePlural("Ntracks_add_to_playlist", R.plurals.Ntracks_add_to_playlist);
            this.TryReplacePlural("Ntracks_remove_from_playlist", R.plurals.Ntracks_remove_from_playlist);
            this.TryReplacePlural("artist_list_fragment_item_count_format", R.plurals.artist_list_fragment_item_count_format);
            this.TryReplacePlural("send_songs_toast", R.plurals.send_songs_toast);
            this.TryReplacePlural("folder_songs_count", R.plurals.folder_songs_count);
            LogPluralsDone();
        }
    }
}

