package com.jdr.pruebaceiba.ui.publication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jdr.pruebaceiba.databinding.FragmentPublicationBinding


class PublicationFragment : Fragment() {

    private var _binding: FragmentPublicationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PublicationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPublicationBinding.inflate(inflater, container, false)
        return binding.root
    }


}