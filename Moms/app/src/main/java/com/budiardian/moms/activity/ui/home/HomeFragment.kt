package com.budiardian.moms.activity.ui.home

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.budiardian.moms.R
import com.budiardian.moms.activity.*
import com.budiardian.moms.activity.session.SessionManager
import kotlinx.android.synthetic.main.fragment_home.*
import com.synnapps.carouselview.ImageListener







class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var cvBayi: CardView? = null
    private var cvHPL: CardView? = null
    private var cvInfo: CardView? = null
    private var cvIbuhamill: CardView? = null

    val sampleImages = intArrayOf(
        R.drawable.bpm,
        R.drawable.gambar1,
        R.drawable.gambar2,
        R.drawable.gambar4,
        R.drawable.gamba

    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        cvIbuhamill = root?.findViewById(R.id.cvIbuhamil) as CardView
        cvBayi = root?.findViewById(R.id.cvBayi) as CardView
        cvHPL = root?.findViewById(R.id.cvHPL) as CardView
        cvInfo = root?.findViewById(R.id.cvInfo) as CardView
        cvIbuhamill?.setOnClickListener {
            var intent= Intent(context, IbuHamilActivity::class.java)
            startActivity(intent)
        }
        cvBayi?.setOnClickListener {
            var intent= Intent(context, CekKesehatanActivity::class.java)
            startActivity(intent)
        }
        cvHPL?.setOnClickListener {
            var intent= Intent(context, HPLActivity::class.java)
            startActivity(intent)
        }
        cvInfo?.setOnClickListener {
            var intent= Intent(context, InfoActivity::class.java)
            startActivity(intent)
        }


        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carouselView.setImageListener(imageListener)
        carouselView.setPageCount(sampleImages.size)



    }
        val imageListener = object : ImageListener {
            override fun setImageForPosition(position: Int, imageView: ImageView?) {
                imageView?.setImageResource(sampleImages[position])
            }


        }


    }

