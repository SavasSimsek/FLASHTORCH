import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }


    }


        private fun takePhoto() {}


        private fun startCamera() {


            

            val toggle: ToggleButton = findViewById(R.id.toggleButton)



            val cameraProviderFuture = ProcessCameraProvider.getInstance(this)


                
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()


                
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            var imageCapture: ImageCapture = ImageCapture.Builder()
                .setFlashMode(ImageCapture.FLASH_MODE_ON)
                .build()



            val camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, imageCapture
                )


            toggle.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {


                        if (camera.cameraInfo.hasFlashUnit()) {
                            camera.cameraControl.enableTorch(true)
                           

                        }


                    } else {



                        if (camera.cameraInfo.hasFlashUnit()) {
                            camera.cameraControl.enableTorch(false)
                            
                        }

                    }
                }



        }

        private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                baseContext, it
            ) == PackageManager.PERMISSION_GRANTED
        }



    companion object {

        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

}
