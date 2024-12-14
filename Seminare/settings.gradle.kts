pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "My Application"
include(":app")
include(":app:seminar_2")
include(":app:seminar_3")
include(":app:seminar_4")
include(":app:seminar_5")
include(":app:seminar_6")
include(":app:seminar_8")
include(":app:seminar_9")
include(":app:seminar_10")
include(":app:seminar_11")
