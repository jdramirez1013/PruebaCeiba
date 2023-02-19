package com.jdr.pruebaceiba.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jdr.pruebaceiba.databinding.FragmentMainBinding
import com.jdr.pruebaceiba.ui.dialog.DialogLoading
import com.jdr.pruebaceiba.ui.gone
import com.jdr.pruebaceiba.ui.main.adapter.UserAdapter
import com.jdr.pruebaceiba.ui.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: UserAdapter
    private lateinit var loadingDialog: DialogLoading

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        loadingDialog = DialogLoading(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
        listeners()
        observers()
    }

    private fun setup() {
        adapter = UserAdapter()
        with(binding) {
            rvPerson.adapter = adapter
            rvPerson.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun listeners() {
        adapter.onClickListener = {

            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToPublicationFragment(
                    it.id
                )
            )
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getUsersSearch(newText!!)
                return true
            }

        })
    }

    private fun observers() {
        with(viewModel) {
            listUser.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }

            isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    loadingDialog.show()
                } else {
                    loadingDialog.dismiss()
                }
            }

            emptyList.observe(viewLifecycleOwner) {
                with(binding) {
                    if (it) {
                        rvPerson.gone()
                        tvListEmpty.show()
                    } else {
                        rvPerson.show()
                        tvListEmpty.gone()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}