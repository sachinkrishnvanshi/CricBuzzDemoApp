package com.example.codelab5.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.codelab5.R
import com.example.codelab5.databinding.FragmentAddPlayerDetailsBinding
import com.example.codelab5.model.PlayerEntity
import com.example.codelab5.model.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_add_player_details.*
import java.io.ByteArrayOutputStream
import java.util.*

class AddPlayerDetailsFragment : Fragment() {
    private var binding:FragmentAddPlayerDetailsBinding? =null
    private lateinit var playerViewModel: PlayerViewModel
    private val cameraRequest = 1888
    private val galleryRequest = 18
    private var selectedimg:Bitmap?=null
    private val pickImage = 100
    var date : String? = null
    private var byteArray:ByteArray? = byteArrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.CAMERA
                )
                == PackageManager.PERMISSION_DENIED
            )
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.CAMERA),
                    cameraRequest
                )

            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_DENIED
            )
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    galleryRequest
                )


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentAddPlayerDetailsBinding.inflate(inflater, container , false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_add_picture.setOnClickListener {
            val builder= AlertDialog.Builder(requireContext())
            builder.setTitle("select Image")
            builder.setMessage("choose your Option?")
            builder.setPositiveButton("Gallery")
            {
                    dailog,which->
                dailog.dismiss()

                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)

            }
            builder.setNegativeButton("camera"){dailog,which->
                dailog.dismiss()

                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, cameraRequest)
            }
            val dailog:AlertDialog=builder.create()
            dailog.show()
        }


        playerViewModel= ViewModelProvider(this).get(PlayerViewModel::class.java)

        btn_date_picker.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val mYear: Int = calendar.get(Calendar.YEAR) // current year
            val mMonth: Int = calendar.get(Calendar.MONTH) // current month
            val mDay: Int = calendar.get(Calendar.DAY_OF_MONTH) // current day

            // date picker dialog

            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { view, year, month, day -> // set day of month , month and year value in the edit text
                    if (calendar.get(Calendar.YEAR) - year >= 18) {
                        binding!!.tvDob.text =
                            day.toString() + "/" + (month + 1) + "/" + year

                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "Age must be greater than 18",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }, mYear, mMonth, mDay

            )
            datePickerDialog.show()

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.btnSave?.setOnClickListener {

            if (binding?.etPlayerName?.text!!.isNotEmpty() &&
                binding?.etCountry?.text!!.isNotEmpty() &&
                binding?.tvDob?.text!!.isNotEmpty() &&
                binding?.etRuns?.text!!.isNotEmpty() &&
                binding?.etMatches?.text!!.isNotEmpty() &&
                binding?.etTeam?.text!!.isNotEmpty() &&
                binding?.etWicket?.text!!.isNotEmpty()
            ) {
                val player = PlayerEntity(
                    0,
                    byteArray,iv_fav.isClickable,
                    binding?.etPlayerName?.text.toString(),
                    binding?.etTeam?.text.toString(),
                    binding?.etCountry?.text.toString(),
                    binding?.tvDob?.text.toString(),
                    binding?.etWicket?.text.toString(),
                    binding?.etRuns?.text.toString(),
                    binding?.etMatches?.text.toString(),
                    rb_batsman.isChecked,
                    rb_bowler.isChecked
                )
                playerViewModel.addPlayer(player)
                it.findNavController()
                    .navigate(R.id.action_addPlayerDetailsFragment_to_homeFragment)
                Toast.makeText(requireContext(), "Submit Successfully", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(requireContext() , "All Field Mandatory" , Toast.LENGTH_SHORT).show()
            }
        }
        iv_fav.setOnClickListener {
            iv_fav_red.visibility=View.VISIBLE
            iv_fav.visibility=View.INVISIBLE
        }
        iv_fav_red.setOnClickListener {
            iv_fav.visibility=View.VISIBLE
            iv_fav_red.visibility=View.INVISIBLE
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) {

            iv_add_picture.setImageBitmap(data?.extras?.get("data") as Bitmap)
            selectedimg=data.extras?.get("data") as Bitmap
            val bitmap = data.extras?.get("data") as Bitmap
            val outPutStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, outPutStream)
            val btArray = outPutStream.toByteArray()
            byteArray = btArray
        }


        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {

            val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,data?.data)
            iv_add_picture.setImageBitmap(bitmap)
            selectedimg=bitmap

            val outPutStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, outPutStream)
            val btArray = outPutStream.toByteArray()
            byteArray = btArray

        }
    }
}
