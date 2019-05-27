package azamat.kz.loginapp.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import azamat.kz.loginapp.*
import azamat.kz.loginapp.view.CustomEditText
import kotlinx.android.synthetic.main.a_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    private val edEmail by lazy { email }
    private val edPassword by lazy { password }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        initUi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                onBackPressed()
            R.id.btn_create ->
                displaySnackbar(getString(R.string.txt_create))
        }
        return true
    }

    private fun initUi() {
        initActionBar()
        initEditText()

        email_sign_in_button.setOnClickListener {
            hideKeybord()
            login_progress.visibility = View.VISIBLE
            viewModel.validation(edEmail.text.toString(), edPassword.text.toString()).observe(this, Observer { t ->
                login_progress.visibility = View.GONE
                when (t) {
                    EMPTY_EMAIL -> {
                        displaySnackbar(getString(R.string.message_empty_email))
                    }
                    NOT_VALID_EMAIL -> {
                        displaySnackbar(getString(R.string.message_not_valid_email))
                    }
                    EMPTY_PASSWORD -> {
                        displaySnackbar(getString(R.string.message_empty_password))
                    }
                    NOT_VALID_PASSWORD -> {
                        displaySnackbar(getString(R.string.txt_password_rule))
                    }
                    SUCCESS -> {
                        geWeather()
                    }
                }
            })
        }
    }

    private fun initActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.toolbar_title)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp)
    }

    private fun initEditText() {
        edPassword.setOnDrawableClickListener(object : CustomEditText.OnDrawableClickListener {
            override fun onDrawableClick() {
                displaySnackbar(getString(R.string.txt_password_rule))
            }
        })
    }

    private fun displaySnackbar(message: String) {
        val view = findViewById<LinearLayout>(R.id.content)
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val textView = snackBar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.maxLines = 5
        snackBar.show()
    }

    private fun geWeather() {
        login_progress.visibility = View.VISIBLE
        email_login_form.visibility = View.GONE
        viewModel.getWeather().observe(this, Observer { t ->
            login_progress.visibility = View.GONE
            email_login_form.visibility = View.VISIBLE
            if (t != null) {
                val weather = t[0]
                val message = "${weather.name} - ${(weather.main.temp - 273.16).toInt()} °C"
                displaySnackbar(message)
            }
        })
    }

    private fun hideKeybord() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = getCurrentFocus()
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
