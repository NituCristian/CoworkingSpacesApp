package com.example.coworkingspaces.qr_based_access

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import com.example.coworkingspaces.R
import com.example.coworkingspaces.other_activities.user_account.UserInfoSingleton
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.lang.Exception

class QrScanActivity : AppCompatActivity() {


    private val requestCodeCameraPosition = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var detector: BarcodeDetector

    private var scanSuccessful = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        scanSuccessful = 1
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scan)

        if(ContextCompat.checkSelfPermission(
                this@QrScanActivity,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
        ) {
            askForCameraPosition()
        } else {
            setupControls()
        }
    }

    private fun setupControls() {

        var cameraSurface = findViewById<SurfaceView>(R.id.cameraSurfaceView)
        detector = BarcodeDetector.Builder(this@QrScanActivity).build()
        cameraSource = CameraSource.Builder(this@QrScanActivity, detector)
            .setAutoFocusEnabled(true)
            .build()

        cameraSurface.holder.addCallback(surfaceCallback)
        detector.setProcessor(processor)

    }

    private fun askForCameraPosition() {
        ActivityCompat.requestPermissions(
            this@QrScanActivity,
            arrayOf(Manifest.permission.CAMERA),
            requestCodeCameraPosition
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == requestCodeCameraPosition && grantResults.isNotEmpty()) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupControls()
            } else {
                Toast.makeText(applicationContext, "Permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val surfaceCallback = object: SurfaceHolder.Callback {

        @SuppressLint("MissingPermission")
        override fun surfaceCreated(holder: SurfaceHolder) {
            try {
                cameraSource.start(holder)
            } catch (exception: Exception) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            cameraSource.stop()
        }

    }

    private val processor = object : Detector.Processor<Barcode> {
        override fun release() {

        }

        override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
            var textResult = findViewById<TextView>(R.id.textScanResult)

            if (detections != null && detections.detectedItems.isNotEmpty()) {
                var qrCodes: SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)

                if(!(code.displayValue == "https://me-qr.com/4200" ||
                            code.displayValue == "https://me-qr.com/4199"||
                            code.displayValue == "https://me-qr.com/4198")) {
                    textResult.text = "Invalid code! This is not your seat!"
                }
                //textResult.text = code.displayValue

                if (    ((code.displayValue == "https://me-qr.com/4200" && UserInfoSingleton.name == "Andrei") ||
                            (code.displayValue == "https://me-qr.com/4199" && UserInfoSingleton.name == "Andrada")||
                            (code.displayValue == "https://me-qr.com/4198" && UserInfoSingleton.name == "Denisa"))
                            && scanSuccessful == 1) {

                    val runnable = Runnable {
                        val intent =
                            Intent(this@QrScanActivity, QrScanSuccessfulActivity::class.java)

                        startActivity(intent)
                        scanSuccessful = 2
                    }

                    Handler(Looper.getMainLooper()).postDelayed(runnable, 1000);
                } else {
                    textResult.text = "Invalid code! This is not your seat!"

                    if(detections!=null && !detections.detectedItems.isNotEmpty()){
                        textResult.text = "Some msg"
                    }

                   // if(detections.detectedItems.valueAt(0).displayValue == "https://me-qr.com/4199"){
                     //   textResult.text = "Some msg2"
                    //}

                }


            }
        }
    }
}
