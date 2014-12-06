package dv.dv_translate;

import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Created by Дмитрий on 22.11.2014.
 */
public class ContactsAPK extends SampleAPK{

    protected ContactsAPK(XC_InitPackageResources.InitPackageResourcesParam resparam, String module_path) {
        super(resparam, module_path);
    }

    @Override
    public void ReplacePlurals() {
        if (!this.PluralsDisabled()) {
            TryReplacePlural("listTotalPhoneContacts", R.plurals.listTotalPhoneContacts);
            TryReplacePlural("listTotalAllContacts", R.plurals.listTotalAllContacts);
            TryReplacePlural("listFoundAllContacts", R.plurals.listFoundAllContacts);
            TryReplacePlural("searchFoundContacts", R.plurals.searchFoundContacts);
            TryReplacePlural("merge_info", R.plurals.merge_info);
            TryReplacePlural("num_groups_in_account", R.plurals.num_groups_in_account);
            TryReplacePlural("num_contacts_in_group", R.plurals.num_contacts_in_group);
            TryReplacePlural("group_list_num_contacts_in_group", R.plurals.group_list_num_contacts_in_group);
            TryReplacePlural("group_list_num_numbers_in_group", R.plurals.group_list_num_numbers_in_group);
            TryReplacePlural("notification_voicemail_title", R.plurals.notification_voicemail_title);
            TryReplacePlural("number_of_items_selected", R.plurals.number_of_items_selected);
            TryReplacePlural("pick_favorites_list_title", R.plurals.pick_favorites_list_title);
            TryReplacePlural("pick_recent_list_title", R.plurals.pick_recent_list_title);
            TryReplacePlural("hint_search_contacts", R.plurals.hint_search_contacts);
            TryReplacePlural("hint_search_phone_numbers", R.plurals.hint_search_phone_numbers);
            TryReplacePlural("hint_search_emails", R.plurals.hint_search_emails);
            TryReplacePlural("pick_favorites_contacts_list_title", R.plurals.pick_favorites_contacts_list_title);
            TryReplacePlural("pick_recent_contacts_list_title", R.plurals.pick_recent_contacts_list_title);
            TryReplacePlural("group_add_success_toast", R.plurals.group_add_success_toast);
            TryReplacePlural("smart_group_member_count", R.plurals.smart_group_member_count);
            TryReplacePlural("device_import_done_toast", R.plurals.device_import_done_toast);
            TryReplacePlural("numSelectedSimContacts", R.plurals.numSelectedSimContacts);
            LogPluralsDone();
        }

    }

/*     @Override
     public void ReplaceDrawables(){
           // TryReplaceDrawable("dialer_buttons", R.drawable.dialer_buttons);
     }*/
}
