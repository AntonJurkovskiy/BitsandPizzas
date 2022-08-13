package com.azimut4946777.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.azimut4946777.bitsandpizzas.databinding.FragmentOrderBinding
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class OrderFragment : Fragment() {
    private var _binding:  FragmentOrderBinding?=null
    private val binding get() = _binding!!

    companion object {
        const val UNDEFINED_ID = -1
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentOrderBinding.inflate(inflater,container,false)
        val view = binding.root

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener {
            val pizzaType = binding.pizzaGroup.checkedRadioButtonId
            if (pizzaType == UNDEFINED_ID) {
                val text = "Вам треба  щось вибрати"
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
            } else {
                var text = (when (pizzaType) {
                    R.id.radio_diavolo -> "Водопійська із зеленню"
                    else -> "Піца з Північного, гостра"
                })
                text += if (binding.parmesan.isChecked) ", додано Parmesan" else ""
                text += if (binding.chiliOil.isChecked) ", додано Chili Oil" else ""
                Snackbar.make(binding.fab, text, Snackbar.LENGTH_SHORT)
                    .setAction("Скасувати") {
                        val text = "Заказ Скасовано"
                        pizzaType== UNDEFINED_ID
                        Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()
                    }
                    .show()
               }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}