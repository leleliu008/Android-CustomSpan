plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.github.dcendents.android-maven")
    id("com.jfrog.bintray")
    id("com.fpliu.bintray.upload.android")
}

android {
    compileSdkVersion(26)
    buildToolsVersion("26.0.2")

    defaultConfig {
        minSdkVersion(14)
        targetSdkVersion(25)
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
    api(fileTree(mapOf(Pair("dir", "src/main/libs"), Pair("include", "*.jar"))))

    //http://kotlinlang.org/docs/reference/using-gradle.html#configuring-dependencies
    api("org.jetbrains.kotlin:kotlin-stdlib:1.2.21")
}

// 这里是groupId,必须填写,一般填你唯一的包名
group = "com.fpliu"

//这个是版本号，必须填写
version = "1.0.0"

val rootProjectName: String = rootProject.name

bintrayUploadAndroidExtension {
    developerName = "leleliu008"
    developerEmail = "leleliu008@gamil.com"

    projectSiteUrl = "https://github.com/$developerName/$rootProjectName"
    projectGitUrl = "https://github.com/$developerName/$rootProjectName"

    bintrayUserName = "fpliu"
    bintrayOrganizationName = "fpliu"
    bintrayRepositoryName = "newton"
    bintrayApiKey = ""
}