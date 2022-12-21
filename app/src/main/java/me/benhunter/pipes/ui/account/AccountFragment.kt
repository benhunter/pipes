package me.benhunter.pipes.ui.account

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import me.benhunter.pipes.Account
import me.benhunter.pipes.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val accountViewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.getPreferences(Context.MODE_PRIVATE)?.let {
            accountViewModel.loadAccount(it)
        }

        accountViewModel.account().observe(viewLifecycleOwner) {
            binding.textInputServer.editText?.setText(it.server)
            binding.textInputToken.editText?.setText(it.token)
        }

        binding.saveAccountButton.setOnClickListener {
            val server = binding.textInputServer.editText?.text.toString()
            val token = binding.textInputToken.editText?.text.toString()
            val account = Account(server, token)
            try {
                accountViewModel.setAccount(account)
            } catch (e:Exception) {
                Log.d(javaClass.name, "exception setting account")
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}