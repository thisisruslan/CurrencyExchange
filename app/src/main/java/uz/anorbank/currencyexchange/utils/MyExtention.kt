package uz.anorbank.currencyexchange.utils

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showToast(msg: String?) {
    Toast.makeText(this.requireContext(), msg, Toast.LENGTH_LONG).show()
}