package tk.andivinu.deliveryapp.ui.delivery

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import tk.andivinu.deliveryapp.R
import tk.andivinu.deliveryapp.databinding.ActivityDeliveryListBinding
import tk.andivinu.deliveryapp.injection.ViewModelFactory
import tk.andivinu.deliveryapp.ui.deliverydetail.DeliveryDetailActivity

class DeliveryListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeliveryListBinding
    private lateinit var viewModel: DeliveryListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery_list)
        binding.resultList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.resultList!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(DeliveryListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.deliveryItemId.observe(this, Observer {
            errorMessage ->
            if (errorMessage != null) deliveryItemClicked(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }


    private fun deliveryItemClicked(deliveryItemId: Int) {
        // Launch Delivery Detail activity, pass delivery ID as int parameter
        val showDetailActivityIntent = Intent(this, DeliveryDetailActivity::class.java)
        showDetailActivityIntent.putExtra(Intent.EXTRA_TEXT, deliveryItemId)
        startActivity(showDetailActivityIntent)
    }
}