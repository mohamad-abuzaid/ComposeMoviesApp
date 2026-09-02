![Compose Movies on Android](https://github.com/mohamad-abuzaid/ComposeMoviesApp/assets/935514/f4c411dd-2570-4d27-abe3-eb56e5f4ab9f)

# Compose Movies: a branch-by-branch Android learning journey

Compose Movies is a guided Jetpack Compose tutorial for junior Android developers who want to understand how a modern application grows one engineering decision at a time.

The movie app is the teaching vehicle. The main artifact is the repository history: each numbered branch introduces a focused concern, gives learners a checkpoint they can inspect, and makes the progression from project setup to a network-backed, animated application visible.

**Target:** Android.

## Why I built it this way

A completed sample can show *what* was built, but it often hides *how* the pieces came together. I created this repository to make that process teachable for developers early in their careers.

The branch structure reflects how I approach technical mentoring and engineering leadership:

- **Decompose the problem:** progress from project foundations to architecture, UI, navigation, data, and interaction polish.
- **Introduce concepts in context:** add a tool when the application has a concrete reason to need it.
- **Create reviewable checkpoints:** let a learner compare two branches instead of reverse-engineering a finished codebase.
- **Teach decisions, not recipes:** use each step to discuss module boundaries, state ownership, dependency direction, and trade-offs.
- **Leave room for ownership:** treat the final app as a foundation for testing, security, offline behavior, and modernization exercises—not as a claim of production completeness.

For portfolio reviewers, this repository demonstrates more than familiarity with Android libraries. It shows how I organize a broad technical subject into a path that another developer can follow, question, and extend.

## Follow the learning path

The branches form one cumulative path from Step 1 through Step 17. Each checkpoint includes everything introduced in the preceding steps.

| Step | Branch | Learning checkpoint | Question to explore |
| --- | --- | --- | --- |
| 1 | [`step_1/project_dependencies_setup`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_1/project_dependencies_setup) | Establish the four Gradle modules and dependency catalog. | Which dependencies should each module expose, and which should remain implementation details? |
| 2 | [`step_2/setup_koin`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_2/setup_koin) | Introduce Koin and assemble application dependencies. | Should a feature know how its dependencies are constructed? |
| 3 | [`step_3/setup_room_database`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_3/setup_room_database) | Add Room entities, a DAO, converters, and the application database. | What should the local data model optimize for, and where should mapping happen? |
| 4 | [`step_4/setup_ktor`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_4/setup_ktor) | Build the Ktor client, serialization, remote models, services, and repository boundary. | How should transport failures and remote data cross into the domain layer? |
| 5 | [`step_5/setup_compose`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_5/setup_compose) | Establish the Compose entry point, theme, and UI foundation. | Which state belongs in a composable, and which state should be lifted out? |
| 6 | [`step_6/setup_shared_preference`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_6/setup_shared_preference) | Persist lightweight user settings with `SharedPreferences`. | When is key-value storage appropriate, and when does the data need a stronger model? |
| 7 | [`step_7/setup_localization`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_7/setup_localization) | Add English and Arabic resources plus locale handling. | How should a locale choice flow through storage, context, and recomposition? |
| 8 | [`step_8/setup_navigation`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_8/setup_navigation) | Introduce Compose Navigation and typed route arguments. | What is the smallest safe contract between two screens? |
| 9 | [`step_9/setup_splash`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_9/setup_splash) | Build the splash experience and its route transition. | Which startup decisions should block navigation, and which can happen in the background? |
| 10 | [`step_10/setup_language_select`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_10/setup_language_select) | Connect language selection to persistence and navigation. | How does one user choice travel across UI, state, storage, and Android resources? |
| 11 | [`step_11/setup_home`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_11/setup_home) | Build the home screen and reusable media components. | Which components are genuinely reusable, and which are coupled to one screen? |
| 12 | [`step_12/setup_movie_details`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_12/setup_movie_details) | Add the movie-details experience. | Should a detail destination receive a full display model or only an identifier? |
| 13 | [`step_13/implement_popular_api`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_13/implement_popular_api) | Connect popular movies across API, repository, use case, state, and UI layers. | How should loading, success, and failure move through the application? |
| 14 | [`step_14/build_shows_screen`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_14/build_shows_screen) | Add paged TV shows and a show-details screen. | Which behavior can movies and shows share without forcing a misleading abstraction? |
| 15 | [`step_15/build_movies_screen`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_15/build_movies_screen) | Add the paged movies screen. | Where should pagination state and retry behavior live? |
| 16 | [`step_16/build_search_screen`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_16/build_search_screen) | Implement multi-search and paged results. | How should search input, request timing, and result state be coordinated? |
| 17 | [`step_17/Implement_animation`](https://github.com/mohamad-abuzaid/ComposeMoviesApp/tree/step_17/Implement_animation) | Explore visibility, transition, keyframe, color, size, and infinite animations. | Which animations clarify state changes, and which only add visual noise? |

## How to study the branches

Clone the full repository so every remote learning checkpoint is available:

```bash
git clone https://github.com/mohamad-abuzaid/ComposeMoviesApp.git
cd ComposeMoviesApp
git branch --remotes
```

Open a checkpoint without creating a local branch:

```bash
git switch --detach origin/step_1/project_dependencies_setup
```

Compare consecutive checkpoints to see the decision in context:

```bash
git diff --stat \
  origin/step_12/setup_movie_details...origin/step_13/implement_popular_api
```

When you are ready to experiment, create your own branch from the relevant checkpoint:

```bash
git switch --create my-compose-experiment \
  origin/step_13/implement_popular_api
```

Do not only ask whether the code runs. Try to explain why the new files belong in their modules, who owns each piece of state, and which alternative you would choose under different constraints.

## Architecture at the final checkpoint

The final learning checkpoint is divided into four Gradle modules:

| Module | Responsibility |
| --- | --- |
| `app` | Compose UI, display models, state and events, navigation, localization, and Android entry points. |
| `domain` | Movie and show models, repository contracts, result models, and use cases. |
| `data` | Room persistence, Ktor networking, remote/local models, mappers, paging sources, and the repository implementation. |
| `di` | Koin modules that assemble the database, network services, repositories, and use cases. |

### Main technologies

- Kotlin and Jetpack Compose
- Coroutines and Flow
- Compose Navigation
- Paging 3
- Room
- Ktor with Kotlinx Serialization
- Koin
- Coil

## Run the final application

### Prerequisites

- JDK 17
- Android Studio with Android SDK 34
- A [TMDB account and API credentials](https://developer.themoviedb.org/docs/getting-started)

### Configure TMDB

The data module reads `base_url`, `api`, and `token` from [`config/common.properties`](config/common.properties). The current HTTP client authenticates with the TMDB API Read Access Token stored in `token`.

```properties
base_url=api.themoviedb.org
api=YOUR_TMDB_API_KEY
token=YOUR_TMDB_API_READ_ACCESS_TOKEN
```

This file is tracked as part of the historical tutorial. Use only your own development credentials and do not commit a live secret. A maintained fork should load credentials from an ignored local file or environment variables.

### Build and install

Open the project in Android Studio and run the `app` configuration, or install a debug build from the command line:

```bash
./gradlew :app:installDebug
```

## Engineering notes and next exercises

This repository preserves a learning project created in 2024. Some dependencies were alpha or beta releases at that time. It is an educational reference, not a current production template.

The current `./gradlew test` task succeeds with JDK 17 and Android SDK 34. It reports warnings around deprecated pull-to-refresh APIs and generated Room code that a modernization pass should address. The repository contains only generated placeholder unit tests and no CI quality gate.

Those gaps create useful follow-on exercises:

1. Replace the placeholder tests with behavior-focused tests for view-model state transitions, repository mapping, paging sources, and network failures.
2. Design Room as a real offline source of truth instead of using it only for the popular-movies flow.
3. Move TMDB credentials out of the tracked configuration file.
4. Upgrade the historical dependency set deliberately, documenting each breaking change rather than changing every version at once.
5. Add accessibility checks, screenshot tests, retry policy, observability, and continuous integration.
6. Review whether the current module dependency directions match the architecture you want to defend.

The goal is not to produce the most polished clone. It is to build enough understanding that you can explain the architecture, diagnose its failures, and improve it with intent.

## Design and data source

- UI reference: [Anime & Movies — Neon Mode on Figma](https://www.figma.com/file/vI6BpWMy3p02hogoFc7R08/App-Anime-%26-Movies-NEON-MODE-(Community))
- Movie data: [The Movie Database API](https://developer.themoviedb.org/reference/intro/getting-started)

## Learn with me

If you are learning Android development, open an issue with the branch you are studying, the behavior you expected, what you observed, and the approaches you already tried. That context creates a much better mentoring conversation than a screenshot of an error alone.

If you are reviewing this repository as part of my portfolio, I am happy to discuss why the learning path was structured this way, what I would modernize today, and how I use the same decomposition and review habits when guiding engineering teams.

- [LinkedIn](https://www.linkedin.com/in/mohamad-abuzaid/)
- [Portfolio](https://abuzaid.me/)
