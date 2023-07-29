package com.ostapr.core.network.musicbrainz


class WikiCommonUrlConvertor: UrlConvertor {

    override fun knowHowToFind(pageUrl: String): Boolean = pageUrl.contains(WIKI_COMMON_URL)

    override suspend fun fetchImageUrls(pageUrl: String): List<String> {
        TODO("Not yet implemented")
    }

    private companion object {
        const val WIKI_COMMON_URL = "https://commons.wikimedia.org"
        const val WIKI_UPLOAD_URL = "https://upload.wikimedia.org/wikipedia/commons/"
    }
}