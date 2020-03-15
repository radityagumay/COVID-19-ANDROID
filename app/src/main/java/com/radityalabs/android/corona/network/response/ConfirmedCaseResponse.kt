package com.radityalabs.android.corona.network.response

import com.google.gson.annotations.SerializedName

data class ConfirmedCaseResponse(
    @SerializedName("features") val features: List<Feature>?,
    @SerializedName("fields") val fields: List<Field>?,
    @SerializedName("geometryType") val geometryType: String?,
    @SerializedName("globalIdFieldName") val globalIdFieldName: String?,
    @SerializedName("objectIdFieldName") val objectIdFieldName: String?,
    @SerializedName("spatialReference") val spatialReference: SpatialReference?,
    @SerializedName("uniqueIdField") val uniqueIdField: UniqueIdField?
) {
    data class Feature(
        @SerializedName("attributes") val attributes: Attributes?
    ) {
        data class Attributes(
            @SerializedName("Confirmed") val confirmed: Int?,
            @SerializedName("Country_Region") val countryRegion: String?,
            @SerializedName("Deaths") val deaths: Int?,
            @SerializedName("Last_Update") val lastUpdate: Long?,
            @SerializedName("Lat") val lat: Double?,
            @SerializedName("Long_") val long: Double?,
            @SerializedName("OBJECTID") val oBJECTID: Int?,
            @SerializedName("Province_State") val provinceState: Any?,
            @SerializedName("Recovered") val recovered: Int?
        )
    }

    data class Field(
        @SerializedName("alias") val alias: String?,
        @SerializedName("defaultValue") val defaultValue: Any?,
        @SerializedName("domain") val domain: Any?,
        @SerializedName("length") val length: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("sqlType") val sqlType: String?,
        @SerializedName("type") val type: String?
    )

    data class SpatialReference(
        @SerializedName("latestWkid") val latestWkid: Int?,
        @SerializedName("wkid") val wkid: Int?
    )

    data class UniqueIdField(
        @SerializedName("isSystemMaintained") val isSystemMaintained: Boolean?,
        @SerializedName("name") val name: String?
    )
}