# NavigationTools

Simple Navigation tools (between activities and fragments) for Android

# Components

- NavigationManager - components for navigate between activities and fragments
- NavigationBackManager - components for navigate back the stack after user event
- AbstractBaseActivity - Abstract Activity that already contains this components

# Usage

# Navigation Manager

You can inherits your own BaseActivity from AbstractBaseActivity you already have access to the NavigationManager.
If you don`t want do it and decide not inherits from base abstract activity you need create NavigationManager.
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

So to navigate to some activity you need call one of this method.

```java
public void navigateToActivity(Class<?> screen) {
    // will navigate to screen class
}
```

As an option you need put activity class what need start, also you can put some other option like
1) Bundle object with your data

```java
public void navigateToActivity(Class<?> screen, Bundle bundle) {
    // will navigate to screen class
}
```

2) Make activity root

```java
public void navigateToActivity(Class<?> screen, Bundle bundle, boolean isActivityRoot) {
    // will navigate to screen class
}
```

3) Add start animation type

```java
public void navigateToActivity(Class<?> screen, Bundle bundle, boolean isActivityRoot, AnimationType animation) {
    // will navigate to screen class
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

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
```

