package com.udacity.shoestore.ui.storelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentStorelistBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.ShoesViewModel

class StoreListFragment : Fragment() {
    private lateinit var binding: FragmentStorelistBinding

    private val shoesViewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_storelist, container, false)

        with(binding) {
            onAddFabClicked = View.OnClickListener { onAddFabClicked() }

            // Though the rubric mentioned to use a ScrollView with a vertical LinearLayout,
            // I thought it is better to use a RecyclerView instead because of performance
            // reasons. Also the code is a bit neat if we use them.
            recyclerView.adapter = StoreListAdapter()
        }

        shoesViewModel.shoes.observe(viewLifecycleOwner) {
            val adapter = binding.recyclerView.adapter as StoreListAdapter
            adapter.items = it
            adapter.itemClickHandler = this::onStoreItemClicked
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    private fun onAddFabClicked() {
        val navigateTo = StoreListFragmentDirections.toDetailsEditorFragment(null)
        findNavController().navigate(navigateTo)
    }

    private fun onStoreItemClicked(shoe: Shoe) {
        val navigateTo = StoreListFragmentDirections.toDetailsEditorFragment(shoe)
        findNavController().navigate(navigateTo)
    }
}