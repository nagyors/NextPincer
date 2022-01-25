package hf.mobweb.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.opengl.ETC1.isValid
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hf.mobweb.R
import hf.mobweb.data.Menu
import hf.mobweb.databinding.DialogNewFoodBinding

class NewFoodDialogFragment :DialogFragment() {
    interface NewFoodDialogListener {
        fun onFoodCreated(newItem: Menu)
    }

    private lateinit var listener: NewFoodDialogListener
    private lateinit var binding: DialogNewFoodBinding
    lateinit var restIDstring: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewFoodDialogFragment.NewFoodDialogListener
            ?: throw RuntimeException("Activity must implement the NewFoodDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewFoodBinding.inflate(LayoutInflater.from(context))

        val bundle = arguments
        restIDstring = bundle!!.getString("restID", "1")

        binding.spType.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.food_types)
        )

        return AlertDialog.Builder(requireContext())
            .setTitle("New Food")
            .setView(binding.root)
            .setPositiveButton("OK") { dialogInterface, i ->
                if (isValid()) {
                    listener.onFoodCreated(getFood())
                }

            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    private fun isValid() = binding.etName.text.isNotEmpty()

    companion object {
        const val TAG = "NewFoodDialogFragment"
    }

    private fun getFood() = Menu(
        name = binding.etName.text.toString(),
        price = Integer.parseInt(binding.etPrice.text.toString()),
        description = binding.etDescription.text.toString(),
        type = getType(binding.spType.selectedItem.toString()),
        restaurantID = restIDstring.toLong()
    )

    private fun getType(type: String): Menu.Type{
        return when(type){
            "Starter" -> Menu.Type.STARTER
            "Soup" -> Menu.Type.SOUP
            "Main course" -> Menu.Type.MAINCOURSE
            "Desert" -> Menu.Type.DESERT
            "Drink" -> Menu.Type.DRINKS
            else -> Menu.Type.STARTER
        }
    }

}