package com.shubham.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shubham.periodicworkrequest.MainActivity.Companion.KEY_COUNT_VALUE
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {

        const val KEY_WORKER = "key_worker"

    }

    override fun doWork(): Result {

        try {

            val count = inputData.getInt(KEY_COUNT_VALUE, 0)

//            for (i in 0..600000) {
//            for (i in 0..count) {
            for (i in 0 until count) {

                Log.i("MY_TAG", "Uploading $i")
            }

            val time = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            val outputData = Data.Builder()
                .putString(KEY_WORKER, currentDate)
                .build()

//            return Result.success()
            return Result.success(outputData)

        } catch (e: Exception) {
            return Result.failure()

        }
    }


}