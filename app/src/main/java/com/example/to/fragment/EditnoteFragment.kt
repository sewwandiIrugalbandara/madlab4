package com.example.to.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.to.MainActivity
import com.example.to.R
import com.example.to.databinding.FragmentEditnoteBinding
import com.example.to.model.Notee
import com.example.to.viewmodel.NoteeViewModel

class EditnoteFragment : Fragment(R.layout.fragment_editnote), MenuProvider {

    private var editnoteBinding: FragmentEditnoteBinding? = null
    private val binding get() = editnoteBinding!!

    private lateinit var noteeViewModel: NoteeViewModel
    private lateinit var currentNotee: Notee

    private val args: EditnoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editnoteBinding = FragmentEditnoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val MenuHost: MenuHost = requireActivity()
        MenuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        noteeViewModel = (activity as MainActivity).noteeViewModel
        currentNotee = args.note!!

        binding.editNoteTitle.setText(currentNotee.noteTitle)
        binding.editNoteDesc.setText(currentNotee.noteDesc)

        binding.editNoteFab.setOnClickListener {
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteDesc = binding.editNoteDesc.text.toString().trim()

            if ( noteTitle.isNotEmpty()){
                val notee = Notee(currentNotee.id, noteTitle, noteDesc)
                noteeViewModel.updateNote(notee)
                view.findNavController().popBackStack(R.id.homeFragment, false)

            } else {
                Toast.makeText(context, "please enter title of the note", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete note")
            setMessage("Are you sure to delete?")
            setPositiveButton("Delete"){_,_ ->
                noteeViewModel.deleteNote(currentNotee)
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment, false)
            }
            setNegativeButton("cancel", null)

        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.edit_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu -> {
                deleteNote()
                true
            }else -> false
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        editnoteBinding = null
    }

}