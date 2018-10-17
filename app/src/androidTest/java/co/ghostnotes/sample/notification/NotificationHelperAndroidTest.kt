package co.ghostnotes.sample.notification

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import co.ghostnotes.sample.notification.util.AndroidVersionUtils
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotificationHelperAndroidTest {

    companion object {

        private const val NOTIFICATION_WAIT_TIMEOUT = 3000L

    }

    private lateinit var context: Context
    private lateinit var notificationHelper: NotificationHelper

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
        notificationHelper = NotificationHelper(context, NotificationID, AndroidVersionUtils())
    }

    @Test
    fun notifyDefault() {
        val notificationTitle = context.getString(R.string.notification_title_default)
        val notificationText = context.getString(R.string.notification_text_default)

        // Call
        notificationHelper.notifyDefault()

        // Test
        assertNotification(notificationTitle, notificationText)
    }

    @Test
    fun notifyMin() {
        val notificationTitle = context.getString(R.string.notification_title_min)
        val notificationText = context.getString(R.string.notification_text_min)

        // Call
        notificationHelper.notifyMin()

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.openNotification()
        device.wait(Until.hasObject(By.text(notificationTitle)), NOTIFICATION_WAIT_TIMEOUT)

        val titleObject = device.findObject(By.text(notificationTitle))
        titleObject.click()
        device.wait(Until.hasObject(By.text(notificationText)), NOTIFICATION_WAIT_TIMEOUT)

        // Test
        assertNotification(notificationTitle, notificationText)
    }

    @Test
    fun notifyLow() {
        val notificationTitle = context.getString(R.string.notification_title_low)
        val notificationText = context.getString(R.string.notification_text_low)

        // Call
        notificationHelper.notifyLow()

        // Test
        assertNotification(notificationTitle, notificationText)
    }

    @Test
    fun notifyHigh() {
        val notificationTitle = context.getString(R.string.notification_title_high)
        val notificationText = context.getString(R.string.notification_text_high)

        // Call
        notificationHelper.notifyHigh()

        // Test
        assertNotification(notificationTitle, notificationText)
    }


    @Test
    fun notifyMax() {
        val notificationTitle = context.getString(R.string.notification_title_max)
        val notificationText = context.getString(R.string.notification_text_max)

        // Call
        notificationHelper.notifyMax()

        // Test
        assertNotification(notificationTitle, notificationText)
    }

    private fun assertNotification(title: String, text: String) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.openNotification()
        device.wait(Until.hasObject(By.text(title)), NOTIFICATION_WAIT_TIMEOUT)

        val titleObject = device.findObject(By.text(title))
        assertThat(titleObject.text, `is`(title))

        val textObject = device.findObject(By.text(text))
        assertThat(textObject.text, `is`(text))
    }

}