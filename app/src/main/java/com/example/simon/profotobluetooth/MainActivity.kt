package com.example.simon.profotobluetooth

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    var bluetoothAdapter: BluetoothAdapter? = null
    lateinit var bluetoothDevice: BluetoothDevice
    val REQUEST_ENABLE_BLUETOOTH = 1

    companion object {
        val EXTRA_ADRESS: String = "Device_adress"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)

        val devices = ArrayList<Device>()

        devices.add(Device("kalle","123",2))
        devices.add(Device("kalle","123",2))
        devices.add(Device("kalle","123",2))

        val adapter = CustomAdapter(devices)

        recyclerView.adapter = adapter

        if(bluetoothAdapter == null ){
            toast("This device does not support bluetooth")
            return
        }
        if(!bluetoothAdapter!!.isEnabled){
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBluetoothIntent,REQUEST_ENABLE_BLUETOOTH)
        }

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val action = intent.getAction()
                Log.d("KALLE123","Action = " + action )
            }
        }

        val intentFilter: IntentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver,intentFilter)

        if(bluetoothAdapter!!.isDiscovering) {
            bluetoothAdapter!!.cancelDiscovery()
        }
        bluetoothAdapter!!.startDiscovery()

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_ENABLE_BLUETOOTH){
            if(requestCode == Activity.RESULT_OK) {
                if(bluetoothAdapter!!.isEnabled){
                    toast("Bluetooth enabled")
                }else{
                    toast("bluetooth disabled")
                }
            }
        }else if(resultCode == Activity.RESULT_CANCELED){
            toast("")
        }
    }
}
