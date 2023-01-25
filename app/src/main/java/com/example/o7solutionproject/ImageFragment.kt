package com.example.o7solutionproject

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.util.lruCache
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.BuildConfig
import com.example.o7solutionproject.databinding.FragmentImageBinding
import java.io.File
import java.nio.file.spi.FileSystemProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null


   lateinit var binding:FragmentImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnImagePick.setOnClickListener{
            imagepicker()
        }

    }




    private val gallery=registerForActivityResult(ActivityResultContracts.GetContent()){
            uri: Uri? ->
            uri?.let {
                binding.ivImage.setImageURI(uri)
            }
                var latestUri:Uri?=null
                val view by lazy {
                    binding.ivImage
                }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun imagepicker() = gallery.launch("image/*")

    private fun getFileUri():Uri {
        val file =File.createTempFile("image1",".png").apply {
            createNewFile()
            deleteOnExit()

        }
        return FileProvider.getUriForFile(
            requireContext(),
        "${BuildConfig.BUILD_TYPE}.Provide",file
        )


    }
}