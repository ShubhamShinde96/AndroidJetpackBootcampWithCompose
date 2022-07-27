package com.anushka.circlecalc

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class MyCalcTest {

    private lateinit var myCalc: MyCalc


    // to generate setUp fun press alt+insert > setUp function
    @Before
    fun setUp() {
        myCalc = MyCalc()
    }

    // Naming rules syntax: SubjectUnderTest_actionOrInput_resultState
    @Test
    fun calculateCircumference_radiusGiven_returnsCorrectResults() {
//        myCalc = MyCalc() // This inefficient, as we are initialising this in every fun
        // so we can use a setUp fun annotated with @before annotation for that.

        val result = myCalc.calculateCircumference(2.1)

        assertThat(result).isEqualTo(13.188)
    }


    // To check the result for 0, if the passed radius is 0 then circumference will also 0
    @Test
    fun calculateCircumference_zeroRadius_returnsCorrectResults() {
//        myCalc = MyCalc()

        val result = myCalc.calculateCircumference(0.0)

        assertThat(result).isEqualTo(0.0)
    }


    // Assignment
    @Test
    fun calculateArea_radiusGiven_returnsCorrectResult() {

        val result = myCalc.calculateArea(2.1)


//        assertThat(String.format("%.2f", result).toDouble()).isEqualTo(13.8474)
        assertThat(result).isEqualTo(13.8474)
    }

    @Test
    fun calculateArea_zeroRadius_returnsCorrectResult() {

        val result = myCalc.calculateArea(0.0)

        assertThat(result).isEqualTo(0.0)
    }

}