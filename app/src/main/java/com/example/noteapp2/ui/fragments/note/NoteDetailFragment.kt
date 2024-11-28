package com.example.noteapp2.ui.fragments.note

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.noteapp2.App
import com.example.noteapp2.R
import com.example.noteapp2.data.models.NoteModel
import com.example.noteapp2.databinding.FragmentNoteDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var noteId: Int? = null
    private var color: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isButtonVisible()
        updateNote()
        setupListener()
    }

    private fun isButtonVisible() {
        if (binding.etTitle.text != null && binding.etDescription.text != null) {
            binding.tvSave.visibility = View.VISIBLE
        }
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }
        if (noteId != -1) {
            val note = App.appDataBase?.Dao()?.getByID(noteId!!)
            note?.let { item ->
                binding.etTitle.setText(item.title)
                binding.etDescription.setText(item.description)
                binding.tvDate.text = item.date
            }
        }
    }

    private fun setupListener() = with(binding) {
        colorChange.setOnClickListener {
            showColorDialog()
        }
        tvSave.setOnClickListener {
            val title = etTitle.text.toString()
            val text = etDescription.text.toString()
            val data = tvDate.text.toString()
            val color = color
            if (noteId != -1){
                val updateNote = NoteModel(title, text, data, color.hashCode())
                updateNote.id = noteId!!
                App.appDataBase?.Dao()?.updateNote(updateNote)

            } else{
                App.appDataBase?.Dao()?.insert(NoteModel(title, text, data, color.hashCode()))
            }
            findNavController().navigateUp()
        }
        toolbarDetail.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    private fun showColorDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.color_picked, null)
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.findViewById<View>(R.id.color_yellow).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.yellow)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_purple).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.purple)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_pink).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.pink)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_red).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.red)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_green).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.green)
            dialog.dismiss()
        }
        dialogView.findViewById<View>(R.id.color_blue).setOnClickListener {
            color = ContextCompat.getColor(requireContext(), R.color.blue)
            dialog.dismiss()
        }
        dialog.show()

        val window =dialog.window
        val layoutParams = window?.attributes

        layoutParams?.gravity = Gravity.END or Gravity.TOP
        layoutParams?.x = 100
        layoutParams?.y = 100

        window?.attributes = layoutParams
    }


    private fun getCurrentTime(): String {
        val date = SimpleDateFormat("dd MMMM HH:mm")
        return date.format(Date())
    }
}