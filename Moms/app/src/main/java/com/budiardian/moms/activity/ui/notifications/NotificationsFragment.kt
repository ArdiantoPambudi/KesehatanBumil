package com.budiardian.moms.activity.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.budiardian.moms.R
import com.budiardian.moms.activity.session.SessionManager
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {
    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        //  val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(this, Observer {
            //            textView.text = it
            val namaLengkap: TextView = root.findViewById(R.id.tvnama_lengkap)

        })
        return root
    }




    //    val textView:TextView=tvnama_lengkap. = username
//    txtusername1.text = username
//    txtjenis_kelamin.text = jenis_kelamin
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val session = SessionManager(this.requireContext())
        var nama_lengkap = session.getNama_lengkap()
        var usernamee = session.getNama_suami()
        var passwordd = session.getTinggi_badan()
        var tglLahir = session.getTgllahir()
        var telepon = session.getTelepon()

        tvnama_lengkap.text = nama_lengkap
//        tvUsername.text = usernamee
//        tvPassword.text = passwordd
        tvTglLahir.text = tglLahir
        tvtelepon.text = telepon


        btnlogout.setOnClickListener {
            session.logoutUser()
            //this.getContext().finish()

        }
       }



    }

