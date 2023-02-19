package com.jdr.pruebaceiba.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jdr.pruebaceiba.databinding.FragmentPostBinding
import com.jdr.pruebaceiba.ui.dialog.DialogLoading
import com.jdr.pruebaceiba.ui.post.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostViewModel by viewModels()

    private lateinit var adapter: PostAdapter
    private lateinit var loadingDialog: DialogLoading

    private val args: PostFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        loadingDialog = DialogLoading(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
        observers()
    }

    private fun setup() {
        adapter = PostAdapter()
        with(binding) {
            rvPublication.adapter = adapter
            rvPublication.layoutManager = LinearLayoutManager(context)
        }

        viewModel.getUser(args.userId)
        viewModel.getPosts(args.userId)

        binding.iPerson.tvSeePublication.visibility = View.GONE
    }

    private fun observers() {
        with(viewModel) {
            user.observe(viewLifecycleOwner) { user ->
                with(binding.iPerson) {
                    tvNamePerson.text = user.name
                    tvPhone.text = user.phone
                    tvEmail.text = user.email
                }
            }

            listPost.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

            isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    loadingDialog.show()
                } else {
                    loadingDialog.dismiss()
                }
            }
        }
    }

}