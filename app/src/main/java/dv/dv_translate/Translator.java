package dv.dv_translate;

/************************************************
 ** Created by Дмитрий В. (DiffeR) on 18.11.2014.
 ************************************************/
import java.io.File;
import java.util.Locale;
import android.app.backup.BackupManager;
import android.content.res.Configuration;
import android.os.Environment;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class Translator implements IXposedHookZygoteInit,
        IXposedHookInitPackageResources /*,IXposedHookLoadPackage */{

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
        apk.ResizeTextEditPopup();

    }

    public SampleAPK GetAPKClass(XC_InitPackageResources.InitPackageResourcesParam resparam)  throws Throwable {
        SampleAPK apk;
        if (resparam.packageName.equals("com.android.settings")) {
            apk = new SettingsAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.browser")) {
            apk = new BrowserAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.calendar")) {
            apk = new CalendarAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.cloudappbackup")) {
            apk = new CloudAppBackupAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.contacts")) {
            apk = new ContactsAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.providers.downloads")) {
            apk = new DownloadProviderAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.camera")) {
            apk = new CameraAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.gallery")) {
            apk = new MiuiGalleryPAK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.miui.home")) {
            apk = new MiuiHomeAPK(resparam, MODULE_PATH);
        } else if (resparam.packageName.equals("com.android.mms")) {
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
        } else
            apk=new SampleAPK(resparam, MODULE_PATH);

        return apk;
    }

 /*   @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.equals("com.android.phone")){
            XModuleResources modRes=XModuleResources.createInstance(MODULE_PATH, null);
            try {
                XposedHelpers.findAndHookMethod(
                final Class<?> AndroidPhoneClass = XposedHelpers.findClass("com.android.phone.",loadPackageParam.classLoader);

                XposedBridge.hookAllConstructors(AndroidPhoneClass, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {

                    }
                });

                XposedBridge.log("!!!Handle Load package com.android.phone");

            } catch (XposedHelpers.ClassNotFoundError e) {
                XposedBridge.log("Can't hooking class AndroidPhoneClass");
            }
        }
    }*/
}
