package com.vanjavier.yellow.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.vanjavier.util.extensions.getAge
import com.vanjavier.yellow.R
import com.vanjavier.yellow.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    /**
     * Instantiate the arguments passed from the navigation actions.
     */
    private val args: ProfileFragmentArgs by navArgs()

    private var _binding: FragmentProfileBinding? = null

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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val person = args.person

        val imageResource: Int = when (person.gender) {
            "male" -> R.drawable.ic_male
            else -> R.drawable.ic_female
        }

        binding.apply {
            imgAvatar.setImageResource(imageResource)

            person.apply {
                val completeAddress = address?.run {
                    "$building $street, $state, $city, $zip, $country"
                }

                val contactPersonFullName =
                    "${contactPerson?.firstName} ${contactPerson?.lastName}"

                txtFirstName.text = firstName
                txtLastName.text = lastName
                txtBirthDay.text = birthDay
                txtAge.text = birthDay?.getAge().toString()
                txtEmailAddress.text = email
                txtMobileNumber.text = mobile
                txtAddress.text = completeAddress
                txtContactPerson.text = contactPersonFullName
                txtContactPersonPhoneNumber.text = contactPerson?.mobile
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}