package me.abuzaid.movies.models

/**
 * Created by "Mohamad Abuzaid" on 05/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
interface MediaDisplay {
    val header: String
    val date: String
    val posterPath: String
    val overview: String
    val voteAverage: Double
    val voteCount: Int
}