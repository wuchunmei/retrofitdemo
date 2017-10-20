
package demo.wuchunmei.com.retrofitdemo.main.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;



/**
 * 和系统Context相关的一些处理，如提示，屏幕信息等
 *
 * @author zhanggx
 */
public class ContextUtils {
//
//    public static void showToast(final Context context, final String text) {
//
//        if (TextUtils.isEmpty(text)) {
//            return;
//        }
//        if (Looper.getMainLooper() == Looper.myLooper()) {
//            MyToast.showToast(context, text);
//        } else {
//            // 异步线程不处理
//            /*
//             * Looper.prepare(); MyToast.showToast(context, text);
//             * Looper.loop();
//             */
//        }
//    }
//
//    public static void showToast(final Context context, final int resid) {
//        if (Looper.getMainLooper() == Looper.myLooper()) {
//            MyToast.showToast(context, resid);
//        } else {
//            // 异步线程不处理
//            // Looper.prepare();
//            // MyToast.showToast(context, resid);
//            // Looper.loop();
//        }
//    }

//    public static void showToast(final Context context, final int resid,
//            final Object... formatArgs) {
//
//        if (Looper.getMainLooper() == Looper.myLooper()) {
//            MyToast.showToast(context, resid, formatArgs);
//        } else {
//            // 异步线程不处理
//            // Looper.prepare();
//            // MyToast.showToast(context, resid, formatArgs);
//            // Looper.loop();
//        }
//    }

//    public static void showToast(final Context context, final String text, int duration) {
//
//        if (TextUtils.isEmpty(text)) {
//            return;
//        }
//        if (Looper.getMainLooper() == Looper.myLooper()) {
//            MyToast.showToast(context, text, duration);
//        } else {
//            // 异步线程不处理
//            /*
//             * Looper.prepare(); MyToast.showToast(context, text);
//             * Looper.loop();
//             */
//        }
//    }

//    public static void showToast(final Context context, final String text, int duration, int gravity) {
//
//        if (TextUtils.isEmpty(text)) {
//            return;
//        }
//        if (Looper.getMainLooper() == Looper.myLooper()) {
//            MyToast.showToast(context, text, duration, gravity);
//        } else {
//            // 异步线程不处理
//            /*
//             * Looper.prepare(); MyToast.showToast(context, text);
//             * Looper.loop();
//             */
//        }
//    }
//    public static void showToast(final Context context, int resId, int duration, int gravity) {
//
//        if (Looper.getMainLooper() == Looper.myLooper()) {
//            MyToast.showToast(context, resId, duration, gravity);
//        } else {
//            // 异步线程不处理
//            /*
//             * Looper.prepare(); MyToast.showToast(context, text);
//             * Looper.loop();
//             */
//        }
//    }

