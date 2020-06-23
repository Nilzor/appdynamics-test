package com.example.appdyntest

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.appdynamics.eumagent.runtime.AgentConfiguration
import com.appdynamics.eumagent.runtime.Instrumentation
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Instrumentation.start(
            AgentConfiguration.builder()
                .withAppKey("TODO-APP-GROUP-ID-HERE")
                .withApplicationName(BuildConfig.APPLICATION_ID)
                .withContext(this.applicationContext)
                .withLoggingEnabled(true)
                .withLoggingLevel(Instrumentation.LOGGING_LEVEL_VERBOSE)
                .withAutoInstrument(true)
                .withCompileTimeInstrumentationCheck(true)
                .withCollectorURL("https://fra-col.eum-appdynamics.com")
                .withScreenshotURL("https://fra-image.eum-appdynamics.com")
                .build()
        )
        Instrumentation.reportMetric("launch", 1)

        findViewById<Button>(R.id.requestButton).setOnClickListener { onRequestButtonClick() }
    }

    fun onRequestButtonClick() {
        AsyncTask.execute {
            val res: Response<String> = DummyService.getInstance().doGet().execute()
            Log.d("ZZZ", "Got code ${res.code()}")
        }
    }
}