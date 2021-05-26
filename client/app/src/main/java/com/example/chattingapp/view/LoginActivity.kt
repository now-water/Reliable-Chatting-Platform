package com.example.chattingapp.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapp.R
import com.example.chattingapp.dto.User
import com.example.chattingapp.dto.request.LoginRequest
import com.example.chattingapp.service.UserApiService
import com.example.chattingapp.view.SimpleTextWatcher
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import java.lang.IllegalStateException

class LoginActivity : AppCompatActivity(), View.OnClickListener, SimpleTextWatcher {

    private val TAG = javaClass.simpleName
    private var joinBtn: Button? = null

    private var findId: Button? = null
    private var findPw: Button? = null
    private var loginBtn: Button? = null
    private var inputID: EditText? = null
    private var inputPW: EditText? = null
    private var inputIDcheck: TextView? = null
    private var inputPWcheck: TextView? = null

    //user 정보
    private lateinit var id: String
    private lateinit var password: String
    private var fcmToken : String = "temporaliy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()

        setUpAdapter()
        setUpListener()
        FirebaseApp.initializeApp(applicationContext)
        System.out.println("token : "+ FirebaseInstanceId.getInstance().getToken()); // 토큰을 확인할 수 있는 코드
        fcmToken = FirebaseInstanceId.getInstance().token!!;
    }

    fun initView() {
        joinBtn = findViewById(R.id.join_btn)
        findId = findViewById(R.id.find_id)
        findPw = findViewById(R.id.find_pw)
        inputIDcheck = findViewById(R.id.input_id_check)
        inputPWcheck = findViewById(R.id.input_pw_check)
        loginBtn = findViewById(R.id.login_btn)
        inputID = findViewById(R.id.input_id)
        inputPW = findViewById(R.id.input_pw)
    }
    fun setUpAdapter() {}
    fun setUpListener() {
        joinBtn!!.setOnClickListener(this)
        findId!!.setOnClickListener(this)
        findPw!!.setOnClickListener(this)
        loginBtn!!.setOnClickListener(this)
        inputID!!.addTextChangedListener(mIdTextWatcher)
        inputPW!!.addTextChangedListener(mPwTextWatcher)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.join_btn -> {
                val i = Intent(getApplicationContext(), SignUpActivity::class.java)
                startActivity(i)
            }
//            R.id.find_id -> {
//                val i = Intent(getApplicationContext(), FindIDActivity::class.java)
//                startActivity(i)
//            }
//            R.id.find_pw -> {
//                val i = Intent(getApplicationContext(), FindPWActivity::class.java)
//                startActivity(i)
//            }
            R.id.login_btn -> {
                id = inputID!!.text.toString().trim { it <= ' ' }
                password = inputPW!!.text.toString().trim { it <= ' ' }
                requestLogin(id, password)
            }
        }
    }

    // 로그인 요청
    fun requestLogin(id: String, password: String) {
        //사용자 앱 memType = P
        //password error = 409
//        RetrofitClient.getInstance(this)
//            .getAuthApiService()
//            .requestPostLogin("password", "carsin", 4515, id, password, "P")
//            .enqueue(object : Callback<LoginOauthResponse?> {
//                override fun onResponse(
//                    call: Call<LoginOauthResponse?>,
//                    response: Response<LoginOauthResponse?>
//                ) {
//                    Log.d("Response code", response.code().toString())
//                    Log.d("Login Response = ", "" + response)
//                    if (response.isSuccessful()) {
//                        val data: LoginOauthResponse? = response.body()
//                        if (data != null) {
//                            SharedPreference.putLoginInfo(this@LoginActivity, data)
//
//                            //meminfo 호출
//                            requestMeminfo()
//                        }
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<LoginOauthResponse?>,
//                    t: Throwable
//                ) {
//                    Log.d("Login Failed = ", t.message!!)
//                }
//            })
        //checkStatus()

        Log.e("good", id + " " + password)


        UserApiService.instance.signIn(LoginRequest(id, password, fcmToken), {
            Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
            UserApiService.instance.checkSession({
                Log.d("logincheck", it.toString())
            },{
                Log.d("logincheckfail", "꺼졍")
            })
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user", it)
            startActivity(intent)
            finish()
        }, {
            Toast.makeText(this, "아이디나 패스워드가 존재하지 않거나 잘못되었습니다.", Toast.LENGTH_SHORT).show()
        })


    }

    // meminfo 요청
    fun requestMeminfo() {
//        //사용자 앱 memType = P
//        //password error = 409
//        RetrofitClient.getInstance(this)
//            .getAuthApiService()
//            .requestMemInfo()
//            .enqueue(object : Callback<ResponseBody?> {
//                override fun onResponse(
//                    call: Call<ResponseBody?>,
//                    response: Response<ResponseBody?>
//                ) {
//                    Log.d("Response code", response.code().toString())
//                    if (response.isSuccessful) {
//                        val data = response.body()
//                        try {
//                            val Jarray = JSONObject(data!!.string())
//                            Log.d(
//                                "Response body to json",
//                                "" + Jarray.getJSONObject("memInfo")
//                            )
//                            val jsonObject = Jarray.getJSONObject("memInfo")
//                            val item = MemInfoResponse()
//                            item.memNo = jsonObject.getString("memNo")
//                            item.email = jsonObject.getString("email")
//                            item.name = jsonObject.getString("name")
//                            item.memType = jsonObject.getString("memType")
//                            item.picture = jsonObject.getString("picture")
//                            item.thumb = jsonObject.getString("thumb")
//                            item.regDate = jsonObject.getString("regDate")
//                            item.mobileNo = jsonObject.getString("mobileNo")
//                            SharedPreference.putMemberInfo(this@LoginActivity, item)
//                            val i =
//                                Intent(getApplicationContext(), MainActivity::class.java)
//                            startActivity(i)
//                            finish()
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                        } catch (e: JSONException) {
//                            e.printStackTrace()
//                        }
//                    }
//                    Log.d("Login Response = ", "" + response)
//                }
//
//                override fun onFailure(
//                    call: Call<ResponseBody?>,
//                    t: Throwable
//                ) {
//                }
//            })
    }

    private val mIdTextWatcher: SimpleTextWatcher = object : SimpleTextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            inputIDcheck!!.visibility = View.VISIBLE
            val isCheckEmail = emailPattern(inputID!!.text.toString())
            if (isCheckEmail) {
                inputIDcheck!!.text = "적합한 형식의 아이디(이메일) 입니다."
                inputIDcheck!!.setTextColor(Color.parseColor("#df504a"))
            } else {
                inputIDcheck!!.text = "이메일 형식에 맞지 않습니다."
                inputIDcheck!!.setTextColor(Color.parseColor("#6e6e6e"))
            }
        }
    }
    private val mPwTextWatcher: SimpleTextWatcher = object : SimpleTextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val isCheckPw = pwPattern(inputPW!!.text.toString())
            if (isCheckPw) {
                inputPWcheck!!.visibility = View.INVISIBLE
            } else {
                inputPWcheck!!.visibility = View.VISIBLE
                inputPWcheck!!.text = "비밀번호 형식에 맞지 않습니다."
                inputPWcheck!!.setTextColor(Color.parseColor("#6e6e6e"))
            }
        }
    }

    // 이메일 패턴 검사
    fun emailPattern(email: String): Boolean {
        val repExp =
            Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
        return email.matches(repExp)
    }

    // 비밀번호 패턴 검사
    fun pwPattern(pw: String): Boolean {
        val repExp = Regex("^([0-9a-zA-Z]).{3,20}$")
        return pw.matches(repExp)
    }
}