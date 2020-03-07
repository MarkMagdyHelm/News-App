package com.Mark.news

import androidx.lifecycle.LiveData
import com.Mark.news.vacation.vacationlists.model.MostPopulerDataContract
import com.Mark.news.vacation.vacationlists.viewmodel.MostPopulerViewModel
import com.example.mostpopularapp.ui.mplist.models.Datum
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Tests for [ListViewModel]
 * */
@RunWith(RobolectricTestRunner::class)
class ListViewModelTest {
    //mocking the needed classes
    private lateinit var viewModel: MostPopulerViewModel

    private val repo: MostPopulerDataContract.Repository = mock()

    private var mpList: LiveData<List<Datum>> = mock()
    //private val factory: DataSource.Factory<Int, post> = mock()


    @Before
    fun init() {

        viewModel = MostPopulerViewModel(repo, CompositeDisposable())
//        viewModel. = mpList

    }

    /*
        Mockito Verify methods are used to check that certain behavior happened. We can use Mockito verify methods at the end of the testing method code to make sure that specified methods are called.
    */
    @Test
    fun testGetPostsSuccess() {
        viewModel.fetchMostPopulerList(7)
        verify(repo).getMostPopulerList(7)

    }


}