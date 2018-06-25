package io.levelsoftware.daggerplayground.models.sample

import android.content.SharedPreferences
import android.content.res.Resources
import com.nhaarman.mockitokotlin2.whenever
import io.levelsoftware.daggerplayground.models.events.ProductionEventBusModel
import io.levelsoftware.daggerplayground.models.sample.ProductionSampleModel.Companion.PRODUCTION_KEY
import io.reactivex.subjects.PublishSubject
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductionSampleModelTest {

    @Mock
    private lateinit var eventBusModel: ProductionEventBusModel

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Mock
    private lateinit var resources: Resources

    private lateinit var model: ProductionSampleModel

    @Before
    fun setup() {
        whenever(sharedPreferences.getString(matches(PRODUCTION_KEY), anyString()))
                .thenReturn("testResult")
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        whenever(sharedPreferencesEditor.putString(anyString(), anyString())).thenReturn(sharedPreferencesEditor)
        whenever(eventBusModel.eventBus).thenReturn(PublishSubject.create())

        model = ProductionSampleModel(eventBusModel, sharedPreferences, resources)
    }

    @Test
    fun retrieveValue_returnsStringForProductionKey() {
        assertEquals("testResult", model.retrieveValue())
        verify(sharedPreferences, atMost(1)).getString(eq(PRODUCTION_KEY), any())
    }

    @Test
    fun writeValue_writesToSharedPreferences() {
        val testValue = "Random test value"
        model.writeValue(testValue)
        verify(sharedPreferences, atMost(1)).edit()
        verify(sharedPreferencesEditor).putString(eq(PRODUCTION_KEY), endsWith(testValue))
    }

    @Test
    fun isDisposed_trueAfterDisposal() {
        assertFalse(model.isDisposed)
        model.dispose()
        assertTrue(model.isDisposed)
    }

}