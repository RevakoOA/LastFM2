package com.ostapr.core.network.musicbrainz

/**
 * As far as LastFM doesn't provide API for images we use different service to fetch artist's images as well as other related info.
 * Some services like MusicBrainz return url to the web page where image is present, rather then URL to the image itself.
 * UrlConvertor know how to find images
 */
interface UrlConvertor {

    /**
     * @return true if convertor knows how search for images in this page.
     * Otherwise [fetchImageUrls] will return url itself.
     */
    fun knowHowToFind(pageUrl: String): Boolean

    /**
     * If web page is supported load the web page and parse for images, otherwise return web page
     * itself.
     *
     * @param [pageUrl] page's url.
     * @return list of found images or webpage itself.
     */
    suspend fun fetchImageUrls(pageUrl: String): List<String>
}