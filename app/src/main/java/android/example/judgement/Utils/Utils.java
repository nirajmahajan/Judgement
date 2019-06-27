package android.example.judgement.Utils;

import android.app.Activity;
import android.content.Context;
import android.example.judgement.Utils.database.AppDatabase;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

public class Utils {
    public static void quitApp(Context context, Activity activity) {
        AppDatabase.destroyInstance(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.finishAffinity();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.finishAndRemoveTask();
        }
        activity.moveTaskToBack(true);
        System.exit(0);
    }

    public static void DeleteRecursive(File dir)
    {
        Log.d("DeleteRecursive", "DELETEPREVIOUS TOP" + dir.getPath());
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                File temp = new File(dir, children[i]);
                if (temp.isDirectory())
                {
                    Log.d("DeleteRecursive", "Recursive Call" + temp.getPath());
                    DeleteRecursive(temp);
                }
                else
                {
                    Log.d("DeleteRecursive", "Delete File" + temp.getPath());
                    boolean b = temp.delete();
                    if (b == false)
                    {
                        Log.d("DeleteRecursive", "DELETE FAIL");
                    }
                }
            }

        }
        dir.delete();
    }

    public static void hideSoftKeyboard(Activity activity) {
        View focusedView = activity.getCurrentFocus();
        if(focusedView != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public static void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }
}
