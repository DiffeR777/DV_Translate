package dv.dv_translate;

/************************************************
 ** Created by Дмитрий В. (DiffeR) on 18.11.2014.
 ************************************************/
import java.io.File;
import java.util.Locale;

import android.app.Activity;
import android.app.backup.BackupManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Translator implements IXposedHookZygoteInit,
        IXposedHookInitPackageResources ,IXposedHookLoadPackage {

    private static String MODULE_PATH = null;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;

        XposedBridge.log("InitZygote of module " + startupParam.modulePath);

        SystemReplacer systemreplacer = new SystemReplacer(MODULE_PATH);
        systemreplacer.ReplaceStrings();
        XposedBridge.log("Strings replaced for framework's");
        systemreplacer.ReplaceArrays();
        XposedBridge.log("Arrays replaced for framework's");
        systemreplacer.ReplacePlurals();
        XposedBridge.log("Plurals replaced for framework's");
    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam)
            throws Throwable {

        //change system locale
        try {
            Class<?> activityManagerNative = Class.forName("android.app.ActivityManagerNative");
            Object am=activityManagerNative.getMethod("getDefault").invoke(activityManagerNative);
            //
            //  IActivityManager am = ActivityManagerNative.getDefault();
            //
            Configuration config=(Configuration)am.getClass().getMethod("getConfiguration").invoke(am);
            //
            //  Configuration config;
            //  config = am.getConfiguration();
            //
            String Activelocale=config.locale.toString();
            if (!Activelocale.equals("ru") && !Activelocale.equals("ru_RU")) {
                config.locale=new Locale("ru","RU");
                //  exmpls:
                //    config.locale = Locale.CHINA;
                //    Locale.setDefault(RUSlocale);
                //
                config.getClass().getDeclaredField("userSetLocale").setBoolean(config, true);
                // Class[] args = new Class[1];
                // args[0] =  Configuration.class;
                am.getClass().getMethod("updateConfiguration", new Class[] { Configuration.class }).invoke(am, config);
                //
                //      am.updateConfiguration(config);
                //
                BackupManager.dataChanged("com.android.providers.settings");
                XposedBridge.log("system locale changed to Russian");
            }
        }catch (Throwable thr1) { //XposedBridge.log("Can't change system locale: "+thr1);
        }

        // if exists module dir
        if (new File(Environment.getExternalStorageDirectory()
                .getPath()
                + "/dv_translate/"
                + resparam.packageName)
                .exists())
            XposedBridge.log("DV_Translate found app " + resparam.packageName);
        else
            return;

        SampleAPK apk = GetAPKClass(resparam);

        apk.ReplacePlurals();
        apk.ReplaceStrings();
        apk.ReplaceArrays();
        apk.ReplaceDrawables();
        //apk.ReplaceDimensions();
        apk.ResizeTextEditPopup();
        apk.CustomModuleActions();

    }

    public SampleAPK GetAPKClass(XC_InitPackageResources.InitPackageResourcesParam resparam)  throws Throwable {
        SampleAPK apk;
        if (resparam.packageName.equals("com.android.settings")) {
            apk = new SettingsAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.browser")) {
            apk = new BrowserAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.calendar")) {
            apk = new CalendarAPK(resparam, MODULE_PATH);
        } else /*if (resparam.packageName.equals("com.miui.cloudappbackup")) {
            apk = new CloudAppBackupAPK(resparam, MODULE_PATH);
        } else */if (resparam.packageName.equals("com.android.contacts")) {
            apk = new ContactsAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.providers.downloads")) {
            apk = new DownloadProviderAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.camera")) {
            apk = new CameraAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.gallery")) {
            apk = new MiuiGalleryPAK(resparam, MODULE_PATH);
        } else /*if (resparam.packageName.equals("com.miui.home")) {
            apk = new MiuiHomeAPK(resparam, MODULE_PATH);
        } else*/ if (resparam.packageName.equals("com.android.mms")) {
            apk = new MmsAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.systemui")) {
            apk = new MiuiSystemUIAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.player")) {
            apk = new MusicAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.securitycenter")) {
            apk = new SecurityCenterPAK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.phone")) {
            apk = new TeleServiceAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.weather2")) {
            apk = new WeatherAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.yellowpage")) {
            apk = new YellowPageAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.deskclock")) {
            apk = new ClockAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.cloudservice")) {
            apk = new CloudServicePAK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.fileexplorer")) {
            apk = new FileExplorerAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.fmradio")) {
            apk = new FMAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.incallui")) {
            apk = new InCallUIAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.notes")) {
            apk = new NotesAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.printspooler")) {
            apk = new PrintSpoolerAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.xiaomi.account")) {
            apk = new AccountAPK(resparam, MODULE_PATH);
        } else
            apk=new SampleAPK(resparam, MODULE_PATH);

        return apk;
    }


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        //Remove 3 annoyed messages from set root dialog
        if(loadPackageParam.packageName.equals("com.miui.securitycenter")) {
                  try{
                      XposedHelpers.findAndHookMethod("com.miui.permcenter.root.RootApplyActivity", loadPackageParam.classLoader,"onCreate",Bundle.class,
                               new XC_MethodHook() {
                                   @Override
                                   protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                       Activity ViewForm = (Activity) param.thisObject;
                                       Button ButtonOk=(Button)ViewForm.findViewById(Integer.valueOf(0x7f0a00d6));
                                       if (ButtonOk != null) {
                                           try {
                                               XposedBridge.log("DV_Translate hack Root Apply Button");
                                               for (int i = 0; i <= 3; i++) {
                                                   ButtonOk.setEnabled(true);
                                                   ButtonOk.callOnClick();
                                               }
                                           } catch (Throwable t) {
                                               XposedBridge.log("DV_Translate Error in find accept Button");
                                           }
                                       }
                                   }
                                   });
                  } catch (XposedHelpers.ClassNotFoundError e) {
                      XposedBridge.log("DV_Translate change RootApplyActivity error");
                  }
        }

    }
}
