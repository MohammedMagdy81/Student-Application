package com.example.studentapplication.ui.fragments.home.lecturesDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.example.studentapplication.databinding.FragmentPdfViewBinding
import com.example.studentapplication.utils.FileUtils
import es.dmoral.toasty.Toasty
import java.io.File

class FragmentPdfView : Fragment() {

    private lateinit var binding: FragmentPdfViewBinding
    private val args by navArgs<FragmentPdfViewArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPdfViewBinding.inflate(inflater)
        PRDownloader.initialize(requireContext().applicationContext)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.VISIBLE
        val fileName = "myLecture.pdf"
        downloadPdfFromInternet(
            args.pdfUri,
            FileUtils.getRootDirPath(requireContext()),
            fileName
        )
    }

    private fun downloadPdfFromInternet(url: String, dirPath: String, fileName: String) {
        PRDownloader.download(
            url,
            dirPath,
            fileName
        ).build()
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    Toast.makeText(requireContext(), "downloadComplete", Toast.LENGTH_LONG)
                        .show()
                    val downloadedFile = File(dirPath, fileName)
                    binding.progressBar.visibility = View.GONE
                    showPdfFromFile(downloadedFile)
                }

                override fun onError(error: Error?) {
                    Toasty.error(requireContext(), "Error in download PDF", Toast.LENGTH_LONG)
                        .show()
                }


            })
    }

    private fun showPdfFromFile(file: File) {
        binding.pdfView.fromFile(file)
            .password(null)
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .onPageError { page, _ ->
                Toast.makeText(
                    requireContext(),
                    "Error at page: $page", Toast.LENGTH_LONG
                ).show()
            }
            .load()
    }

}