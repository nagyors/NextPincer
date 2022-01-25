package hf.mobweb.fragments

import android.app.Dialog
import android.content.Context
import android.opengl.ETC1.isValid
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hf.mobweb.R
import hf.mobweb.data.Restaurant
import hf.mobweb.databinding.DialogNewRestaurantBinding

class NewRestaurantDialogFragment : DialogFragment()  {
    interface NewRestaurantDialogListener {
        fun onRestaurantCreated(newItem: Restaurant)
    }

    private lateinit var listener: NewRestaurantDialogListener
    private lateinit var binding: DialogNewRestaurantBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewRestaurantDialogListener
            ?: throw RuntimeException("Activity must implement the NewFoodDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewRestaurantBinding.inflate(LayoutInflater.from(context))
        binding.spCuisine.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.cuisine_items)
        )

        binding.spPricing.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.pricing_items)
        )


        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_restaurant)
            .setView(binding.root)
            .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                if (isValid()) {
                    listener.onRestaurantCreated(getRestaurant())
                }
            }
            .setNegativeButton(R.string.button_cancel, null)
            .create()
    }

    companion object {
        const val TAG = "NewRestaurantDialogFragment"
    }

    private fun isValid() = binding.etName.text.isNotEmpty() && ratingValid()

    private fun ratingValid(): Boolean{
        if(binding.etRating.text.toString().toFloat() in 0.0..5.0)
            return true
        return false
    }

    private fun getRestaurant() = Restaurant(
        name = binding.etName.text.toString(),
        cuisine = binding.spCuisine.selectedItem.toString(),
        address = binding.etAddress.text.toString(),
        pricing = binding.spPricing.selectedItem.toString(),
        rating = binding.etRating.text.toString().toFloat()
    )

}