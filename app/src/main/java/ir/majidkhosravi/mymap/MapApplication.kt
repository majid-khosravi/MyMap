package ir.majidkhosravi.mymap

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This class only created to assign the [dagger.hilt.android.HiltAndroidApp] annotation into our application
 */

/**
 * Annotation for marking the [android.app.Application] class where the Dagger components
 * should be generated. Since all components will be built in the same compilation as the annotated
 * application, all modules and entry points that should be installed in the component need to be
 * transitive compilation dependencies of the annotated application.
 */


@HiltAndroidApp
class MapApplication : Application()