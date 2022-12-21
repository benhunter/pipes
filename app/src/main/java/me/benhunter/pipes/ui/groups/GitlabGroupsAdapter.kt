package me.benhunter.pipes.ui.groups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.benhunter.pipes.GitlabGroup
import me.benhunter.pipes.databinding.GitlabGroupBinding

class GitlabGroupsAdapter(layoutInflater: LayoutInflater, navToGitlabGroup: (groupId: String)->Unit): ListAdapter<GitlabGroup, GitlabGroupsAdapter.ViewHolder>(Diff()) {

    inner class ViewHolder(val groupBinding: GitlabGroupBinding): RecyclerView.ViewHolder(groupBinding.root)

    class Diff : DiffUtil.ItemCallback<GitlabGroup>(){
        override fun areItemsTheSame(oldItem: GitlabGroup, newItem: GitlabGroup): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: GitlabGroup, newItem: GitlabGroup): Boolean {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
