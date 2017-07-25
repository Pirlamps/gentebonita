package com.gentebonita.gentebonita.gentebonita;

/**
 * Created by Vinicius on 19/06/2017.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncoesBasicas {

    public FuncoesBasicas() {
    }

    public Barcode.GeoPoint getLocationFromAddress(String strAddress, Context ctx){

        Geocoder coder = new Geocoder(ctx);
        List<Address> address;
        Barcode.GeoPoint p1 = null;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new Barcode.GeoPoint(1,location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {

        }
        return p1;
    }
    public void mensagemerro(String texto1, String texto2, Context ct1)
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(ct1).create();
        alertDialog.setTitle(texto1);
        alertDialog.setMessage(texto2);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }
    public String transfdata(String data)
    {
        String dataf = "";
        if(data.equals("")||data.equals(false)||data.equals("null")||data.isEmpty())        return dataf;
        String[] dataaux = data.split(" ");
        String[] dataaux2 = dataaux[0].split("-");
        dataf = dataaux2[2]+"-"+dataaux2[1]+"-"+dataaux2[0];
        return dataf;
    }

    public String transfdataface(String data)
    {
        String dataf = "";
        if(data.equals("")||data.equals(false)||data.equals("null")||data.isEmpty())        return dataf;
        String[] dataaux = data.split(" ");
        String[] dataaux2 = dataaux[0].split("-");
        dataf = dataaux2[2]+"-"+dataaux2[1]+"-"+dataaux2[0];
        return dataf;
    }

    public String hashinvertido(Map<String, Integer> itemsaux, Integer valor)
    {
        String retorno = "";
        if(valor.equals("")||valor.equals(false)||valor.equals(0))        return retorno;
        for (Map.Entry<String, Integer> e : itemsaux.entrySet()) {
            if( e.getValue().equals(valor) )
            {
                retorno = e.getKey();
            }
        }
        return retorno;
    }
}
