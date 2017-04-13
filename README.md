# NavigationTools

Simple Navigation tools (between activities and fragments) for Android

# Components

- NavigationManager - components for navigate between activities and fragments
- NavigationBackManager - components for navigate back the stack after user event
- AbstractBaseActivity - Abstract Activity that already contains this components

# Usage

# Navigation Manager

You can inherits your own BaseActivity from AbstractBaseActivity.
Or initialize it by yourself

```java
    private NavigationManager navigationManager;
    private NavigationBackManager navigationBackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationManager = new NavigationManager(this);
        // set container for fragments
        navigationManager.setFragmentContainer(R.id.container);
        // by default use SupportFragmentManager
        navigationManager.setFragmentManager(getSupportFragmentManager());
        navigationBackManager = new NavigationBackManager(this, navigationManager);
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public NavigationBackManager getNavigationBackManager() {
        return navigationBackManager;
    }
```

NavigationManager have some method what need to set it before use

```java
public void setFragmentContainer(@IdRes int container) {
    // set container recourse for put fragment inside this container
}
```

Also need set ChildFragmentManager if you use nested fragments. By default manager use SupportFragmentManager

```java
public void setFragmentManager(FragmentManager fragmentManager) {
    // set fragment manager for replacing fragments
}
```

When manager is prepared you can use it.

# Navigate to Activity

To navigate to some activity you need call one of this method with options

1) Activity class

```java
public void navigateToActivity(Class<?> screen) {
    // will navigate to activity class
}
```

2) Bundle object with your data

```java
public void navigateToActivity(Class<?> screen, Bundle bundle) {
    // will navigate to activity class
}
```

3) Make activity root

```java
public void navigateToActivity(Class<?> screen, Bundle bundle, boolean isActivityRoot) {
    // will navigate to activity class
}
```

4) Add start animation type

```java
public void navigateToActivity(Class<?> screen, Bundle bundle, boolean isActivityRoot, AnimationType animation) {
    // will navigate to activity class
}
```

# Navigate to Fragment

To navigate to some fragment you need call one of this method with options

1) Fragment class and flag to add into back stack

```java
public void navigateToFragment(Class<?> screen, boolean isAddToBackStack) {
    // will navigate fragment class
}
```

2) Bundle object with your data

```java
public void navigateToFragment(Class<?> screen, Bundle bundle, boolean isAddToBackStack) {
    // will navigate fragment class
}
```

3) include start animation

```java
public void navigateToFragment(Class<?> screen, Bundle bundle, boolean isAddToBackStack, boolean animation) {
    // will navigate fragment class
}
```

If you need to check what screen is current you can use this method

```java
public boolean isCurrentScreen(Class<?> screen) {
    // will return screen is current
}
```

# License

```
Copyright 2017 SteelKiwi Inc, steelkiwi.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
```

