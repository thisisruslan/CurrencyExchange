package uz.anorbank.currencyexchange.data.remote.dataclasses

import com.google.gson.annotations.SerializedName


data class AnorBankData(
    @SerializedName("list") val list: List<AnorBankDataItem>
)

data class AnorBankDataItem(
    @SerializedName("baseCode") val baseCode: String,
    @SerializedName("baseISO") val baseISO: String,
    @SerializedName("baseName") val baseName: String,
    @SerializedName("baseFractDigits") val baseFractDigits: Int,
    @SerializedName("fractDigits") val fractDigits: Int,
    @SerializedName("code") val code: String,
    @SerializedName("isoCode") val isoCode: String,
    @SerializedName("rateBuy") val rateBuy: Float,
    @SerializedName("rateSell") val rateSell: Float,
    @SerializedName("rateDate") val rateDate: String,
    @SerializedName("scale") val scale: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rateCB") val rateCB: Float
)

/*
        {
            "baseCode": "643",
            "baseISO": "Российский рубль",
            "baseName": "RUB",
            "baseFractDigits": 0,
            "fractDigits": 0,
            "code": "000",
            "isoCode": "UZS",
            "rateBuy": 130.0,
            "rateSell": 205.0,
            "rateDate": "2022-06-28 00:00:00.0",
            "scale": 1,
            "name": "Российский рубль",
            "rateCB": 203.16
        },
 */
