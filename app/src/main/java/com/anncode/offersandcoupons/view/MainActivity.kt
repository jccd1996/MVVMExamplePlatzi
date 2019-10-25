package com.anncode.offersandcoupons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.databinding.ActivityMainBinding
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.viewmodel.CouponViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var couponsViewModel: CouponViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        rvCoupons.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        setUpBindings(savedInstanceState)
        //CallCoupons
        //getCoupons - lista de cupones

    }

    fun setUpBindings(savedInstanceState: Bundle?) {
        //Esto solo funciona con androidx
        var activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Para usar el ViewModelProviders se necesita la libreria implementation'androidx.lifecycle:lifecycle-extensions:2.0.0'
        //couponsViewModel
        couponsViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)
        activityMainBinding.model = couponsViewModel
        setupListUpdate()

    }

    fun setupListUpdate() {
        couponsViewModel.callCoupons()
        couponsViewModel.getCoupons().observe(this, Observer { coupons: List<Coupon> ->
            Log.w("COUPON", coupons[0].title)
            couponsViewModel.setCouponsInRecyclerAdapter(coupons)
        })
    }
}
