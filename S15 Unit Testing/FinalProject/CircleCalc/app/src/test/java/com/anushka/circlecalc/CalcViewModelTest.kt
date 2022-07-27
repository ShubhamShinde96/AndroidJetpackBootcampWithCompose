package com.anushka.circlecalc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CalcViewModelTest {


    private lateinit var calcViewModel: CalcViewModel
    private lateinit var calculations: Calculations

    // last point: now we have one last thing to do, since we're going to assert live data, we need to add instant
    // task executor role to this class, this rule runs all architecture components related background jobs in the
    // same thread so that the test results happen synchronously and in repeatable order.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        // Now we're generating a calculations instance using mockito
        calculations = Mockito.mock(Calculations::class.java)

        // As we have learned: mocks allows us to set answer to method calls when writing the test case.
        // Following is the way how we do it.
        Mockito.`when`(calculations.calculateArea(2.1)).thenReturn(13.8474)
        Mockito.`when`(calculations.calculateCircumference(1.0)).thenReturn(6.28)

        calcViewModel = CalcViewModel(calculations)
    }

    @Test
    fun calculateArea_radiusSent_updateLiveData() {
        calcViewModel.calculateArea(2.1)

        // if the calcViewModel code is working as expected then area value, livedata should provide the result as
        // 13.8474

        val result = calcViewModel.areaValue.value
        assertThat(result).isEqualTo("13.8474")
    }

    @Test
    fun calculateCircumference_radiusSent_updateLiveData() {

        calcViewModel.calculateCircumference(2.1)

        // if the calcViewModel code is working as expected then area value, livedata should provide the result as
        // 13.8474

        val result = calcViewModel.circumferenceValue.value
        assertThat(result).isEqualTo("13.188")
    }

}