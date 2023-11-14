import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica3.Class.Imc
import com.example.practica3.databinding.ItemImcBinding

class ImcAdapter : RecyclerView.Adapter<ImcAdapter.ViewHolder>() {
    private var history: MutableList<Imc> = mutableListOf()

    fun updateData(updatedHistory: MutableList<Imc>) {
        history = updatedHistory
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImcAdapter.ViewHolder {
        return ViewHolder(ItemImcBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(history[position])

    override fun getItemCount(): Int {
       return history.size
    }

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
