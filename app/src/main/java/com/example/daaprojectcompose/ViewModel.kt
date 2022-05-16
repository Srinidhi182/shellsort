package com.example.daaprojectcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModel: ViewModel() {

    private val array1 = MutableLiveData<List<String>>()
    val _array: LiveData<List<String>> = array1


    fun shellSort(list: List<String>) {
        var array = list.toTypedArray()
        var h = 1
        while (h < list.size / 3) {
            h = h * 3 + 1
        }

        while (h >= 1) {
            for (i in h until list.size) {
                for (j in i downTo h step h) {
                    if (array[j - h] < array[j]) break
                    var temp = array[j]
                    array[j] = array[j-h]
                    array[j-h]= temp.toString()
                }
            }
            h /= 3
        }
        array1.value = array.toList()
    }

    fun clearArray(){
        array1.value = listOf()
    }

}