package me.eric.component.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.eric.basic.base.R;

public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialogStyle);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.loading_dialog, null);
        setContentView(view);
        setCancelable(false);
    }
}
