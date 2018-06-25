package io.levelsoftware.daggerplayground.models.sample

interface SampleModel {
    fun writeValue(value: String)
    fun retrieveValue(): String
}