package steelkiwi.com.navigationmanager.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import steelkiwi.com.navigationmanager.AbstractBaseActivity;
import steelkiwi.com.navigationmanager.R;
import steelkiwi.com.navigationmanager.navigation.factory.ActivityFactory;
import steelkiwi.com.navigationmanager.navigation.factory.FragmentFactory;

/**
 * Created by yaroslav on 4/12/17.
 */

public class NavigationManager implements NavigationController {

    private AbstractBaseActivity activity;
    private @IdRes int container;
    private FragmentManager fragmentManager;
    private ActivityFactory activityFactory;
    private FragmentFactory fragmentFactory;
    private List<Class<?>> fragments = new ArrayList<>();
    private Class<?> currentScreen;

    public NavigationManager(AbstractBaseActivity activity) {
        this.activity = activity;
        this.fragmentManager = activity.getSupportFragmentManager();
        activityFactory = new ActivityFactory(activity);
        fragmentFactory = new FragmentFactory(activity);
    }

    /**
     * The method to navigate to some activity
     * @param screen it's an activity where need navigate
     * */
    @Override
    public void navigateToActivity(Class<?> screen) {
        toActivity(screen, null, false, AnimationType.NONE_TYPE);
    }

    /**
     * The method to navigate to some activity
     * @param screen it`s an activity where need navigate
     * @param bundle it`s bundle for put some data between activities
     * */
    @Override
    public void navigateToActivity(Class<?> screen, Bundle bundle) {
        toActivity(screen, bundle, false, AnimationType.NONE_TYPE);
    }

    /**
     * The method to navigate to some activity
     * @param screen it's an activity where need navigate
     * @param bundle it`s bundle for put some data between activities
     * @param isActivityRoot flag for making activity root
     * */
    @Override
    public void navigateToActivity(Class<?> screen, Bundle bundle, boolean isActivityRoot) {
        toActivity(screen, bundle, isActivityRoot, AnimationType.NONE_TYPE);
    }

    /**
     * The method to navigate to some activity
     * @param screen it's an activity where need navigate
     * @param bundle it`s bundle for put some data between activities
     * @param isActivityRoot flag for making activity root
     * @param animation activity switch animation type
     * */
    @Override
    public void navigateToActivity(Class<?> screen, Bundle bundle, boolean isActivityRoot, AnimationType animation) {
        toActivity(screen, bundle, isActivityRoot, animation);
    }

    /**
     * The method to navigate to some fragment
     * @param screen it`s fragment what need replace
     * @param isAddToBackStack flag for adding fragment to back stack
     * */
    @Override
    public void navigateToFragment(Class<?> screen, boolean isAddToBackStack) {
        toFragment(screen, null, isAddToBackStack, false);
    }

    /**
     * The method to navigate to some fragment
     * @param screen it`s fragment what need replace
     * @param bundle it`s bundle for put some data between fragments
     * @param isAddToBackStack flag for adding fragment to back stack
     * */
    @Override
    public void navigateToFragment(Class<?> screen, Bundle bundle, boolean isAddToBackStack) {
        toFragment(screen, bundle, isAddToBackStack, false);
    }

    /**
     * The method to navigate to some fragment
     * @param screen it`s fragment what need replace
     * @param bundle it`s bundle for put some data between fragments
     * @param isAddToBackStack flag for adding fragment to back stack
     * @param animation flag for showing animation when fragment replace
     * */
    @Override
    public void navigateToFragment(Class<?> screen, Bundle bundle, boolean isAddToBackStack, boolean animation) {
        toFragment(screen, bundle, isAddToBackStack, animation);
        activity.hideKeyboard();
    }

    private void toActivity(Class<?> screen, Bundle bundle, boolean isActivityRoot, AnimationType animation) {
        currentScreen = screen;
        switchActivity(screen, bundle, animation, isActivityRoot);
        activity.hideKeyboard();
    }

    private void toFragment(Class<?> screen, Bundle bundle, boolean isAddToBackStack, boolean animation) {
        currentScreen = screen;
        switchFragment(screen, bundle, animation, isAddToBackStack);
        fragments.add(screen);
    }

    private void switchFragment(Class<?> screen, Bundle bundle, boolean animation, boolean isAddToBackStack) {
        if (isSameFragmentAlreadyPlaced(screen)) {
            return;
        }
        FragmentTransaction tran = fragmentManager.beginTransaction();
        Fragment fragment = fragmentFactory.getFragmentByName(screen);
        if (bundle != null && !bundle.isEmpty()) {
            fragment.setArguments(bundle);
        }
        if (animation) {
            tran.setCustomAnimations(R.anim.right_to_left_in, R.anim.right_to_left_out, R.anim.left_to_right_in, R.anim.left_to_right_out);
        }
        if (isAddToBackStack) {
            tran.replace(container, fragment, fragment.getClass().getSimpleName());
            tran.addToBackStack(fragment.getClass().getSimpleName());
        } else {
            tran.replace(container, fragment);
        }
        tran.commit();
    }

    private void switchActivity(Class<?> screen, Bundle bundle, AnimationType animation, boolean isActivityRoot) {
        Intent intent = activityFactory.getActivityByName(screen);
        if (isActivityRoot) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (bundle != null && !bundle.isEmpty()) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        switch (animation) {
            case FADE_TYPE:
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case RIGHT_TO_LEFT_TYPE:
                activity.overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out);
                break;
            case LEFT_TO_RIGHT_TYPE:
                activity.overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
                break;
        }
    }

    private boolean isSameFragmentAlreadyPlaced(Class<?> requested) {
        Fragment existing = activity.getSupportFragmentManager().findFragmentById(container);
        if (existing != null) {
            if (existing.getClass().equals(requested)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clear all fragment from our stack
     * */
    public void clearScreenStack() {
        fragments.clear();
    }

    /**
     * Clear fragment by position from our stack
     * */
    public void removeFragmentsFromStack(int position) {
        fragments.remove(position);
    }
    /**
     * Method for checking if screen is current
     * @param screen instance that need to check
     * @return flag if screen is current
     * */
    public boolean isCurrentScreen(Class<?> screen) {
        return currentScreen == screen;
    }

    /**
     * Set container id recourse for putting fragment inside
     * @param container container id recourse
     * */
    public void setFragmentContainer(@IdRes int container) {
        this.container = container;
    }

    /**
     * Set FragmentManager for handle replace logic in the
     * activity(SupportFragmentManager) and fragment(ChildFragmentManager)
     * @param fragmentManager fragment manager instance
     * */
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
}
