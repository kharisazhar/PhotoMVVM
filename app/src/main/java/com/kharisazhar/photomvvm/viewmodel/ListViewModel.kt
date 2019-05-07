package com.kharisazhar.photomvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kharisazhar.photomvvm.api.PhotosService
import com.kharisazhar.photomvvm.model.Photo
import com.kharisazhar.photomvvm.utils.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val photosService = PhotosService()
    private val disposable = CompositeDisposable()

    val photos = MutableLiveData<List<Photo>>()


    fun fetchData() {
        EspressoIdlingResource.increment()
        disposable.add(
            photosService.getPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Photo>>() {
                    override fun onSuccess(value: List<Photo>?) {
                        photos.value = value
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("ERRORFETCHDATA", "error$e")
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}