package com.andre.checkpoint04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.andre.checkpoint04.database.AppDatabase
import com.andre.checkpoint04.database.ArtefatoInfo
import com.andre.checkpoint04.databinding.FragmentRegisterArtefatoBinding

class ArtefatoItemFragment : Fragment() {

    private var binding: FragmentRegisterArtefatoBinding? = null
    private val appDb by lazy {
        view?.context?.let {
            AppDatabase.getDatabase(it)
        }
    }

    private val artefatoInfoArgument by lazy {
        arguments?.getParcelable(ARTEFATO_INFO_BUNDLE_KEY) as? ArtefatoInfo
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterArtefatoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding?.buttonBackToArtefatos?.setOnClickListener {
            findNavController().navigateUp()
        }

        binding?.registerArtefatoButton?.setOnClickListener {
            insertData()
        }
    }

    private fun insertData() {
        binding?.run {
            val artefatoInfo = ArtefatoInfo(
                nome = textInputEditTextArtefatoNome.text.toString(),
                nomeSistemaEstelar = textInputEditTextArtefatoNomeSistemaEstelar.text.toString(),
                nomePlanetaLua = textInputEditTextArtefatoNomePlanetaLua.text.toString(),
                coordenadaX = textInputEditTextArtefatoCoordenadaX.text.toString().toDouble(),
                coordenadaY = textInputEditTextArtefatoCoordenadaY.text.toString().toDouble(),
            )
            appDb?.artefatoInfoDao()?.insert(artefatoInfo)
            clearForm()
        }
    }

    private fun clearForm() {
        binding?.run {
            textInputEditTextArtefatoNome.text?.clear()
            textInputEditTextArtefatoNomeSistemaEstelar.text?.clear()
            textInputEditTextArtefatoNomePlanetaLua.text?.clear()
            textInputEditTextArtefatoCoordenadaX.text?.clear()
            textInputEditTextArtefatoCoordenadaY.text?.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val ARTEFATO_INFO_BUNDLE_KEY = "ARTEFATO_INFO_BUNDLE_KEY"

        fun buildBundle(artefatoInfo: ArtefatoInfo?): Bundle? {
            return artefatoInfo?.let {
                bundleOf(ARTEFATO_INFO_BUNDLE_KEY to it)
            }
        }
    }
}