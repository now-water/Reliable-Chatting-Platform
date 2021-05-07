package com.example.chattingapp.view

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

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

        setUpAdapter()
        setUpListener()
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
//        inputID!!.addTextChangedListener(mIdTextWatcher)
//        inputPW!!.addTextChangedListener(mPwTextWatcher)
    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.join_btn -> {
//                val i = Intent(getApplicationContext(), SignUpActivity::class.java)
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
                id = inputID!!.text.toString().trim { it <= ' ' }
                password = inputPW!!.text.toString().trim { it <= ' ' }

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


            UserApiService.instance.signIn(LoginRequest(id, password), {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", it)
                startActivity(intent)
                finish()
            }, {
                Toast.makeText(this, "해당 id나 pwd가 없습니다.", Toast.LENGTH_SHORT).show()
            })

//        UserApiService.instance.signIn(user){
//            Log.d("it", it.toString())
//            Log.d("user", user.toString())
//
//            Log.d("1", it.accountId)
//            Log.d("1", user.accountId)
//            Log.d("1", it.accountId.equals(user.accountId).toString())
//
////            Log.d("1", it.password)
////            Log.d("1", user.password)
////            Log.d("2", it.password.equals(user.password).toString())
//
//            //이부분!!!!!!!!!!!!!!!!!!!
//            if(it.accountId.equals(user.accountId) ){
//                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
//
//                user.userId = it.userId
//                user.accountId.replace(user.accountId, it.accountId)
//                user.name.replace(user.name, it.name)
//                user.nickName.replace(user.nickName, it.nickName)
//                //user.password.replace(user.password, it.password)
//                user.phoneNum.replace(user.phoneNum, it.phoneNum)
//                //user.statusMessage.replace(user.statusMessage, it.statusMessage)
//
//                intent = Intent(this, MainActivity::class.java)
//                intent.putExtra("user", user)
//                startActivity(intent)
//                finish()
//            }else{
//                Toast.makeText(this, "해당 id나 pwd가 없습니다.", Toast.LENGTH_SHORT).show()
//            }
//        }
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

//    private val mIdTextWatcher: SimpleTextWatcher = object : SimpleTextWatcher {
//        override fun afterTextChanged(s: Editable?) {
//            inputIDcheck!!.visibility = View.VISIBLE
//            val isCheckEmail = emailPattern(inputID!!.text.toString())
//            if (isCheckEmail) {
//                inputIDcheck!!.text = "사용할 수 있는 아이디 입니다."
//                inputIDcheck!!.setTextColor(Color.parseColor("#df504a"))
//            } else {
//                inputIDcheck!!.text = "이메일 형식에 맞지 않습니다."
//                inputIDcheck!!.setTextColor(Color.parseColor("#6e6e6e"))
//            }
//        }
//    }
//    private val mPwTextWatcher: SimpleTextWatcher = object : SimpleTextWatcher {
//        override fun afterTextChanged(s: Editable?) {
//            val isCheckPw = pwPattern(inputPW!!.text.toString())
//            Log.d("test", "aaaa == $isCheckPw")
//            if (isCheckPw) {
//                inputPWcheck!!.visibility = View.INVISIBLE
//            } else {
//                inputPWcheck!!.visibility = View.VISIBLE
//                inputPWcheck!!.text = "비밀번호 형식에 맞지 않습니다."
//                inputPWcheck!!.setTextColor(Color.parseColor("#6e6e6e"))
//            }
//        }
//    }

    // 이메일 패턴 검사
    fun emailPattern(email: String): Boolean {
        val repExp =
            Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
        return email.matches(repExp)
    }

    // 비밀번호 패턴 검사
    fun pwPattern(pw: String): Boolean {
        val repExp = Regex("^([0-9a-zA-Z]).{1,20}$")
        return pw.matches(repExp)
    }
}