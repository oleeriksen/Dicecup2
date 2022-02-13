package easv.oe.dicecup2.dice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import easv.oe.dicecup2.R
import kotlin.math.sqrt

private const val TAG = "DiceListFragment"
class DiceListFragment: Fragment() {

    //region vals and vars

    private lateinit var diceRecyclerView: RecyclerView
    private var adapter: DiceAdapter? = null

    private val diceListViewModel: DiceListViewModel by lazy {
        ViewModelProvider(this).get(DiceListViewModel::class.java)
    }

    //endregion

    //region override

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total dice: ${diceListViewModel.dice.size}")

        diceListViewModel.updateDice()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dice_list, container, false)

        diceRecyclerView = view.findViewById(R.id.dice_recycler_view) as RecyclerView
        var layoutCol = 3
        if(sqrt(diceListViewModel.currentDiceAmount.toDouble()).toInt() > 3){
            layoutCol = sqrt(diceListViewModel.currentDiceAmount.toDouble()).toInt();
        }
        diceRecyclerView.layoutManager = GridLayoutManager(context, layoutCol)

        updateUI()

        return view
    }
    //endregion


    //region dice

    fun setDiceAmount(diceAmount: Int){
        diceListViewModel.currentDiceAmount = diceAmount
        diceListViewModel.updateDice()
        updateUI()
    }

    fun rollDice(diceHistoryManager: DiceHistoryManager){
        diceListViewModel.rollDice(diceHistoryManager)
        updateUI()
    }

    fun setDiceFromRoll(diceRollLog: DiceRollLog){
        diceListViewModel.updateDiceFromHistory(diceRollLog)
    }

    private fun updateUI() {
        val dices = diceListViewModel.dice
        adapter = DiceAdapter(dices)
        diceRecyclerView.adapter = adapter
    }

    //endregion


    private inner class DiceHolder(view:View): RecyclerView.ViewHolder(view) {
        val diceImageView : ImageView = itemView.findViewById(R.id.diceImage)

    }

    private inner class DiceAdapter(var dice:List<Dice>) : RecyclerView.Adapter<DiceHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceHolder {
            val view = layoutInflater.inflate(R.layout.dice_fragment, parent, false)
            return DiceHolder(view)
        }

        override fun getItemCount(): Int {
            return dice.size
        }

        override fun onBindViewHolder(holder: DiceHolder, position: Int) {
            val dice = dice[position]
            holder.apply {
                diceImageView.setImageResource(DiceImageManager().diceImages[dice.currentEyes])
            }
        }
    }

    companion object{
        fun newInstance(): DiceListFragment{
            return DiceListFragment()
        }
    }
}