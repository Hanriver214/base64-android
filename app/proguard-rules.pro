# Add project specific ProGuard rules here.
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.view.View

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep JavaScript Interface methods
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Suppress R8 warnings
-dontwarn java.lang.invoke.StringConcatFactory
-dontwarn javax.lang.model.element.ModuleElement$DirectiveVisitor