    public static Dialog showRemindDialog(Context context, int titleresid,
            int msgresid, int msgCancelId) {
        Dialog dialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(titleresid))
                .setMessage(context.getString(msgresid))
                .setNegativeButton(msgCancelId,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                // TODO Auto-generated method stub

                            }
                        }).create();
        dialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                    KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getRepeatCount() == 0) {
                    dialog.dismiss();
                }
                return false;
            }

        });
        try {
            dialog.show();
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
        return dialog;
    }

    public static Dialog showConfirmDialog(Context context, int titleresid,
            int msgresid, int msgConfirmId, int msgCancelId,
            DialogInterface.OnClickListener listener) {
        return showConfirmDialog(context, context.getString(titleresid),
                context.getString(msgresid), msgConfirmId, msgCancelId,
                listener);
    }

    public static Dialog showConfirmDialog(Context context, String title,
            String msg, int msgConfirmId, int msgCancelId,
            DialogInterface.OnClickListener listener) {
        Dialog dialog = new AlertDialog.Builder(context).setTitle(title)
                .setMessage(msg).setNegativeButton(msgCancelId, listener)
                .setPositiveButton(msgConfirmId, listener).create();
        /*
         * dialog.setOnKeyListener(new
         * android.content.DialogInterface.OnKeyListener(){
         * @Override public boolean onKey(DialogInterface dialog, int keyCode,
         * KeyEvent event) { if (keyCode == KeyEvent.KEYCODE_BACK &&
         * event.getRepeatCount() == 0) { dialog.dismiss(); } return false; }
         * });
         */
        dialog.setCancelable(true);
        try {
            dialog.show();
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
        return dialog;
    }

    public static void killAppliction(Context context, String name) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        am.restartPackage(name);
    }

    public static void setBrightness(Activity activity, int screenBrightness) {
        int brightness = screenBrightness;
        if (brightness < 10) {
            brightness = 30;
        }
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        // Log.v("setBrightness", lp.screenBrightness+"");
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 判断当前是否有网络,包括wifi以及移动网络
     *
     * @param context
     * @return 如果有网络,则返回true.否则返回false
     */
    public static boolean checkNetworkConnection(Context context) {

        if (context == null)
            return false;

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity.getActiveNetworkInfo() != null) {
            return true;
        }
        NetworkInfo mWifi = connectivity
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi != null && mWifi.isAvailable()
                && mWifi.isConnectedOrConnecting()) {
            return true;
        }
        NetworkInfo mMobile = connectivity
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mMobile != null && mMobile.isAvailable()
                && mMobile.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    /**
     * 检查数据是否支持移动数据连接
     */
    public static boolean isSupportMobileConnect(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity.getActiveNetworkInfo() == null) {
            return false;
        }
        NetworkInfo mMobile = connectivity
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mMobile != null) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前使用的是否wap连接
     *
     * @param context
     * @return
     */
    public static boolean isWapConnecting(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netWrokInfo = manager.getActiveNetworkInfo();
        if (netWrokInfo != null && netWrokInfo.isAvailable()
                && netWrokInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            // String proxyHost = android.net.Proxy.getDefaultHost();
            // return proxyHost;
            String wapType = netWrokInfo.getExtraInfo();
            if (wapType != null) {
                if (wapType.toLowerCase().indexOf("wap") >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断当前使用的是否wifi连接
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnecting(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netWrokInfo = manager.getActiveNetworkInfo();
        if (netWrokInfo != null && netWrokInfo.isAvailable()
                && netWrokInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }

        NetworkInfo mWifi = manager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi != null && mWifi.isAvailable()
                && mWifi.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static final int CONNECTIONTYPE_NONE = 0;
    public static final int CONNECTIONTYPE_MOBILE = 1;
    public static final int CONNECTIONTYPE_WIFI = 2;
    private static int previousConnetionType;

    public static int getConnectionType(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netWrokInfo = connectivity.getActiveNetworkInfo();
        if (netWrokInfo == null || !netWrokInfo.isAvailable()) {
            return CONNECTIONTYPE_NONE;
        }
        if (netWrokInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return CONNECTIONTYPE_WIFI;
        }
        return CONNECTIONTYPE_MOBILE;
    }

    public static void setPreviousConectionType(Context context){
        previousConnetionType = getConnectionType(context);
    }

    public static boolean isConnectionTypeChanged(Context context){
        return previousConnetionType != getConnectionType(context);
    }

    public static void hideSoftInput(Context context, EditText editText) {
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    public static void showSoftInput(Context context, EditText editText) {
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, 0);
        }
    }

    private static DisplayMetrics dm;
    private static int ScreenHeight;
    private static int ScreenWidth;

    public static int getScreenHeight(Activity activity) {
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenHeight = dm.heightPixels;
        return ScreenHeight;
    }

    public static int getScreenWidth(Activity activity) {
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenWidth = dm.widthPixels;
        return ScreenWidth;
    }

    // 转换dip为px
    public static int dip2px(Context context, int dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    // 转换px为dip
    public static int px2dip(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

//    private static class MyToast {
//        private static Toast mToast;
//        public static void showToast(Context context, String text) {
//            showToast(context, text, Toast.LENGTH_SHORT);
//        }
//
//        public static void showToast(Context context, int resid) {
//            showToast(context, resid, Toast.LENGTH_SHORT);
//        }
//
//        public static void showToast(Context context, int resid,
//                Object... formatArgs) {
//            showToast(context, resid, Toast.LENGTH_SHORT, formatArgs);
//        }

//        public static void showToast(Context context, int resid, int duration) {
//            try {
//                if(mToast==null){
//                    mToast = new Toast(context.getApplicationContext());
//                    mToast.setDuration(duration);
//                }
//                if (resid == R.string.noconnectionremind || resid == R.string.noconnection) {
//					mToast.setGravity(Gravity.CENTER, 0, 0);
//				} else if(resid==R.string.live_network_error){
//                    mToast.setGravity(Gravity.TOP, 0, 0);
//                }else {
//					mToast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0,
//							context.getResources().getDimensionPixelSize(R.dimen.toast_margin_window));
//				}
//                mToast.setView(customView(context.getApplicationContext(), resid));
//
//                mToast.show();
//            } catch (Throwable tr) {
//                tr.printStackTrace();
//            }
//        }

//        public static void showToast(Context context, int resid, int duration,
//                Object... formatArgs) {
//            try {
//                if (mToast == null) {
//                    mToast = new Toast(context.getApplicationContext());
//                    mToast.setDuration(duration);
//                }
//                if (resid == R.string.noconnectionremind || resid == R.string.noconnection) {
//					mToast.setGravity(Gravity.CENTER, 0, 0);
//				} else {
//					mToast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0,
//							context.getResources().getDimensionPixelSize(R.dimen.toast_margin_window));
//				}
//                mToast.setView(customView(context.getApplicationContext(),
//                        context.getString(resid, formatArgs)));
//                mToast.show();
//            } catch (Throwable tr) {
//                tr.printStackTrace();
//            }
//        }
//
//        public static void showToast(Context context, String text, int duration) {
//            if(context == null){
//                return;
//            }
//            try {
//                if (mToast == null) {
//                    mToast = new Toast(context.getApplicationContext());
//                }
//                mToast.setDuration(duration);
//                mToast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0,
//                        context.getResources().getDimensionPixelSize(R.dimen.toast_margin_window));
//                mToast.setView(customView(context.getApplicationContext(), text));
//                mToast.show();
//            } catch (Throwable tr) {
//                tr.printStackTrace();
//            }
//        }
//
//        public static void showToast(Context context, String text, int duration, int gravity) {
//            try {
//                if (mToast == null) {
//                    mToast = new Toast(context.getApplicationContext());
//                }
//                mToast.setDuration(duration);
//                mToast.setGravity(gravity, 0,
//                        context.getResources().getDimensionPixelSize(R.dimen.toast_margin_window));
//                mToast.setView(customView(context.getApplicationContext(), text));
//                mToast.show();
//            } catch (Throwable tr) {
//                tr.printStackTrace();
//            }
//        }
//
//        public static void showToast(Context context, int resId, int duration, int gravity) {
//            try {
//                if (mToast == null) {
//                    mToast = new Toast(context.getApplicationContext());
//                }
//                mToast.setDuration(duration);
//                mToast.setGravity(gravity, 0,
//                        context.getResources().getDimensionPixelSize(R.dimen.toast_margin_window));
//                mToast.setView(customView(context.getApplicationContext(), resId));
//                mToast.show();
//            } catch (Throwable tr) {
//                tr.printStackTrace();
//            }
//        }
//    }

//	private static View customView(Context context, int resId) {
//		View v = null;
//		if (resId == R.string.noconnectionremind || resId == R.string.noconnection) {
//			v = findNetToastView(context);
//		} else {
//			v = findToastView(context);
//		}
//		TextView tv = (TextView) v.findViewById(R.id.message);
//		tv.setText(resId);
//		return v;
//	}
//
//    private static View findToastView(Context context) {
//        LayoutInflater inflate = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflate.inflate(R.layout.transient_notification, null);
//        return v;
//    }
//
//    private static View findNetToastView(Context context) {
//        LayoutInflater inflate = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflate.inflate(R.layout.toast_tip, null);
//        return v;
//    }
//
//    private static View customView(Context context, String text) {
//        View v = findToastView(context);
//        TextView tv = (TextView) v.findViewById(R.id.message);
//        tv.setText(text);
//        return v;
//    }
//
//    private static int pBottom = 0;
//    private static int pTop = 0;
//
//    public static int getPaddingBottom(Context context) {
//        if (pBottom == 0) {
//            pBottom = dip2px(context, 52);
//        }
//        return pBottom;
//    }
//
//    public static int getPaddingTop(Context context) {
//        if (pTop == 0) {
//            pTop = dip2px(context, 45);
//        }
//        return pTop;
//    }

    /**
     * 是否有桌面快捷方式
     *
     * @return
     */
    public static boolean hasShortCut(Context context, String appName) {
        Uri uri;
        if (Build.VERSION.SDK_INT < 8) {
            uri = Uri
                    .parse("content://com.android.launcher.settings/favorites?notify=true");
        } else {
            uri = Uri
                    .parse("content://com.android.launcher2.settings/favorites?notify=true");
        }
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, null, "title=?",
                    new String[] {
                        appName
                    }, null);

            if (cursor != null && cursor.moveToFirst()) {
                cursor.close();
                return true;
            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return false;
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sbar;
    }
//
//    public static boolean showNetErrorMessage(Context context){
//        if(!checkNetworkConnection(context)){
//            ContextUtils.showToast(context, R.string.noconnectionremind);
//            return false;
//        }
//        return true;
//    }

    public static boolean showAboutme(String aboutme){
        if (TextUtils.isEmpty(aboutme)) {
            return false;
        }
        if (aboutme.contains("微博") || aboutme.contains("微信") || aboutme.toLowerCase().contains("http")
                || aboutme.toLowerCase().contains(".com") || aboutme.toLowerCase().contains(".cn")) {
            return false;
        }
        return true;
    }


}
