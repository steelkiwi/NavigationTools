package steelkiwi.com.navigationmanager.navigation;

import android.os.Bundle;

/**
 * Created by yaroslav on 4/12/17.
 */

public interface NavigationController {
    void navigateToActivity(Class<?> screen);
    void navigateToActivity(Class<?> screen, Bundle bundle);
    void navigateToActivity(Class<?> screen, Bundle bundle, boolean isActivityRoot);
    void navigateToActivity(Class<?> screen, Bundle bundle, boolean isActivityRoot, AnimationType animation);
    void navigateToFragment(Class<?> screen, boolean isAddToBackStack);
    void navigateToFragment(Class<?> screen, Bundle bundle, boolean isAddToBackStack);
    void navigateToFragment(Class<?> screen, Bundle bundle, boolean isAddToBackStack, boolean animation);
}
