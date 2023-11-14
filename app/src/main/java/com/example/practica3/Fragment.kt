import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practica3.Class.Imc
import com.example.practica3.MainActivity
import com.example.practica3.R
import com.example.practica3.databinding.FragmentFragemtBinding


class YourFragment : Fragment() {
    private var historyFr: MutableList<Imc> = mutableListOf()
    private lateinit var binding: FragmentFragemtBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fragemt, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView()
    }

    private fun recyclerView(){
        binding.fragmentLayout.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@YourFragment.context, 1)
            val adapter = ImcAdapter()
            adapter.updateData(history())
            this.adapter = adapter
        }
    }


    private fun history(): MutableList<Imc> {
        requireActivity().apply { historyFr=(this as MainActivity).getHistory()}
        return historyFr
    }

    fun updateHistory(updatedHistory: MutableList<Imc>) {
        historyFr = updatedHistory
        (binding.fragmentLayout.adapter as? ImcAdapter)?.updateData(updatedHistory)
    }


    companion object{
        @JvmStatic
        fun newInstance() =
            YourFragment()
        }
    }

