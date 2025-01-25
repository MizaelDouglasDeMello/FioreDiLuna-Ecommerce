package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mizaeldouglas.fiorediluna_ecommerce.R
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.adapters.GridAdapter
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.adapters.Item
import com.google.android.material.appbar.AppBarLayout

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var adapter: GridAdapter
    private lateinit var container: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var textView5: TextView

    companion object {
        private const val ARG_USER_EMAIL = "user_email"
        private const val ARG_USER_NAME = "user_name"

        fun newInstance(userEmail: String?, userName: String?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle().apply {
                putString(ARG_USER_EMAIL, userEmail)
                putString(ARG_USER_NAME, userName)
            }
            fragment.arguments = args
            return fragment
        }
    }

    private var userEmail: String? = null
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userEmail = it.getString(ARG_USER_EMAIL)
            userName = it.getString(ARG_USER_NAME)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        container = view.findViewById(R.id.container)
        recyclerView = view.findViewById(R.id.recyclerView)
        appBarLayout = view.findViewById(R.id.appBarLayout)

        // Carregar o adapter
        loadingAdapter(view)

        // Adicionar o listener de rolagem
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var isScrollingDown = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (dy > 0 && !isScrollingDown && firstVisibleItemPosition > 0) {
                    // Quando rolar para baixo, alterar a altura e animar a translação
                    animateAppBarHeight(140)
                    animateAppBarTranslation(-140f)
                    isScrollingDown = true
                } else if (dy < 0 && isScrollingDown) {
                    // Quando rolar para cima, voltar a altura para wrap_content e restaurar a translação
                    animateAppBarHeight(-140)
                    animateAppBarTranslation(140f)
                    isScrollingDown = false
                }
            }
        })
    }

    private fun animateAppBarHeight(targetHeight: Int) {
        val currentHeight = appBarLayout.height
        val animator = ValueAnimator.ofInt(currentHeight, targetHeight)
        animator.duration = 500  // Duração da animação
        animator.addUpdateListener { valueAnimator ->
            val params = appBarLayout.layoutParams
            params.height = valueAnimator.animatedValue as Int
            appBarLayout.layoutParams = params
        }
        animator.start()
    }

    private fun animateAppBarTranslation(targetTranslationY: Float) {
        appBarLayout.animate().translationY(targetTranslationY).setDuration(300).start()
    }

    private fun loadingAdapter(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val items = listOf(
            Item(R.drawable.flor_list, "Item 1", "Tipo A", "R$ 19.99"),
            Item(R.drawable.flor_list, "Item 2", "Tipo B", "R$ 29.99"),
            Item(R.drawable.flor_list, "Item 3", "Tipo C", "R$ 39.99"),
            Item(R.drawable.flor_list, "Item 4", "Tipo D", "R$ 49.99"),
            Item(R.drawable.flor_list, "Item 5", "Tipo E", "R$ 59.99"),
            Item(R.drawable.flor_list, "Item 6", "Tipo F", "R$ 69.99"),
            Item(R.drawable.flor_list, "Item 7", "Tipo G", "R$ 79.99"),
            Item(R.drawable.flor_list, "Item 8", "Tipo H", "R$ 89.99")
        )

        adapter = GridAdapter(items)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}
