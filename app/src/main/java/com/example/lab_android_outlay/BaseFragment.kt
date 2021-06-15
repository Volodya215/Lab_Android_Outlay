package com.example.lab_android_outlay

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab_android_outlay.data.LoginDataSource
import com.example.lab_android_outlay.databinding.FragmentBaseBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BaseFragment : Fragment(), SensorEventListener {

    private var _binding: FragmentBaseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sensorManager: SensorManager
    private lateinit var tempSensor: Sensor
    private var isTempSensorAvailable: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null)
        {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
            isTempSensorAvailable = true
        }
        else
        {
            binding.temperature.text = "Temperature sensor is not available"
            isTempSensorAvailable = false
        }

        binding.showHistoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.enterDataButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_enterDataFragment)
        }

        binding.setLimitsButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_setLimitsFragment)
        }

        binding.logOutButton.setOnClickListener {
            val loginData: LoginDataSource = LoginDataSource()
            loginData.logout()
            findNavController().navigate(R.id.action_FirstFragment_to_loginFragment)
        }

        (activity as BaseActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSensorChanged(event: SensorEvent?) {
        binding.temperature.text = event!!.values[0].toString() + "°C"
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        if(isTempSensorAvailable)
        {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        if(isTempSensorAvailable)
        {
            sensorManager.unregisterListener(this)
        }
    }
}