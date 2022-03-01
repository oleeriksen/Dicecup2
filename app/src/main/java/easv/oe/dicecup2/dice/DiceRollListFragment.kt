package easv.oe.dicecup2.dice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import easv.oe.dicecup2.R


private const val TAG ="DiceRollListFragment"
class DiceRollListFragment: Fragment() {

    private lateinit var diceRollRecyclerView: RecyclerView
    private var adapter: DiceRollAdapter? = null

    private val diceRollListViewModel: DiceViewModel by lazy {
        ViewModelProvider(requireActivity()).get(DiceViewModel:: class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total diceRolls: ${diceRollListViewModel.diceHistoryManager.historyList.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_diceroll_list, container, false)

        diceRollRecyclerView =
            view.findViewById(R.id.diceRoll_recycler_view) as RecyclerView
        diceRollRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()
        return  view
    }

    private fun updateUI(){
        val diceRolls = diceRollListViewModel.diceHistoryManager.historyList
        adapter = DiceRollAdapter(diceRolls)
        diceRollRecyclerView.adapter =adapter
    }

    private inner class DiceRollHolder(view: View)
        : RecyclerView.ViewHolder(view){
        private lateinit var diceRollLog: DiceRollLog
        private val dieEyesTextView: TextView = itemView.findViewById(R.id.die_eyes)

        fun bind(diceRollLog: DiceRollLog){
            this.diceRollLog = diceRollLog
            dieEyesTextView.text = this.diceRollLog.toString()
        }

    }

    private inner class DiceRollAdapter(var diceRolls: List<DiceRollLog>)
        : RecyclerView.Adapter<DiceRollHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceRollHolder {
            val view = layoutInflater.inflate(R.layout.list_item_diceroll, parent, false)
            return DiceRollHolder(view)
        }

        override fun onBindViewHolder(holder: DiceRollHolder, position: Int) {
            val diceRollLog = diceRolls[position]
            holder.bind(diceRollLog)
        }

        override fun getItemCount(): Int = diceRolls.size



    }

    companion object{
        fun newInstance(): DiceRollListFragment{
            return DiceRollListFragment()
        }
    }
}