package com.andre.checkpoint04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.andre.checkpoint04.database.AppDatabase
import com.andre.checkpoint04.database.ArtefatoInfo
import com.andre.checkpoint04.databinding.FragmentArtefatosBinding

class ArtefatosFragment : Fragment() {

    private var binding: FragmentArtefatosBinding? = null
    private val artefatoAdapter by lazy {
        ArtefatoItemAdapter(
            onDeleteListener = ::onOpenConfirmationDialog,
            onUpdateListener = ::updateArtefato
        )
    }
    private val appDb by lazy {
        view?.context?.let {
            AppDatabase.getDatabase(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtefatosBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupViews() {
        binding?.recyclerViewArtefatos?.apply {
            setHasFixedSize(true)
            adapter = artefatoAdapter
        }
        binding?.buttonAddArtefato?.setOnClickListener { view ->
            findNavController().navigate(R.id.action_to_RegisterArtefatoFragment)
        }

        getDataFromDatabase()
    }

    private fun getDataFromDatabase() {
        appDb?.artefatoInfoDao()?.getAll()?.let {
            artefatoAdapter.setData(it)
        }
    }

    private fun updateArtefato(artefatoInfo: ArtefatoInfo) {
        findNavController().navigate(
            R.id.action_to_RegisterArtefatoFragment,
            ArtefatoItemFragment.buildBundle(artefatoInfo)
        )
    }

    private fun deleteArtefato(artefatoInfo: ArtefatoInfo) {
        appDb?.artefatoInfoDao()?.delete(artefatoInfo)
        binding?.recyclerViewArtefatos?.let {
            SnackBarUtil.showSnackBar(
                view = it,
                message = getString(R.string.success_deleted_message, artefatoInfo.nome)
            )
        }
        getDataFromDatabase()
    }

    private fun onOpenConfirmationDialog(artefatoInfo: ArtefatoInfo) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.delete_dialog_title))
                .setMessage(getString(R.string.delete_dialog_message, artefatoInfo.nome))
                .setNeutralButton(getString(R.string.delete_cancel_label)) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(getString(R.string.delete_continue_label)) { dialog, _ ->
                    deleteArtefato(artefatoInfo)
                    dialog.dismiss()
                }
        }
    }

}