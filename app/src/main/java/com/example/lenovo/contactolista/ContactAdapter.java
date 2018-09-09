package com.example.lenovo.contactolista;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    ArrayList<Contact> contacts;
    Activity activity;

    public ContactAdapter (Activity activity){
        this.activity =activity;
        contacts =new ArrayList<>();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();

        View renglon = inflater.inflate(R.layout.renglon, null,false);
        ImageView iv_genero = renglon.findViewById(R.id.iv_genero);
        if (contacts.get(position).getGenero().equals("femenino")){
            iv_genero.setImageResource(R.drawable.mujer);
        }else {
            iv_genero.setImageResource(R.drawable.hombre);
        }
        TextView tv_nombre = renglon.findViewById(R.id.tv_nombre);
        TextView tv_telefono = renglon.findViewById(R.id.tv_telefono);
        ImageButton ib_call = renglon.findViewById(R.id.ib_call);
        ImageButton ib_delete = renglon.findViewById(R.id.ib_delete);
        tv_nombre.setText(contacts.get(position).getNombre());
        tv_telefono.setText(contacts.get(position).getTelefono());

        ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts.remove(position);
                notifyDataSetChanged();

            }
        });

        ib_call.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("Num"+ contacts.get(position).getTelefono()));

                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 222);
                }else {
                    activity.startActivity(call);
                }
            }
        });
        return renglon;

    }
    public void agregarContacto(Contact contact){
        contacts.add(contact);
        notifyDataSetChanged();
    }
}
