package sample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sample.model.SampleModel

class SampleViewModel(

):ViewModel() {

    private val _grade = MutableLiveData<Int>()
    val grade: MutableLiveData<Int> = _grade

    private val _class_num = MutableLiveData<Int>()
    val class_num: MutableLiveData<Int> = _class_num

    fun changeGrade(sampleModel: SampleModel){
        _grade.value = sampleModel.grade
        _class_num.value = sampleModel.class_num
    }

}