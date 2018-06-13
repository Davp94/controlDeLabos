package com.example.dell.controldelabos

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.list_view_equipo.view.*

class EquipoAdapter(val context: Context, val layout: Int, var list: ArrayList<Equipo>) : BaseAdapter() {

    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
      fun reemplazar(position: Int,reemp:Equipo){

          notifyDataSetChanged()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: EquipoViewHolder

        if (convertView==null){
            view = mInflator.inflate(layout,parent,false)
            viewHolder =EquipoViewHolder(view)
            view.tag=viewHolder
        }else{
            view = convertView
            viewHolder =view.tag as EquipoViewHolder
        }

        val fullName = "${list[position].codigo}"
        val estado:Boolean = list[position].estado
        viewHolder.codigo.text =fullName
        if(estado.equals(true))
        {
            viewHolder.estado.setBackgroundResource(R.drawable.ic_check_blank_24dp)
            viewHolder.contenedor.setBackgroundResource(R.color.colorAlert)
        }else{
            viewHolder.estado.setBackgroundResource(R.drawable.ic_warning_blank_24dp)
            viewHolder.contenedor.setBackgroundResource(R.color.colorPrimary)
        }


        return view
    }
    private class EquipoViewHolder(view: View){
        val codigo: TextView =view.textViewCodigo
        val estado: ImageView=view.imgViewEstado
        val contenedor: LinearLayout=view.container
    }
}