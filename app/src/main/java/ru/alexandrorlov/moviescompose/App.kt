package ru.alexandrorlov.moviescompose

import android.app.Application
import android.content.Context

class App : Application() {
//    private val repositoryNetworkConfiguration: RepositoryNetworkConfiguration =
//        RepositoryNetworkConfiguration.Singleton.instance
//    private val _stateConfigURLImage: MutableStateFlow<StateConfigURLImage> =
//        MutableStateFlow(StateConfigURLImage.Loading)
//    private val stateConfigURLImage = _stateConfigURLImage.asStateFlow()
//    private val appScope = CoroutineScope(SupervisorJob())

//    init {
//        appScope.launch {//TODO уточнить насчёт в каком потоке запускается.
//            val resultConfiguration = repositoryNetworkConfiguration.getResultConfiguration()
//            if (resultConfiguration is Result.Success) {
//                val configURLImage = resultConfiguration.data as ConfigURLImage
//                _stateConfigURLImage.emit(StateConfigURLImage.Success(configURLImage))
//            }
//            if (resultConfiguration is Result.Error) {
//                _stateConfigURLImage.emit(
//                    StateConfigURLImage.Error(
//                        resultConfiguration.message
//                    )
//                )
//            }
//        }
//    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
//        configURLImage = stateConfigURLImage.value as ConfigURLImage
    }


    companion object {
        lateinit var appContext: Context
////        lateinit var configURLImage: ConfigURLImage
//        fun getFirstPartURL(imageType: NetworkConfig.ImageType): String {
//            return when (imageType) {
//                NetworkConfig.ImageType.MOVIE_POSTER -> configURLImage.let {
//                    it.baseURLImage + it.posterSizes[NetworkConfig.POSTER_SIZE_MEDIUM]
//                }
//                NetworkConfig.ImageType.MOVIE_BACKDROP -> configURLImage.let {
//                    it.baseURLImage + it.backdropSizes[NetworkConfig.BACKDROP_SIZE_SMALL]
//                }
//                NetworkConfig.ImageType.ACTOR_PHOTO -> configURLImage.let {
//                    it.baseURLImage + it.profileSizes[NetworkConfig.ACTOR_PHOTO_SIZE_SMALL]
//                }
//            }
//        }
    }
}