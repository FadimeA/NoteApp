package com.example.noteapp2.ui.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp2.App
import com.example.noteapp2.R
import com.example.noteapp2.data.models.NoteModel
import com.example.noteapp2.databinding.FragmentNoteBinding
import com.example.noteapp2.interfaces.OnClickItem
import com.example.noteapp2.ui.adapters.NoteAdapter
import com.example.noteapp2.utils.PreferenceHelper


class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this, this)

    private val sharedPreference = PreferenceHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        getData()
        initialize()

        sharedPreference.init(requireContext())


        val isLinearLayout = sharedPreference.isLinearLayout()
        setRecyclerViewLayout(isLinearLayout)

    }

    private fun initialize(){
        binding.rvNote.adapter = noteAdapter
    }

    private fun setRecyclerViewLayout(isLinearLayout: Boolean) {
        binding.rvNote.layoutManager = if (isLinearLayout) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }


        binding.btnToggleLayout.setOnClickListener {

                val isCurrentlyLinear = binding.rvNote.layoutManager is LinearLayoutManager
                setRecyclerViewLayout(!isCurrentlyLinear)

                sharedPreference.setLinearLayout(!isCurrentlyLinear)

        }
    }

    private fun getData() {
        App.appDataBase?.Dao()?.getAll()?.observe(viewLifecycleOwner) { listNote ->
            noteAdapter.submitList(listNote)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить заметку?")
            setPositiveButton("Удалить") { _, _ ->
                App.appDataBase?.Dao()?.deleteNote(noteModel)
            }
            App.appDataBase?.Dao()?.getAll()?.observe(viewLifecycleOwner) { listNote ->
                noteAdapter.submitList(listNote)
            }
            setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}

