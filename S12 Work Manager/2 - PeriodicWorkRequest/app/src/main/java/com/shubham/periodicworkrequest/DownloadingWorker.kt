package com.shubham.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DownloadingWorker(context: Context, params: WorkerParameters): Worker(context, params) {


    override fun doWork(): Result {

        try {

            for (i in 0..3000) {

                Log.i("MY_TAG", "Downloading $i")
            }

            val time = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            val outputData = Data.Builder()
                .putString(UploadWorker.KEY_WORKER, currentDate)
                .build()

            Log.i("MY_TAG", "Completed: $currentDate")

            return Result.success()

        } catch (e: Exception) {

            return Result.failure()
        }
    }


}