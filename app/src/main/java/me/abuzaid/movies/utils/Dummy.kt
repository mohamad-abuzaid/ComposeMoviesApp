package me.abuzaid.movies.utils

import me.abuzaid.movies.models.ActorDisplay
import me.abuzaid.movies.models.MovieDisplay

/**
 * Created by "Mohamad Abuzaid" on 05/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
object Dummy {
    val movie1 = MovieDisplay(
        id = 4789,
        adult = false,
        backdropPath = "definitionem",
        genreIds = listOf(),
        originalLanguage = "veri",
        originalTitle = "Furiosa: A Mad Max Saga",
        overview = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        popularity = 4.5,
        posterPath = "https://picsum.photos/100",
        releaseDate = "2024-05-08",
        title = "Furiosa",
        video = false,
        voteAverage = 2.5,
        voteCount = 7715
    )

    private val movie2 = MovieDisplay(
        id = 5391,
        adult = false,
        backdropPath = "iusto",
        genreIds = listOf(),
        originalLanguage = "aliquam",
        originalTitle = "Godzilla Minus One",
        overview = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        popularity = 12.13,
        posterPath = "https://picsum.photos/100",
        releaseDate = "2023-05-08",
        title = "Godzilla Minus One",
        video = false,
        voteAverage = 3.0,
        voteCount = 6739
    )

    private val movie3 = MovieDisplay(
        id = 9213,
        adult = false,
        backdropPath = "penatibus",
        genreIds = listOf(),
        originalLanguage = "conclusionemque",
        originalTitle = "The First Omen",
        overview = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        popularity = 20.21,
        posterPath = "https://picsum.photos/100",
        releaseDate = "2022-05-08",
        title = "The First Omen",
        video = false,
        voteAverage = 4.5,
        voteCount = 9493
    )

    private val movie4 = MovieDisplay(
        id = 5063,
        adult = false,
        backdropPath = "homero",
        genreIds = listOf(),
        originalLanguage = "hendrerit",
        originalTitle = "Tarot",
        overview = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        popularity = 28.29,
        posterPath = "https://picsum.photos/100",
        releaseDate = "2020-05-08",
        title = "Tarot",
        video = false,
        voteAverage = 1.5,
        voteCount = 2462
    )

    private val movie5 = MovieDisplay(
        id = 4363,
        adult = false,
        backdropPath = "lacinia",
        genreIds = listOf(),
        originalLanguage = "saperet",
        originalTitle = "Civil War",
        overview = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        popularity = 36.37,
        posterPath = "https://picsum.photos/100",
        releaseDate = "2021-05-08",
        title = "Civil War",
        video = false,
        voteAverage = 3.5,
        voteCount = 4188
    )

    private val movie6 = MovieDisplay(
        id = 2739,
        adult = false,
        backdropPath = "justo",
        genreIds = listOf(),
        originalLanguage = "augue",
        originalTitle = "Kingdom of the Planet of the Apes",
        overview = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        popularity = 44.45,
        posterPath = "https://picsum.photos/100",
        releaseDate = "2023-05-08",
        title = "Kingdom of the Planet of the Apes",
        video = false,
        voteAverage = 4.0,
        voteCount = 1777
    )

    val movies = listOf(movie1, movie2, movie3, movie4, movie5, movie6)

    val actor1 = ActorDisplay(
        name = "Toni Duran",
        role = "as per",
        photo = "https://picsum.photos/100",
    )

    private val actor2 = ActorDisplay(
        name = "Sophia Meadows",
        role = "as errem",
        photo = "https://picsum.photos/100",
    )

    private val actor3 = ActorDisplay(
        name = "Gavin Gamble",
        role = "as maiorum",
        photo = "https://picsum.photos/100",
    )

    private val actor4 = ActorDisplay(
        name = "Albert Cohen",
        role = "as harum",
        photo = "https://picsum.photos/100",
    )

    val actors = listOf(actor1, actor2, actor3, actor4)
}


