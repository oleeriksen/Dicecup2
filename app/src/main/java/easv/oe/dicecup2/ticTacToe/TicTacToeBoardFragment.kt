package easv.oe.dicecup2.ticTacToe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import easv.oe.dicecup2.R

class TicTacToeBoardFragment: Fragment() {

    private lateinit var boardRecyclerView: RecyclerView
    private var adapter: TicTacToeBoardFragment.BoardAdapter? = null


    private val ticTacToeBoardViewModel: TicTacToeBoardViewModel by lazy {
        TicTacToeBoardViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_board, container, false)
        boardRecyclerView = view.findViewById(R.id.board_recycler_view) as RecyclerView
        boardRecyclerView.layoutManager = GridLayoutManager(context, ticTacToeBoardViewModel.colAmount)
        ticTacToeBoardViewModel.createBoardFields()

        updateUI()


        return view
    }

    private fun updateUI() {
        val boardFields = ticTacToeBoardViewModel.boardFields
        adapter = BoardAdapter(boardFields)
        boardRecyclerView.adapter = adapter
    }


    fun setupBoard(){
        ticTacToeBoardViewModel.createBoardFields()
        boardRecyclerView.layoutManager = GridLayoutManager(context, ticTacToeBoardViewModel.colAmount)
        updateUI()
    }

    companion object{
        fun newInstance(): TicTacToeBoardFragment {
            return TicTacToeBoardFragment()
        }
    }

    private inner class BoardHolder(view:View): RecyclerView.ViewHolder(view) {
        val boardImageview : ImageView = itemView.findViewById(R.id.boardImage)

    }

    private inner class BoardAdapter(var boardfields:List<BoardField>) : RecyclerView.Adapter<BoardHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardHolder {
            val view = layoutInflater.inflate(R.layout.fragment_boardfield, parent, false)
            return BoardHolder(view)
        }

        override fun getItemCount(): Int {
            return boardfields.size
        }

        override fun onBindViewHolder(holder: BoardHolder, position: Int) {
            val boardField = boardfields[position]
            boardField.position = position
            holder.apply {

                boardImageview.setImageResource(boardField.getImage())

                boardImageview.setOnClickListener{ticTacToeBoardViewModel.onclickBoard(boardField, boardImageview)}
            }
        }
    }

}