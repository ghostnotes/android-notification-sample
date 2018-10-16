package co.ghostnotes.sample.notification.util

import android.os.Build

class AndroidVersionUtils {

    fun isOreoOrUp(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    fun isNougatOrBelow(): Boolean = Build.VERSION.SDK_INT <= Build.VERSION_CODES.N

}