import com.example.mvp_my_first_mvp_example.MainContract
import com.example.mvp_my_first_mvp_example.data.InfoDataSource
import com.example.mvp_my_first_mvp_example.data.InfoRepository
import org.json.JSONObject

class MainPresenter(
    val view: MainContract.View,
    val repository: InfoRepository
) : MainContract.Presenter {

    override fun initInfo() {
        repository.getInfo(object: InfoDataSource.LoadInfoCallback {
            override fun onInfoLoaded(info: JSONObject) {
                view.showInfo(info)
            }
            override fun onDataNotAvailable() {
                // 아무것도 하지 않음
            }
        })
    }

    override fun setInfo(info: JSONObject) {
        view.showInfo(info)
    }

    override fun saveInfo(info: JSONObject) {
        repository.saveInfo(info)
    }
}