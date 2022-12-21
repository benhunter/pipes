package me.benhunter.pipes.ui.groups

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import me.benhunter.pipes.databinding.FragmentGroupsBinding

class GitlabGroupsFragment : Fragment() {

    private val gitlabGroupsViewModel: GitlabGroupsViewModel by activityViewModels()

    private var _binding: FragmentGroupsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.getPreferences(Context.MODE_PRIVATE)?.let {
            gitlabGroupsViewModel.loadAccount(it)
        }

        val gitlabGroupsAdapter = GitlabGroupsAdapter(layoutInflater, ::navToGitlabGroup)
        binding.gitlabGroupsRecyclerview.adapter = gitlabGroupsAdapter

        gitlabGroupsViewModel.observeGitlabGroups().observe(this.viewLifecycleOwner) {
            gitlabGroupsAdapter.submitList(it)
        }

        gitlabGroupsViewModel.fetchGroups()
    }

    private fun navToGitlabGroup(groupId: String) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}