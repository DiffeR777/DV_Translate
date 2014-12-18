package dv.dv_translate;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.TypedValue;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * Created by Дмитрий В. on 23.11.2014.
 * Мастер-класс для замены непереводимых ресурсов отдельно взятых APK
 */
public class SampleAPK {
        private String packagename;
        private XResources packageres;
        private String appFolder;
        private XModuleResources modRes;

        protected SampleAPK(XC_InitPackageResources.InitPackageResourcesParam resparam,String module_path){
            this.packagename = resparam.packageName;
            this.packageres = resparam.res;
            this.modRes = XModuleResources.createInstance(module_path, resparam.res);
            this.appFolder=Environment.getExternalStorageDirectory().getPath()
                    + "/dv_translate/"
                    + packagename;
        }

    public void TryReplacePlural(String pluralname,int resourceid){
        try {
            this.packageres.setReplacement(this.packagename, "plurals", pluralname, this.modRes.fwd(resourceid));
        } catch (Throwable localThrowable1) {
            XposedBridge.log("exception then try replace plural: " + pluralname);
        }
    }

    public void TryReplaceDrawable(String drawablename,int resourceid) {
        try {
           this.packageres.setReplacement(this.packagename, "drawable", drawablename, modRes.fwd(resourceid));
        } catch (Throwable localThrowable1) {
            XposedBridge.log("exception then try replace drawable: "+drawablename);
        }
    }

    public boolean PluralsDisabled(){
        if (new File( appFolder + "/pluralsdisable.xml").exists()){
            XposedBridge.log("Plurals replace disabled for " + packagename);
            return true;
        }
        else return false;
    }

    public void LogPluralsDone() {
        //XposedBridge.log("Plurals replaced for " + packagename);
    }

    public void ReplaceStrings() throws IOException, SAXException, ParserConfigurationException {
        File stringsFile = new File(appFolder + "/strings.xml");

        if (stringsFile.exists()) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentbuilder = dbf.newDocumentBuilder();
            Document document = documentbuilder.parse(stringsFile);

            document.getDocumentElement().normalize();

            NodeList a =  document.getElementsByTagName("string");

            for(int i = 0; i<a.getLength();i++)
            {
                Node b = a.item(i);

                try { packageres.setReplacement(
                        packagename,
                        "string",
                        b.getAttributes().item(0).getNodeValue(),
                        b.getTextContent());
                } catch (Throwable localThrowable1) {
                    XposedBridge.log("exception then try replace string: " + b.getAttributes().item(0).getNodeValue());
                }
            }
           // XposedBridge.log("Strings replaced for " + packagename);

            //documentbuilder.reset();
        }

    }


    public void ReplaceArrays() throws IOException, SAXException, ParserConfigurationException {
        File arrayFile = new File( appFolder + "/arrays.xml");

        if (arrayFile.exists()) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentbuilder = dbf.newDocumentBuilder();
            Document document = documentbuilder.parse(arrayFile);

            document.getDocumentElement().normalize();

            try {
                NodeList a = document.getElementsByTagName("string-array");

                for (int i = 0; i < a.getLength(); i++) {
                    if (a.item(i).hasChildNodes()) {


                        NodeList b = a.item(i).getChildNodes();
                        if (b.getLength() > 0) {
                            ArrayList<String> arrStrings = new ArrayList<String>();
                            for (int n = 0; n <b.getLength(); n++) {
                                if (b.item(n).getNodeType() == Node.ELEMENT_NODE) {
                                    arrStrings.add(b.item(n).getTextContent());
                                }
                            }
                            String[] newarr = arrStrings.toArray(new String[arrStrings.size()]);
                               /* for (int n = 0; n < newarr.length; n++) {
                                    XposedBridge.log("array " + a.item(i).getAttributes().item(0).getNodeValue() + " " + n + ": " + newarr[n]);
                                }*/

                            try {
                                packageres.setReplacement(
                                        packagename,
                                        "array",
                                        a.item(i).getAttributes().item(0).getNodeValue(),  //т.е. <string-array name=
                                        newarr);
                                //XposedBridge.log("array: " + a.item(i).getAttributes().item(0).getNodeValue() + "replaced");
                            } catch (Throwable localThrowable1) {
                                XposedBridge.log("exception then try replace array: " + a.item(i).getAttributes().item(0).getNodeValue());
                            }
                        }
                    }
                }
            } catch (Throwable localThrowable1) {
                XposedBridge.log("error in parsing arrays.xml");
            }

            //XposedBridge.log("Arrays replaced for " + packagename);

            //documentbuilder.reset();
        }

    }

    public void ReplacePlurals(){

    }

    public void ReplaceDrawables() throws IOException, SAXException, ParserConfigurationException {

        File drawableFile = new File( appFolder + "/drawables.xml");

        if (drawableFile.exists()) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentbuilder = dbf.newDocumentBuilder();
            Document document = documentbuilder.parse(drawableFile);

            document.getDocumentElement().normalize();

            NodeList a =  document.getElementsByTagName("drawable");

            for(int i = 0; i<a.getLength();i++)
            {
                Node b = a.item(i);
                Uri uri = Uri.parse(appFolder + "/" + b.getTextContent());
                final String drpath = uri.getPath();
                final String drname= b.getAttributes().getNamedItem("name").getNodeValue();

                final int drheigth,drwidth;

                Node drnode=(b.getAttributes().getNamedItem("width"));
                if (drnode!=null) {
                    drwidth= Integer.parseInt(drnode.getNodeValue());
                } else drwidth=0;

                drnode=(b.getAttributes().getNamedItem("heigth"));
                if (drnode!=null){
                    drheigth= Integer.parseInt(drnode.getNodeValue());
                }else drheigth=0;

                Drawable origindrawable =  packageres.getDrawable(packageres.getIdentifier(drname, "drawable",packagename));
                Rect origin_rect=new Rect();
                if (origindrawable!= null)  origin_rect = origindrawable.getBounds();
                final Rect new_rect=origin_rect;

                // Read your drawable from somewhere
                try {
                   // XposedBridge.log("try replace drawable from " + drpath);
                    packageres.setReplacement(packagename,"drawable",drname,new XResources.DrawableLoader() {
                        @Override
                        public Drawable newDrawable(XResources res, int id) throws Throwable {
                            Drawable drawable;
                            drawable = Drawable.createFromPath(drpath);
                            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                            if ((drwidth!=0)&&(drheigth!=0)){
                                drawable.setBounds(0,0,drwidth,drheigth);
                                drawable =  new BitmapDrawable(packageres,Bitmap.createScaledBitmap(bitmap, drwidth, drheigth, true));
                            }
                            else {
                             if (new_rect.width()>0&&new_rect.height()>0){
                                drawable.setBounds(new_rect);
                                drawable =  new BitmapDrawable(packageres,Bitmap.createScaledBitmap(bitmap, new_rect.width(), new_rect.height(), true));
                             }

                            }
                            return drawable;
                        }
                    });
                } catch (Throwable localThrowable1) {
                    XposedBridge.log("exception then try replace drawable: " + drname);
                    //XposedBridge.log(localThrowable1);
                }
            }
            //XposedBridge.log("Drawables replaced for " + packagename);

            //documentbuilder.reset();
        }

    }

    // Try to Resize Bad wrapped copy/paste popup menu

    //<TextView android:textAppearance="?android:textAppearanceMediumInverse"
    // android:textSize="@dimen/text_edit_action_popup_text_size"
    // android:textColor="@android:color/white"
    // android:gravity="center"
    // android:background="@drawable/text_select_button_bg"
    // android:layout_width="wrap_content"
    // android:layout_height="wrap_content"
    //xmlns:android="http://schemas.android.com/apk/res/android" />
    //  <dimen name="text_edit_action_popup_text_size">13.0sp</dimen>
    public void ResizeTextEditPopup(){
        packageres.hookLayout("android.miui", "layout", "text_edit_action_popup_text", new EditPopupHook());
    }

    public enum Dimension_ {
        px ("px"),
        dip("dip"),
        sp("sp"),
        pt("pt"),
        in("in"),
        mm("mm");

        private final String name;

        private Dimension_(String s) {
            name = s;
        }

        public boolean equalsStr(String str2){
            return (str2 != null) && name.equals(str2);
        }

        public String toString(){
            return name;
        }
    }

