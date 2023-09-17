package com.cleo.weatherforecast.data

data class WeatherResponse(
    var coord:Coord,
    var weather: List<Weather>,
    var base:String,
    var main:Main,
    var visibility:Long,
    var wind:Wind,
    var clouds:Clouds,
    var dt:Long,
    var sys:Sys,
    var timezone:Long,
    var id:Long,
    var name:String?,
    var cod:Long
)

data class Coord(
    var lon:Double,
    var lat:Double
)

data class Weather(
    var id:Long,
    var main:String,
    var description:String,
    var icon:String
)

data class Main(
    var temp:Double,
    var feels_like:Double,
    var temp_min:Double,
    var temp_max:Double,
    var pressure:Long,
    var humidity:Long,
    var sea_level:Long,
    var grnd_level:Long
)
data class Wind(
    var speed:Double,
    var deg:Long,
    var gust:Double
)

data class Clouds(
    var all:Long
)
data class Sys(
    var type:Int,
    var id:Long,
    var country:String,
    var sunrise:Long,
    var sunset:Long
)





//{
//    "coord": {
//    "lon": 32.5822,
//    "lat": 0.3163
//},
//    "weather": [
//    {
//        "id": 803,
//        "main": "Clouds",
//        "description": "broken clouds",
//        "icon": "04d"
//    }
//    ],
//    "base": "stations",
//    "main": {
//    "temp": 298.81,
//    "feels_like": 298.37,
//    "temp_min": 298.81,
//    "temp_max": 298.81,
//    "pressure": 1012,
//    "humidity": 36,
//    "sea_level": 1012,
//    "grnd_level": 884
//},
//    "visibility": 10000,
//    "wind": {
//    "speed": 1.02,
//    "deg": 166,
//    "gust": 1.29
//},
//    "clouds": {
//    "all": 72
//},
//    "dt": 1694855878,
//    "sys": {
//    "type": 1,
//    "id": 2642,
//    "country": "UG",
//    "sunrise": 1694835687,
//    "sunset": 1694879293
//},
//    "timezone": 10800,
//    "id": 232422,
//    "name": "Kampala",
//    "cod": 200
//}