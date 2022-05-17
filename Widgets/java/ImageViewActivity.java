package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageViewActivity extends AppCompatActivity {
    // Arreglo de ids de las imagenes
    private int idImgArr[] = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};
    // Indice actual del arreglo de las imagenes
    private int indiceImgActual = 0;
    // Valor de la transparencia de la imagen, su valor Maximo es 1.0f, el Minimo es 0.0f
    private  float alphaImagen = 1.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        // Se define el objeto tipo imageView que contiene a la imagen por defecto
        final ImageView imageViewSource = findViewById(R.id.imageViewFuente);
        imageViewSource.setScaleType(ImageView.ScaleType.CENTER);
        // Se establecen el ajuste de la imagen en los limites de la vista
        imageViewSource.setAdjustViewBounds(true);
        // Se establece la imagen por defecto.
        imageViewSource.setImageResource(idImgArr[indiceImgActual]);

        // Boton para seleccionar la siguiente imagen.
        Button nextImageButton = findViewById(R.id.btnSigImagen);
        nextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indiceImgActual==5)
                {
                    indiceImgActual = -1;
                }
                indiceImgActual++;
                imageViewSource.setImageResource(idImgArr[indiceImgActual]);
            }
        });

        // Boton para el escalado por Matriz
        // Escala la imagen por medio de una matriz
        Button matrixImageButton = findViewById(R.id.btnMatImagen);
        matrixImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewSource.setScaleType(ImageView.ScaleType.MATRIX);
            }
        });
        // Boton de ajuste a lo largo de X y Y
        // Escala la imagen en horizontal y vertical. Esto permite que se adapte por completo
        // al ImageView pero puede que el radio del aspecto puede cambiar.
        Button fitXYImageButton = findViewById(R.id.btnAjustarXY);
        fitXYImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewSource.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });
        // Boton de ajuste de escalado en Start.
        // Posiciona la imagen a la izquierda y arriba
        Button fitStartImageButton = findViewById(R.id.btnAjusteStart);
        fitStartImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewSource.setScaleType(ImageView.ScaleType.FIT_START);
            }
        });
        // Boton para el ajuste del escalado al centro
        // Posiciona la imagen en el centro del ImageView
        Button fitCenterImageButton = findViewById(R.id.btnAjustarCenter);
        fitCenterImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewSource.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        });
        // Boton de escalado para el ajuste a END
        // Posiciona la imagen en la parte baja de la derecha
        Button fitEndImageButton = findViewById(R.id.btnAjustarEnd);
        fitEndImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewSource.setScaleType(ImageView.ScaleType.FIT_END);
            }
        });
        // Boton de escalado CENTER
        // Posiciona la imagen en el centro del ImageView
        Button centerImageButton = findViewById(R.id.btnCentroImagen);
        centerImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewSource.setScaleType(ImageView.ScaleType.CENTER);
            }
        });
        /*
         Boton para escalado haciendo recorte
         Hace zoom a la imagen y mantiene el radio de aspecto hasta que la imagen cubre
         todo el ImageView
        */
        Button centerCorpImageButton = findViewById(R.id.btnCropImagen);
        centerCorpImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewSource.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        });
        // Boton para escalado dentro del centro
        // Coloca la imagen en el centro del Image View sin hacer escalado
        Button centerInsideImageButton = findViewById(R.id.btnCentrarDentroImagen);
        centerInsideImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewSource.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        });
        // Boton para el incremento de la transparencia
        Button increaseImageTransparencyButton = findViewById(R.id.btnIncTransparencia);
        increaseImageTransparencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alphaImagen > 1)
                {
                    alphaImagen = 1;
                }
                alphaImagen += 0.1;
                imageViewSource.setAlpha(alphaImagen);
            }
        });
        // Boton para el decremento de la transparencia
        Button decreaseImageTransparencyButton = findViewById(R.id.btnDecTransparencia);
        decreaseImageTransparencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alphaImagen < 0)
                {
                    alphaImagen = 0;
                }
                alphaImagen -= 0.1;
                imageViewSource.setAlpha(alphaImagen);
            }
        });
    }
}