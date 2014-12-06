package dv.dv_translate;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_close:
                finish();
                return true;
            /*case R.id.action_clear:

            File appFolder = new java.io.File(Environment.getExternalStorageDirectory().getPath()
                    + "/dv_translate/");

            if (appFolder.exists()){
                File[] listOfFolders = appFolder.listFiles();
                ActivityManager act = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);

                if (listOfFolders!=null)
                for(File subfolder :listOfFolders) {
                    try {
                        if (subfolder.isDirectory() && (!subfolder.getName().equals("System"))) {
                            int pid = android.os.Process.getUidForName(subfolder.getName());
                            act.killBackgroundProcesses(subfolder.getName());
                            android.os.Process.killProcess(pid);
                        }
                    }catch (Exception e){
                        System.out.println(e.getMessage());}
                }
            }*/
            case R.id.action_softreboot:
                try {
                    //Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "reboot" });
                    Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "setprop ctl.restart surfaceflinger" });
                    proc.waitFor();
                    proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "setprop ctl.restart zygote"});
                    proc.waitFor();
//                    PowerManager pwrMgr = (PowerManager)this.getApplicationContext().getSystemService(Context.POWER_SERVICE);
//                    pwrMgr.reboot(null);

                   // Process proc = Runtime.getRuntime().exec("sh");
                    //Process rtm=Runtime.getRuntime().exec("su");
                    //DataOutputStream stdin = new DataOutputStream(rtm.getOutputStream());
                    //HARD Reboot
                    //stdin.writeBytes("reboot\n");
                    //Soft Reboot
                    //Runtime.getRuntime().exec("setprop ctl.restart surfaceflinger\nsetprop ctl.restart zygote\n");
                    //stdin.writeBytes("setprop ctl.restart surfaceflinger\nsetprop ctl.restart zygote\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
