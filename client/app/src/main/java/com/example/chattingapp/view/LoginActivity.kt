package com.example.chattingapp.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapp.R
import com.example.chattingapp.dto.User
import com.example.chattingapp.service.UserApiService
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = javaClass.simpleName

    private var loginBtn: Button? = null
    private var inputID: EditText? = null
    private var inputPW: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

        setUpAdapter()
        setUpListener()
//        val accessToken: String = SharedPreference.getLoginInfo(this).access_token
//        Log.d(TAG, "Authorization : $accessToken")
//        if (!TextUtils.isEmpty(accessToken)) { // 자동 로그인
//            requestMeminfo()
//        }
    }

    fun initView() {
//        mJoinBtn = findViewById(R.id.join_btn)
//        mFindId = findViewById(R.id.find_id)
//        mFindPw = findViewById(R.id.find_pw)
//        mInputIDcheck = findViewById(R.id.input_id_check)
//        mInputPWcheck = findViewById(R.id.input_pw_check)
        loginBtn = findViewById(R.id.login_btn)
        inputID = findViewById(R.id.input_id)
        inputPW = findViewById(R.id.input_pw)
    }
    fun setUpAdapter() {}
    fun setUpListener() {
//        mJoinBtn!!.setOnClickListener(this)
//        mFindId!!.setOnClickListener(this)
//        mFindPw!!.setOnClickListener(this)
        loginBtn!!.setOnClickListener(this)
//        inputID!!.addTextChangedListener(mIdTextWatcher)
//        inputPW!!.addTextChangedListener(mPwTextWatcher)
    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.join_btn -> {
//                val i = Intent(getApplicationContext(), JoinActivity::class.java)
//                startActivity(i)
//            }
//            R.id.find_id -> {
//                val i = Intent(getApplicationContext(), FindIDActivity::class.java)
//                startActivity(i)
//            }
//            R.id.find_pw -> {
//                val i = Intent(getApplicationContext(), FindPWActivity::class.java)
//                startActivity(i)
//            }
            R.id.login_btn -> {
                val id = inputID!!.text.toString().trim { it <= ' ' }
                val password = inputPW!!.text.toString().trim { it <= ' ' }

//                intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)

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

        var user = User(0, id, "효동", "쿠쿠루삥뽕", password,"")

        UserApiService.instance.signIn(user){
            if(it == -1){
                Toast.makeText(this, "해당 id나 pwd가 없습니다링.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "로그인에 성공하였다링.", Toast.LENGTH_SHORT).show()

                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
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

//    private val mIdTextWatcher: SimpleTextWatcher = object : SimpleTextWatcher() {
//        fun afterTextChanged(s: Editable?) {
//            mInputIDcheck!!.visibility = View.VISIBLE
//            val isCheckEmail = emailPattern(mInputID!!.text.toString())
//            if (isCheckEmail) {
//                mInputIDcheck!!.text = "사용할 수 있는 아이디 입니다."
//                mInputIDcheck!!.setTextColor(Color.parseColor("#df504a"))
//            } else {
//                mInputIDcheck!!.text = "이메일 형식에 맞지 않습니다."
//                mInputIDcheck!!.setTextColor(Color.parseColor("#6e6e6e"))
//            }
//        }
//    }
//    private val mPwTextWatcher: SimpleTextWatcher = object : SimpleTextWatcher() {
//        fun afterTextChanged(s: Editable?) {
//            val isCheckPw = pwPattern(mInputPW!!.text.toString())
//            Log.d("test", "aaaa == $isCheckPw")
//            if (isCheckPw) {
//                mInputPWcheck!!.visibility = View.INVISIBLE
//            } else {
//                mInputPWcheck!!.visibility = View.VISIBLE
//                mInputPWcheck!!.text = "비밀번호 형식에 맞지 않습니다."
//                mInputPWcheck!!.setTextColor(Color.parseColor("#6e6e6e"))
//            }
//        }
//    }
//
//    // 이메일 패턴 검사
//    fun emailPattern(email: String): Boolean {
//        val repExp =
//            "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"
//        return email.matches(repExp)
//    }
//
//    // 비밀번호 패턴 검사
//    fun pwPattern(pw: String): Boolean {
//        val repExp = "^([0-9a-zA-Z]).{7,20}$"
//        return pw.matches(repExp)
//    }
}