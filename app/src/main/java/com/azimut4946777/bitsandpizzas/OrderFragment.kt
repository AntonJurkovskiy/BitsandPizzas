package com.azimut4946777.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class OrderFragment : Fragment() {
    companion object {
        const val UNDEFINED_ID = -1
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        val toolBar = view.findViewById<MaterialToolbar>(R.id.toolbar)

        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val pizzaGroup = view.findViewById<RadioGroup>(R.id.pizza_group)
            val pizzaType = pizzaGroup.checkedRadioButtonId
            if (pizzaType == UNDEFINED_ID) {
                val text = "Вам треба  щось вибрати"
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
            } else {
                var text = (when (pizzaType) {
                    R.id.radio_diavolo -> "Водопійська із зеленню"
                    else -> "Піца з Північного, гостра"
                })
                val parmesan = view.findViewById<Chip>(R.id.parmesan)
                text += if (parmesan.isChecked) ", додано Parmesan" else ""
                val chiliOil = view.findViewById<Chip>(R.id.chili_oil)
                text += if (chiliOil.isChecked) ", додано Chili Oil" else ""
                Snackbar.make(fab, text, Snackbar.LENGTH_SHORT)
                    .setAction("Відмініти") {
                        val text = "Заказ Відмінено"

                        Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()
                    }
                    .show()
               }
        }
        return view
    }


}