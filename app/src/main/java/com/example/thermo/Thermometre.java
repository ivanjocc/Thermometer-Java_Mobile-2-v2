package com.example.thermo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Thermometre extends View {

    private float tempEnDegC;
    private String unit;

    public Thermometre(Context context) {
        super(context);
        this.tempEnDegC = 20.0f;
        this.unit = "C";
    }

    public Thermometre(Context context, float temp) {
        super(context);
        this.tempEnDegC = temp;
        this.unit = "C";
    }

    public Thermometre(Context context, float temp, String unit) {
        super(context);
        this.tempEnDegC = temp;
        this.unit = unit;
    }

    public void setTemp(float temp) {
        this.tempEnDegC = temp;
        invalidate();
    }

    public void setUnit(String unit) {
        this.unit = unit;
        invalidate();
    }

    private float celsiusToFahrenheit(float celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private float celsiusToKelvin(float celsius) {
        return celsius + 273.15f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float tempMin = 0f;
        float tempMax = 100f;
        float tempProportion = (tempEnDegC - tempMin) / (tempMax - tempMin);

        int red = (int) (255 * tempProportion);
        int blue = (int) (255 * (1 - tempProportion));
        int green = 0;

        switch (unit) {
            case "C":
                drawEchelleCelcius(canvas);
                break;
            case "F":
                drawEchelleFahrenheit(canvas);
                break;
            case "K":
                drawEchelleKelvin(canvas);
                break;
        }

        Paint paint = new Paint();
        paint.setColor(Color.rgb(red, green, blue));

        float drawableHeight = this.getHeight() - 120f;
        float tempPosition = (tempEnDegC - tempMin) / (tempMax - tempMin) * drawableHeight;

        canvas.drawLine((this.getWidth() / 2.0f), this.getHeight() - 60.0f - tempPosition, (this.getWidth() / 2.0f), this.getHeight() - 60.0f, paint);
        canvas.drawCircle((this.getWidth() / 2.0f), this.getHeight() - 60.0f, 60.0f, paint);
    }

    private void drawEchelleCelcius(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(30);

        float drawableHeight = this.getHeight() - 120f;

        float tempMin = 0f;
        float tempMax = 100f;

        for (int temp = 0; temp <= 100; temp += 10) {
            float proportion = (float) temp / (tempMax - tempMin);
            float yPos = this.getHeight() - 60f - (proportion * drawableHeight);

            canvas.drawLine((this.getWidth() / 2.0f) - 20, yPos, (this.getWidth() / 2.0f) + 20, yPos, paint);
            canvas.drawText(String.valueOf(temp), (this.getWidth() / 2.0f) + 25, yPos + 10, paint);
        }

        float tempPosition = (tempEnDegC - tempMin) / (tempMax - tempMin) * drawableHeight;
        canvas.drawLine((this.getWidth() / 2.0f), this.getHeight() - 60.0f - tempPosition, (this.getWidth() / 2.0f), this.getHeight() - 60.0f, paint);
        canvas.drawCircle((this.getWidth() / 2.0f), this.getHeight() - 60.0f, 60.0f, paint);
    }

    private void drawEchelleFahrenheit(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(30);

        float tempMinF = celsiusToFahrenheit(0);
        float tempMaxF = celsiusToFahrenheit(100);
        float drawableHeight = this.getHeight() - 120f;

        for (int temp = 32; temp <= 212; temp += 18) {
            float proportion = (float) (temp - 32) / (212 - 32);
            float yPos = this.getHeight() - 60f - (proportion * drawableHeight);

            canvas.drawLine((this.getWidth() / 2.0f) - 20, yPos, (this.getWidth() / 2.0f) + 20, yPos, paint);
            canvas.drawText(String.valueOf(temp), (this.getWidth() / 2.0f) + 25, yPos + 10, paint);
        }

        float tempF = celsiusToFahrenheit(tempEnDegC);
        float tempPositionF = (tempF - tempMinF) / (tempMaxF - tempMinF) * drawableHeight;
        canvas.drawLine((this.getWidth() / 2.0f), this.getHeight() - 60.0f - tempPositionF, (this.getWidth() / 2.0f), this.getHeight() - 60.0f, paint);
        canvas.drawCircle((this.getWidth() / 2.0f), this.getHeight() - 60.0f, 60.0f, paint);
    }

    private void drawEchelleKelvin(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(30);

        float tempMinK = celsiusToKelvin(0);
        float tempMaxK = celsiusToKelvin(100);
        float drawableHeight = this.getHeight() - 120f;

        for (int temp = 273; temp <= 373; temp += 10) {
            float proportion = (float) (temp - 273) / (373 - 273);
            float yPos = this.getHeight() - 60f - (proportion * drawableHeight);

            canvas.drawLine((this.getWidth() / 2.0f) - 20, yPos, (this.getWidth() / 2.0f) + 20, yPos, paint);
            canvas.drawText(String.valueOf(temp), (this.getWidth() / 2.0f) + 25, yPos + 10, paint);
        }

        float tempK = celsiusToKelvin(tempEnDegC);
        float tempPositionK = (tempK - tempMinK) / (tempMaxK - tempMinK) * drawableHeight;
        canvas.drawLine((this.getWidth() / 2.0f), this.getHeight() - 60.0f - tempPositionK, (this.getWidth() / 2.0f), this.getHeight() - 60.0f, paint);
        canvas.drawCircle((this.getWidth() / 2.0f), this.getHeight() - 60.0f, 60.0f, paint);
    }
}