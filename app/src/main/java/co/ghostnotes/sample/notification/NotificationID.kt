package co.ghostnotes.sample.notification

import java.util.concurrent.atomic.AtomicInteger

object NotificationID {

    private val atomicInteger = AtomicInteger()

    fun get(): Int = atomicInteger.incrementAndGet()

}