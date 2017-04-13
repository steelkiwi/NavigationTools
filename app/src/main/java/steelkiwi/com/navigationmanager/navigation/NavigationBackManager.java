package steelkiwi.com.navigationmanager.navigation;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import steelkiwi.com.navigationmanager.AbstractBaseActivity;

/**
 * Created by yaroslav on 4/13/17.
 */

public class NavigationBackManager {

    private final static String TAG = NavigationBackManager.class.getSimpleName();
    private final static int TIME_INTERVAL = 1600;

    private AbstractBaseActivity activity;
    private NavigationManager navigationManager;
    private boolean doublePressToExit = false;

    public NavigationBackManager(AbstractBaseActivity activity, NavigationManager navigationManager) {
        this.activity = activity;
        this.navigationManager = navigationManager;
    }

    public void navigateBack() {
        activity.hideKeyboard();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            Log.i(TAG, "Popping back stack");
            int fragmentPosition = fragmentManager.getBackStackEntryCount() - 1;
            FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(fragmentPosition);
            String fragmentName = backEntry.getName();
            fragmentManager.popBackStackImmediate(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            navigationManager.removeFragmentsFromStack(fragmentPosition);
        } else {
            Log.i(TAG, "Nothing on back stack");
            if (activity.isTaskRoot()) {
                if (doublePressToExit) {
                    activity.finish();
                    return;
                }
                doublePressToExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doublePressToExit = false;
                    }
                }, TIME_INTERVAL);
            } else {
                activity.finish();
            }
        }
    }

    public void clearBackStack(){
        int backStackEntry = activity.getSupportFragmentManager().getBackStackEntryCount();
        if(backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                activity.getSupportFragmentManager().popBackStackImmediate();
            }
            navigationManager.clearScreenStack();
        }
    }

    public void setDoublePressToExit(boolean doublePressToExit) {
        this.doublePressToExit = doublePressToExit;
    }
}
