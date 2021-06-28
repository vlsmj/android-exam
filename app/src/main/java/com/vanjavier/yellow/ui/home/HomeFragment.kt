package com.vanjavier.yellow.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanjavier.common.entities.Person
import com.vanjavier.network.service.Resource
import com.vanjavier.util.extensions.setActionBarTitle
import com.vanjavier.yellow.BuildConfig
import com.vanjavier.yellow.databinding.FragmentHomeBinding
import com.vanjavier.yellow.ui.home.adapter.PersonsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    /**
     * Create an instance of the adapter to be attached to the RecyclerView.
     */
    private val personsAdapter = PersonsAdapter()

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.apply {

            // Prepare the adapter and layout for the RecyclerView.
            recyclerView.apply {
                adapter = personsAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            // Observe the returned data to be applied to the adapter of the RecyclerView.
            viewModel.persons.observe(viewLifecycleOwner) {
                personsAdapter.submitList(it.data)

                checkViewsState(this, it)
            }
        }

        personsAdapter.onClickPerson = ::onClickPerson

        return binding.root
    }

    /**
     * Manage the views state into one function. This observes to the process of the
     * fetching and saving of data.
     *
     * @param binding The view binding of the fragment.
     * @param result The Resource state with the returned data.
     */
    private fun checkViewsState(binding: FragmentHomeBinding, result: Resource<List<Person>>) {
        binding.apply {
            progressBar.isVisible = result is Resource.Loading
            recyclerView.isVisible = result !is Resource.Loading && !result.data.isNullOrEmpty()
        }
    }

    /**
     * Redirect to the Profile fragment that displays the details of the person selected.
     */
    private fun onClickPerson(person: Person) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToNavigationProfile(
                person
            )
        )
    }

    override fun onResume() {
        super.onResume()
        setActionBarTitle(BuildConfig.APP_ENVIRONMENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}