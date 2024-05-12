package com.example.to.fragment

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
import com.example.to.MainActivity
import com.example.to.R
import com.example.to.databinding.FragmentAddnoteBinding
import com.example.to.model.Notee
import com.example.to.viewmodel.NoteeViewModel

class AddnoteFragment : Fragment(R.layout.fragment_addnote), MenuProvider {

    private var addnoteBinding: FragmentAddnoteBinding? = null
    private val binding get()= addnoteBinding !!

    private lateinit var noteeViewModel: NoteeViewModel
    private lateinit var addNoteeView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addnoteBinding =  FragmentAddnoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val MenuHost: MenuHost = requireActivity()
        MenuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        noteeViewModel = (activity as MainActivity).noteeViewModel
        addNoteeView = view
    }

    private fun saveNote(view: View){
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteTitle.text.toString().trim()

        if (noteTitle.isNotEmpty()){
            val note = Notee(0, noteTitle, noteDesc)
            noteeViewModel.addNote(note)

            Toast.makeText(addNoteeView.context, "Note Saved",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment, false)
        }
        else{
            Toast.makeText(addNoteeView.context, "please enter the title of the note",Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.add_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu ->{
                saveNote(addNoteeView)
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addnoteBinding = null
    }

}