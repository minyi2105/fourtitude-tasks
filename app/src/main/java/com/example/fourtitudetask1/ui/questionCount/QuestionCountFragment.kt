package com.example.fourtitudetask1.ui.questionCount


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.adapter.QuestionCountAdapter
import com.example.fourtitudetask1.base.fragment.BaseMvpFragment
import com.example.fourtitudetask1.lib.data.model.json.response.CategoryQuestionCount
import com.example.fourtitudetask1.lib.data.model.json.response.QuestionCount
import kotlinx.android.synthetic.main.fragment_question_count.*

class QuestionCountFragment : BaseMvpFragment(), QuestionCountFragmentMvpView {

    override fun injectAppComponent() {
        appComponent.inject(this)
    }

    override fun setQuestionCategoryCount(questionCountList: List<CategoryQuestionCount>) {
        rv_category_count.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rv_category_count.adapter = QuestionCountAdapter(activity!!, questionCountList)
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    private var presenter: QuestionCountFragmentPresenter = QuestionCountFragmentPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_count, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        (activity as AppCompatActivity).supportActionBar!!.title = "Question Category"
        (activity as AppCompatActivity).supportActionBar!!.elevation = 0f

        presenter.getQuestionCount()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }
}