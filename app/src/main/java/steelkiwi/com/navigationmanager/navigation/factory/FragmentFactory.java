package steelkiwi.com.navigationmanager.navigation.factory;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by yaroslav on 4/12/17.
 */

public class FragmentFactory {

    private Context context;

    public FragmentFactory(Context context) {
        this.context = context;
    }

    public Fragment getFragmentByName(Class<?> screen){
        return Fragment.instantiate(context, screen.getName());
    }
}
