package co.ghostnotes.sample.notification.util

import android.os.Build
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidVersionUtilsTest {

    private lateinit var androidVersionUtils: AndroidVersionUtils

    @Before
    fun setUp() {
        androidVersionUtils = AndroidVersionUtils()
    }

    @Test
    fun isOreoOrUp() {
        val isOreo = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

        assertThat(androidVersionUtils.isOreoOrUp(), `is`(isOreo))
    }

    @Test
    fun isNougatOrBelow() {
        val isNougat = Build.VERSION.SDK_INT <= Build.VERSION_CODES.N

        assertThat(androidVersionUtils.isNougatOrBelow(), `is`(isNougat))
    }

}