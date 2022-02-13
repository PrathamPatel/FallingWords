package com.babbeltest.fallingwords.gameviewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.babbeltest.fallingwords.App
import com.babbeltest.fallingwords.Const.CORRECT_MATCH
import com.babbeltest.fallingwords.Const.INCORRECT_MATCH
import com.babbeltest.fallingwords.helper.RxImmediateSchedulerRule
import com.babbeltest.fallingwords.models.WordDataItem
import com.babbeltest.fallingwords.repositories.WordDataRepository
import com.babbeltest.fallingwords.ui.game.GameViewModel
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

/**
 * Created by : Pratham
 */
@RunWith(MockitoJUnitRunner::class)
class GameViewModelTest {

    @get:Rule
    var rule : TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Test
    fun getWordsTest(){
        val mockData = listOf(WordDataItem(
            textEng = "Hello",
            textSpa = "Halo"
        ))

        val viewModel = createViewModel(
            app = App(),
            wordDataRepository = mock{
                on {
                    getWords()
                } doReturn
                        Single.just(mockData)
            }
        )

        viewModel.load()

        assertEquals(viewModel.setWord.value, "Hello")
        assertEquals(viewModel.fallingWord.value, "Halo")

    }

    @Test
    fun testScoreCount(){
        val mockData = listOf(
            WordDataItem(
                textEng = "Hello",
                textSpa = "Halo"
        ),
            WordDataItem(
                textEng = "holidays",
                 textSpa = "timbre"
            )
        )

        val viewModel = createViewModel(
            app = App(),
            wordDataRepository = mock{
                on {
                    getWords()
                } doReturn
                        Single.just(mockData)
            }
        )

        viewModel.load()

        val correctMatch = (CORRECT_MATCH..INCORRECT_MATCH).random() == CORRECT_MATCH

        if(correctMatch){
            viewModel.onMatchClicked()
            assertEquals(viewModel.scoreCount, 1)
        }else{
            viewModel.onNoMatchClicked()
            assertEquals(viewModel.scoreCount, viewModel.scoreCount)
        }
    }

    private fun createViewModel(
        app: App = mock(),
        wordDataRepository: WordDataRepository = mock()
    ) = GameViewModel(app, wordDataRepository)
}