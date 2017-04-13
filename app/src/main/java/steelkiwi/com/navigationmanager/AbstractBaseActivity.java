package steelkiwi.com.navigationmanager;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import steelkiwi.com.navigationmanager.navigation.NavigationBackManager;
import steelkiwi.com.navigationmanager.navigation.NavigationManager;

/**
 * Created by yaroslav on 4/13/17.
 */

public abstract class AbstractBaseActivity extends AppCompatActivity {

    private InputMethodManager inputMethodManager;
    private NavigationManager navigationManager;
    private NavigationBackManager navigationBackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        navigationManager = new NavigationManager(this);
        // set container for fragments
        navigationManager.setFragmentContainer(R.id.container);
        // by default use SupportFragmentManager
        navigationManager.setFragmentManager(getSupportFragmentManager());
        navigationBackManager = new NavigationBackManager(this, navigationManager);
    }

    @Override
    public void onBackPressed() {
        navigationBackManager.navigateBack();
    }

    public boolean hideKeyboard() {
        try {
            IBinder windowToken = getWindow().getDecorView().getRootView().getWindowToken();
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void showKeyboard(View view){
        inputMethodManager.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public NavigationBackManager getNavigationBackManager() {
        return navigationBackManager;
    }
}