/*
    public void ReplaceDimensions() throws ParserConfigurationException, IOException, SAXException {

        */
/*public enum Dimension {
            //Pixels - Corresponds to actual pixels on the screen.
            px(TypedValue.COMPLEX_UNIT_PX),

            // dp или dip Density-independent Pixels - An abstract unit that is based on the physical density of the screen
            dip(TypedValue.COMPLEX_UNIT_DIP),

            //Scale-independent Pixels
            sp(TypedValue.COMPLEX_UNIT_SP),

            //Points - 1/72 of an inch based on the physical size of the screen.
            pt(TypedValue.COMPLEX_UNIT_PT),

            //Inches - Based on the physical size of the screen.
            in(TypedValue.COMPLEX_UNIT_IN),

            //Millimeters - Based on the physical size of the screen.
            mm(TypedValue.COMPLEX_UNIT_MM);

            private final int unitType;

            Dimension(int unitType) {
                this.unitType = unitType;
            }

            public XResources.DimensionReplacement valueOf(float value) {
                return new XResources.DimensionReplacement(value, unitType);
            };
          *//*




        File dimensFile = new File(appFolder + "/dimens.xml");

        if (dimensFile.exists()) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentbuilder = dbf.newDocumentBuilder();
            Document document = documentbuilder.parse(dimensFile);

            document.getDocumentElement().normalize();

            NodeList a = document.getElementsByTagName("dimen");

            for(int i = 0; i<a.getLength();i++)
            {
                Node b = a.item(i);


                mFontSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mFontSize,
                        packageres.getDisplayMetrics());

                try { packageres.setReplacement(
                        packagename,
                        "dimen",
                        b.getAttributes().item(0).getNodeValue(),
                        b.getTextContent());
                } catch (Throwable localThrowable1) {
                    XposedBridge.log("exception then try replace dimention: " + b.getAttributes().item(0).getNodeValue());
                }
            }
            XposedBridge.log("Dimentions replaced for " + packagename);
        }
    }
*/
}

class EditPopupHook extends XC_LayoutInflated {
    @Override
    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable
    {
                /*final TextView txtEditPopup = (TextView) liparam.view.findViewById(
                        liparam.res.getIdentifier("hintText", "id", PACKAGE_NAME)); */
        TextView txtEditPopup = (TextView) liparam.view;

        if (txtEditPopup != null) {
            txtEditPopup.setTextSize(TypedValue.COMPLEX_UNIT_SP,9);
            //XposedBridge.log("Text in Edit Popup set to 9.0sp size ("+txtEditPopup.getText()+")");
        }
    }
}