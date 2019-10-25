package com.anncode.offersandcoupons.viewmodel

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.model.CouponObservable
import com.anncode.offersandcoupons.view.RecyclerCouponsAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class CouponViewModel : ViewModel() {

    private var couponObservable: CouponObservable = CouponObservable()
    private lateinit var recyclerCouponsAdapter: RecyclerCouponsAdapter

    fun callCoupons() = couponObservable.callCoupons()

    fun getCoupons(): MutableLiveData<List<Coupon>> = couponObservable.getCoupons()

    fun setCouponsInRecyclerAdapter(coupons: List<Coupon>){
        recyclerCouponsAdapter.apply {
            setCouponsList(coupons)
            notifyDataSetChanged()
        }
    }

    fun setRecyclerCouponsAdapter():RecyclerCouponsAdapter{
        recyclerCouponsAdapter = RecyclerCouponsAdapter(this, R.layout.card_coupon)
        return recyclerCouponsAdapter
    }

    fun getCouponAt(position: Int): Coupon?{
        var coupons: List<Coupon>? = couponObservable.getCoupons().value
        return coupons?.get(position)
    }


    companion object{
        @JvmStatic
        @BindingAdapter ("loadImage")
        fun loadImage (imageView: CircleImageView, imageUrl: String){
            imageUrl?.let {
                if(it.isNotEmpty()){
                    Picasso.get().load(it).into(imageView)
                }
            }
        }
    }

}