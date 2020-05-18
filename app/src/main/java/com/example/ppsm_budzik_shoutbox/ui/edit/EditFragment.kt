package com.example.ppsm_budzik_shoutbox.ui.shoutbox

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.ppsm_budzik_shoutbox.MyMessage
import com.example.ppsm_budzik_shoutbox.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ppsm_budzik_shoutbox.JsonPlaceholderAPI

class EditFragment : Fragment() {

    private lateinit var editViewModel: EditViewModel
    private lateinit var jsonPlaceholderAPI: JsonPlaceholderAPI
    private val baseUrl: String = "http://tgryl.pl/"
    private lateinit var infoToast: Toast
    private lateinit var button: Button
    private lateinit var textLogin: TextView
    private lateinit var textDate: TextView
    private lateinit var textTime: TextView
    private lateinit var editTextContent: EditText

    private lateinit var login: String
    private lateinit var date: String
    private lateinit var content: String
    private lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editViewModel =
            ViewModelProvider(this).get(EditViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_edit, container, false)

        editTextContent = root.findViewById(R.id.editText)
        textLogin = root.findViewById(R.id.loginEditTextView)
        textDate = root.findViewById(R.id.dateEditTextView)
        textTime = root.findViewById(R.id.timeEditTextView)
        button = root.findViewById(R.id.editButton)

        login = arguments?.getString("login").toString()
        date = arguments?.getString("date_hour").toString()
        id = arguments?.getString("id").toString()
        content = arguments?.getString("content").toString()

        textLogin.text = login
        textDate.text = date.subSequence(0, 10)
        textTime.text = date.subSequence(11, 19)
        editTextContent.setText(content)

        //////////json
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory
                    .create()
            )
            .build()
        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI::class.java)
        //json

        button.setOnClickListener {
            //dzialanie przycisku save edit
            content = editTextContent.text.toString()
            putData()
            val bundle = Bundle()
            bundle.putString("login", login)
            val fragment: Fragment = ShoutboxFragment()
            fragment.arguments = bundle
            val fragmentManager: FragmentManager? = fragmentManager
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, fragment)
                ?.commit()
        }

        return root
    }

    fun openCloseNavigationDrawer(view: View) {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    fun checkNetworkConnection(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    private fun putData() {
        val message = MyMessage(login, content)

        val call = jsonPlaceholderAPI.createPut(id, message)

        call.enqueue(object : Callback<MyMessage> {
            override fun onResponse(
                call: Call<MyMessage>,
                response: Response<MyMessage>
            ) {
                if (!response.isSuccessful) {
                    println("Code: " + response.code())
                    return
                }
            }

            override fun onFailure(
                call: Call<MyMessage>,
                t: Throwable
            ) {
                println(t.message)
            }
        })
    }
}
