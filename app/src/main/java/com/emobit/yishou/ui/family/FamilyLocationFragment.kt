package com.emobit.yishou.ui.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emobit.yishou.databinding.FragmentFamilyLocationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FamilyLocationFragment : Fragment() {
    private var _binding: FragmentFamilyLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFamilyLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
