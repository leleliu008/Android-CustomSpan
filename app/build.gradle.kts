plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(26)
    buildToolsVersion("26.0.2")

    defaultConfig {
        minSdkVersion(14)
        targetSdkVersion(25)
        applicationId = "com.fpliu.newton.ui.span.sample"
        versionCode = 1
        versionName = "1.0.0"
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDir("src/main/libs")
            aidl.srcDirs("src/main/kotlin")
            java.srcDirs("src/main/kotlin")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    lintOptions {
        isAbortOnError = false
    }

    compileOptions {
        //使用JAVA8语法解析
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
}

dependencies {
    api(project(":library"))

    //http://kotlinlang.org/docs/reference/using-gradle.html#configuring-dependencies
    api("org.jetbrains.kotlin:kotlin-stdlib:1.2.21")

    api("com.fpliu:Android-BaseUI:1.0.0")
    api("com.fpliu:Android-CustomDimen:1.0.0")
//    api("com.fpliu:Android-CustomSpan:1.0.0")
}
