package uz.anorbank.currencyexchange.data.remote.dataclasses

import com.google.gson.annotations.SerializedName


data class CentralBankData(
    @SerializedName("id") val id: Int,
    @SerializedName("Code") val Code: Int,
    @SerializedName("Ccy") val Ccy: String,
    @SerializedName("CcyNm_RU") val CcyNm_RU: String,
    @SerializedName("CcyNm_UZ") val CcyNm_UZ: String,
    @SerializedName("CcyNm_UZC") val CcyNm_UZC: String,
    @SerializedName("CcyNm_EN") val CcyNm_EN: String,
    @SerializedName("Nominal") val Nominal: String,
    @SerializedName("Rate") val Rate: String,
    @SerializedName("Diff") val Diff: String,
    @SerializedName("Date") val Date: String
)

/*
    {
        "id": 69,
        "Code": "840",
        "Ccy": "USD",
        "CcyNm_RU": "Доллар США",
        "CcyNm_UZ": "AQSH dollari",
        "CcyNm_UZC": "АҚШ доллари",
        "CcyNm_EN": "US Dollar",
        "Nominal": "1",
        "Rate": "10823.52",
        "Diff": "-16.38",
        "Date": "28.06.2022"
    },
 */
