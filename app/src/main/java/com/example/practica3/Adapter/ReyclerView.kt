import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica3.Class.Imc
import com.example.practica3.R
import com.example.practica3.databinding.ItemImcBinding

class ImcAdapter(val history: MutableList<Imc>?): RecyclerView.Adapter<ImcAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImcAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_imc,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(history!![position])

    override fun getItemCount(): Int = history!!.size


    inner class ViewHolder(historyItem: View) : RecyclerView.ViewHolder(historyItem) {

        private val binding = ItemImcBinding.bind(historyItem)

        fun bind(imc : Imc) {
            binding.imcTv.text = imc.imc.toString().format("%.2f")
            binding.dayTv.text = imc.day.toString()
            binding.monthTv.text = imc.month
            binding.yearTv.text= imc.year.toString()
            binding.stateTv.text = imc.state
            binding.genderTv.text = imc.gender

        }

    }

}
