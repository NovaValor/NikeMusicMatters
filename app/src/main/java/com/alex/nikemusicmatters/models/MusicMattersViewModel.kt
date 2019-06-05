package com.alex.nikemusicmatters.models

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class MusicMattersViewModel:ViewModel() {

    private val TAG = this.javaClass.simpleName

    val FEATURE_ENDPOINT = "https://rss.itunes.apple.com/api/v1/us/apple-music/coming-soon/all/40/explicit.json"

    lateinit var selectedAlbum:Album

    private val albums: MutableLiveData<ArrayList<Album>> = MutableLiveData()
    fun getAlbumsLiveData(): LiveData<ArrayList<Album>> = albums

    var recyclerInstanceState: Parcelable? = null

    enum class apiKeys {
        feed,
        results,
        artistName,
        url,
        artworkUrl100,
        releaseDate,
        genres,
        copyright
    }

    init {
        GlobalScope.launch{fetchData()}
    }

    suspend fun fetchData(){
        withContext(Dispatchers.IO){
            var data: JSONObject = JSONObject("{\"status\":\"root failure\"}")
            try {
                data = JSONObject(URL(FEATURE_ENDPOINT).readText())
                data = data.getJSONObject(apiKeys.feed.name)

            } catch (e: Exception){
                println("$TAG ${e.message}")
                return@withContext
            }
            parseData(data.getJSONArray(apiKeys.results.name))
        }
    }

    fun parseData(iData: JSONArray){
        val workingList = ArrayList<Album>()
        try {
            for(i in 0 until(iData.length())){
                val album = iData.getJSONObject(i)

                val genres = album.getJSONArray(apiKeys.genres.name)
                val genre = genres.getJSONObject(0)
                val genreText = genre.getString("name")

                with(album){
                    workingList.add(Album(
                        getString(apiKeys.artistName.name),
                        getString("name"),
                        getString(apiKeys.url.name),
                        getString(apiKeys.artworkUrl100.name),
                        getString(apiKeys.releaseDate.name),
                        genreText,
                        getString(apiKeys.copyright.name)))
                }

            }
        }catch (e: JSONException){
            println("$TAG ${e.message}")
        }
        albums.postValue(workingList)
    }
}