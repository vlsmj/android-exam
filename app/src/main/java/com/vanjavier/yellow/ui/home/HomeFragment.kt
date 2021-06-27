package com.vanjavier.yellow.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.ajalt.timberkt.d
import com.vanjavier.common.entities.Person
import com.vanjavier.network.service.Resource
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

        binding.run {
            // Observe the returned data to be applied to the adapter of the RecyclerView.
            viewModel.persons.observe(viewLifecycleOwner) {
                d {"Persons ${it.data}"}
                personsAdapter.submitList(it.data)

                checkViewsState(this, it)
            }
        }

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

    }

    /**
     * Redirect to the Profile fragment that displays details of the person.
     */
    private fun onClickPerson(person: Person) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}