# ProGuard rules for RE-WILDER

# Keep all game classes
-keep class com.rewilderdev.** { *; }

# Keep LibGDX classes
-keep class com.badlogic.gdx.** { *; }
-keep class com.badlogic.gdx.backends.android.** { *; }

# Keep Android classes
-keep class android.** { *; }
-keep class androidx.** { *; }

# Keep enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep view constructors for inflation
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable

# Rename SourceFile attribute
-renamesourcefileattribute SourceFile
