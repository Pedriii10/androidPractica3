import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica3.MainActivity
import com.example.practica3.databinding.FragmentFragemtBinding


class YourFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding = FragmentFragemtBinding.inflate(inflater,container,false)
        val view = binding?.root



        val mainActivity = activity as?MainActivity
        val historyFr = mainActivity?.getHistory()

        Log.i("message","$historyFr.size")

        val imc = ImcAdapter(historyFr)

        binding.fragmentLayout.layoutManager = LinearLayoutManager(context)
        binding.fragmentLayout.adapter = imc

        return view
    }

    companion object{
        @JvmStatic
        fun newInstance() =
            YourFragment()
        }
    }

