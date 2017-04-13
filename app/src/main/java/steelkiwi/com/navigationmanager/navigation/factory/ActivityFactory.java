package steelkiwi.com.navigationmanager.navigation.factory;

import android.content.Context;
import android.content.Intent;

/**
 * Created by yaroslav on 4/12/17.
 */

public class ActivityFactory {

    private Context context;

    public ActivityFactory(Context context) {
        this.context = context;
    }

    public Intent getActivityByName(Class<?> screen){
        return new Intent(context, screen);
    }
}
