package com.gentebonita.gentebonita.gentebonita.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.math.BigDecimal;

import com.gentebonita.gentebonita.gentebonita.DescricaoPerfil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

//import com.gentebonita.gentebonita.gentebonita.FiltroActivity;
//import com.gentebonita.gentebonita.gentebonita.OfertaEspActivity;
import com.gentebonita.gentebonita.gentebonita.R;
import com.gentebonita.gentebonita.gentebonita.models.Perfil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinicius on 29/06/2017.
 */

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.MyViewHolder> {

    private List<Perfil> lista;
    private Activity mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nome, idade, nota;
        public ImageView imagem;
        public LinearLayout div,divbusca,divini;
        public MyViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.texto1);
            idade = (TextView) view.findViewById(R.id.texto2);
            nota = (TextView) view.findViewById(R.id.texto3);
            imagem = (ImageView) view.findViewById(R.id.imagem);
            div = (LinearLayout) view.findViewById(R.id.div);
            //divbusca = (LinearLayout) view.findViewById(R.id.divbusca);
            //divini = (LinearLayout) view.findViewById(R.id.divini);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(mContext, DescricaoPerfil.class);
                    Bundle b = new Bundle();
                    b.putInt("id", 1); //Your id
                    myIntent.putExtras(b); //Put your id to your next Intent
                    mContext.startActivity(myIntent);
                }
            });
        }
    }


    public PerfilAdapter(List<Perfil> lista,Activity context) {
        this.lista = lista;
        this.mContext = context;
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_perfil, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Perfil fid = lista.get(position);

            BigDecimal nota = new BigDecimal(fid.getNota());
            nota  = nota.setScale(2, BigDecimal.ROUND_HALF_UP);

            holder.nome.setText(fid.getNome());
            holder.idade.setText(fid.getIdade());
            holder.nota.setText(nota.toString());

        //holder.imagem.setImageResource(R.drawable.fotoslide1);

        String img1 = fid.getImagem();
        if(img1==null||img1.equals("")||img1.equals("null"))
        {
            Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.fotodefault);
            holder.imagem.setImageBitmap(getCircleBitmap(bm));
        }
        else
        {
            Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.fotoslide1);
            holder.imagem.setImageBitmap(getCircleBitmap(bm));
            /*ImageLoader imageLoader = ImageLoader.getInstance();
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .displayer(new RoundedBitmapDisplayer(150))
                    .cacheOnDisk(true).resetViewBeforeLoading(true)
                    .build();
            imageLoader.displayImage(img1, holder.imagem, options);*/
        }
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }

}
