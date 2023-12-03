package com.example.studentapplication.ui.fragments.home.lecturesDetail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studentapplication.R
import com.example.studentapplication.data.remote.response.get_lectures.GetLecturesResponseItem
import com.example.studentapplication.databinding.FragmentLectureDetailBinding

class LectureDetailFragment : Fragment() {
    private val args by navArgs<LectureDetailFragmentArgs>()
    private lateinit var itemLecture: GetLecturesResponseItem

    private lateinit var binding: FragmentLectureDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLectureDetailBinding.inflate(inflater)
        binding.btnShowPdf.setOnClickListener {
            val action =
                LectureDetailFragmentDirections.actionLectureDetailFragmentToFragmentPdfView(
                    itemLecture.filePath
                )
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemLecture = args.item
        binding.apply {
            if (itemLecture.lecture_Desc.isNotEmpty())
                lecDesc.text = itemLecture.lecture_Desc
            else {
                lecDesc.visibility = View.INVISIBLE
                tvLecDesc.visibility = View.INVISIBLE
            }
            if (itemLecture.lecture_Note.isNotEmpty())
                lecNote.text = itemLecture.lecture_Note
            else {
                lecNote.visibility = View.INVISIBLE
                tvLecNote.visibility = View.INVISIBLE
            }

            if (itemLecture.lecture_Name.isNotEmpty())
                lecName.text = itemLecture.lecture_Name
            else {
                lecName.visibility = View.INVISIBLE
                tvLecName.visibility = View.INVISIBLE
            }
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videoView)
            val uri = Uri.parse(itemLecture.videoPath)
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()

        }

    }

    override fun onStop() {
        super.onStop()
        binding.videoView.stopPlayback()
    }


}









