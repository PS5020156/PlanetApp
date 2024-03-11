object Dependencies {
    const val core = "androidx.core:core-ktx:${Versions.coreVersion}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtimeKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val material3 = "androidx.compose.material3:material3"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJUnitVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val composeBomTest = "androidx.compose:compose-bom:${Versions.composeBomVersion}"
    const val junit4 = "androidx.compose.ui:ui-test-junit4"
    const val uiTooling = "androidx.compose.ui:ui-tooling"
    const val testManifest = "androidx.compose.ui:ui-test-manifest"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val runtimeCompose =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.runtime_compose}"
    const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.nav_version}"
    const val room = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_version}"

    // test implementations
    const val roomTesting = "androidx.room:room-testing:${Versions.roomTestingVersion}"
    const val mockServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockServerVersion}"
    const val coroutineTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineVersion}"
    const val mockitoTest = "org.mockito:mockito-core:${Versions.mockitoTestVersion}"
    const val mockitoKotlinTest =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinTestVersion}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTestingVersion}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbineVersion}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInlineVersion}"
}