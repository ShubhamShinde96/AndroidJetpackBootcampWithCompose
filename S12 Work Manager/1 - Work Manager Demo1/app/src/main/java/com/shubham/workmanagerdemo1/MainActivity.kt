package com.shubham.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import com.shubham.workmanagerdemo1.UploadWorker.Companion.KEY_WORKER

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var button: Button

    companion object {
        const val KEY_COUNT_VALUE = "key_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        button.setOnClickListener {

            setOneTimeWorkRequest()
        }


        // In work manager we can sequentially and parallely chain different task

    }

    private fun setOneTimeWorkRequest() {

        val workManager = WorkManager.getInstance(applicationContext)

        val data: Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 125).build() // This is how we can pass the data to work if we need, otherwise we can skip this.

        val constraints = Constraints.Builder()
            .setRequiresCharging(true) // This is how we can add conditions to execute work, otherwise we can skip the constraints.
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build() // This is optional.

        //        workManager.enqueue(oneTimeWorkRequest)

        val uploadingRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()

        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()

        val downloadingRequest = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()

//        workManager.beginWith(filteringRequest)
//            .then(compressingRequest)
//            .then(uploadingRequest)
//            .enqueue()  // This is how we can use sequential chaining

        // When we're adding parallel works we have to first add them into the mutable list

        val parallelWorks = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadingRequest)
        parallelWorks.add(filteringRequest)


        workManager.beginWith(parallelWorks)
            .then(compressingRequest)
            .then(uploadingRequest)
            .enqueue()  // See the logcat and scroll to the top, you'll see the  filtering and downloading logs being mixed, this represents the parallel execution of works


        workManager.getWorkInfoByIdLiveData(uploadingRequest.id)
            .observe(this@MainActivity, Observer {
                textView.text = it.state.name

                if(it.state.isFinished) {
                    val data = it.outputData
                    val message = data.getString(KEY_WORKER)

                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                }
            })

    }

}