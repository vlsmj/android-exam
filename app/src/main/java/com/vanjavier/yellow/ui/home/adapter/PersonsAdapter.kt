package com.vanjavier.yellow.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vanjavier.common.entities.Person
import com.vanjavier.yellow.databinding.ItemPersonBinding

/**
 * The adapter class to be used in a RecyclerView for
 * getting the list of persons.
 */
class PersonsAdapter :
    ListAdapter<Person, PersonsAdapter.PersonViewHolder>(PersonComparator()) {

    /**
     * On click listener for an item in the list
     *
     * @return Return the selected person model from the list.
     */
    lateinit var onClickPerson: (person: Person) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {
        return PersonViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**
     * This is the ViewHolder of the adapter that binds the data to
     * the corresponding views.
     */
    inner class PersonViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            binding.apply {

            }
        }
    }

    override fun onBindViewHolder(
        holder: PersonViewHolder,
        position: Int
    ) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it) }
    }

    /**
     * Compare data and contents to prevent the repeating of items shown
     * in the list.
     */
    class PersonComparator : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Person, newItem: Person) =
            oldItem == newItem
    }
}