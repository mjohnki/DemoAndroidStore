package de.johnki.demoAndroidStore.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import dagger.android.support.DaggerFragment
import de.johnki.demoAndroidFlow.R
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import javax.inject.Inject

class RestaurantListFragment : DaggerFragment() {

    @Inject
    lateinit var factoryList: RestaurantListViewModelFactory

    private val listViewModel: RestaurantListViewModel by lazy {
        ViewModelProvider(this, factoryList).get(RestaurantListViewModel::class.java)
    }

    private val adapter =
        RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_restaurant_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantList.adapter = adapter
        adapter.listener = {
            val action =
                RestaurantListFragmentDirections.toRestaurantDetailFragment(it)
            view.findNavController().navigate(action)

        }
        listViewModel.init()
        listViewModel.restaurantList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}
