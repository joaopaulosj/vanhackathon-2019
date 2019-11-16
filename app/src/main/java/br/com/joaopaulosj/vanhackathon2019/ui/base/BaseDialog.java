package br.com.joaopaulosj.vanhackathon2019.ui.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialog;
import android.view.Window;
import android.widget.LinearLayout;

public abstract class BaseDialog extends AppCompatDialog{

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    public BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void setLayoutToMatchWindowSize() {
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }
}
