package ir.majidkhosravi.mymap.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import ir.majidkhosravi.mymap.R


/**
 * This's a simple custom [Dialog] to show a  circle [android.widget.ProgressBar]
 * when our view state is in the [ir.majidkhosravi.domain.models.PureResult.Loading] situation.
 *
 */

class ProgressDialog(context: Context) : Dialog(context) {
    init {
        @SuppressLint("InflateParams")
        val inflate = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)
        setContentView(inflate)
        setCancelable(false)
        window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
    }
}