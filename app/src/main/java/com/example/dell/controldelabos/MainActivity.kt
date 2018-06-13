package com.example.dell.controldelabos

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar

import android.util.Log
import android.widget.AdapterView
import android.widget.Toast

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference


import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.RemoteMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_view_equipo.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val mDocRef:CollectionReference=FirebaseFirestore.getInstance().collection("laboratorios/Telematica/equipos")
    private lateinit var adapter:EquipoAdapter
    private lateinit var list: ArrayList<Equipo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setTitle("Telematica")
        list= arrayListOf()
        loadData()

        listView.onItemClickListener=AdapterView.OnItemClickListener { parent, view, position, id ->
            //Toast.makeText(this,list[position].codigo,Toast.LENGTH_LONG).show()
            val cod: String = list[position].codigo
            val ref: DocumentReference = FirebaseFirestore.getInstance().document("laboratorios/Telematica/equipos/" + cod)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Estado del PC")
            builder.setMessage("Marque una opcion")
            builder.setPositiveButton("Solucionado", { dialogInterface: DialogInterface, i: Int ->

                //Toast.makeText(this,"soluc",Toast.LENGTH_LONG).show()
                updateData(true,ref,position)

            })
            builder.setNegativeButton("Con falla", { dialogInterface: DialogInterface, i: Int ->

                //Toast.makeText(this,"falla",Toast.LENGTH_LONG).show()
                updateData(false,ref,position)

            })
            builder.show()
        }
    }
    fun loadData(){
        list.clear()
        mDocRef.get()
                .addOnCompleteListener {
                    list.clear()
                    if (it.isSuccessful) {
                        for (document: DocumentSnapshot in it.result) {
                            var  equipo:Equipo= Equipo(document.id,document.getBoolean("estado"))
                            list.add(equipo)
                        }
                        adapter=EquipoAdapter(this,R.layout.list_view_equipo,list)
                        listView.adapter=adapter
                    }
                }
    }


    fun updateData(estado:Boolean,ref:DocumentReference,position:Int) {
        ref.update("estado",estado)
                .addOnSuccessListener {
                    Toast.makeText(this,"UPDATED",Toast.LENGTH_LONG).show()
                }
        ref.addSnapshotListener { snapshot, firebaseFirestoreException ->
            loadData()
        }
    }
    fun onMessageReceived(msg:RemoteMessage)
    {

    }




}
