package de.johnki.demoAndroidStore.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import de.johnki.demoAndroidFlow.R
import de.johnki.demoAndroidStore.images.loadUrl
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*
import javax.inject.Inject

class RestaurantDetailFragment : DaggerFragment() {

    private val args: RestaurantDetailFragmentArgs by navArgs()

    @Inject
    lateinit var factoryList: RestaurantDetailViewModelFactory

    private val viewModel: RestaurantDetailViewModel by lazy {
        ViewModelProvider(this, factoryList).get(RestaurantDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_restaurant_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(args.name)
        viewModel.restaurantList.observe(viewLifecycleOwner, Observer {
            restaurantName.text = it.name
            restaurantImage.loadUrl(it.presentationImage)
        })
    }
}
