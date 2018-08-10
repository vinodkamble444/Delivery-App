package tk.andivinu.deliveryapp.ui.delivery

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tk.andivinu.deliveryapp.model.Delivery
import tk.andivinu.deliveryapp.R
import tk.andivinu.deliveryapp.databinding.ItemDeliveryBinding
import tk.andivinu.deliveryapp.ui.delivery.DeliveryViewModel

class DeliveryListAdapter(val clickListener: (Delivery) -> Unit) : RecyclerView.Adapter<DeliveryListAdapter.ViewHolder>() {
    private lateinit var deliveryList: List<Delivery>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryListAdapter.ViewHolder {
        val binding: ItemDeliveryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_delivery, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeliveryListAdapter.ViewHolder, position: Int) {
        holder.bind(deliveryList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return if (::deliveryList.isInitialized) deliveryList.size else 0
    }

    fun updateDeliveryList(delivery: List<Delivery>) {
        this.deliveryList = delivery
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemDeliveryBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = DeliveryViewModel()

        fun bind(delivery: Delivery, clickListener: (Delivery) -> Unit) {
            viewModel.bind(delivery)
            binding.viewModel = viewModel
            binding.root.rootView.setOnClickListener { clickListener(delivery) }
        }

    }
}