package leonard.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 城市对象
 * Created by leonard on 2016/05/16.
 */
public class CityModel extends BaseModel {
    private static final long serialVersionUID = 7695148552129888451L;

    @JsonProperty("id")
    private String cityId;          //城市id
    @JsonProperty("city")
    private String cityName;        //城市名称
    @JsonProperty("cnty")
    private String country;         //国家
    @JsonProperty("lon")
    private String lng;             //经度
    @JsonProperty("lat")
    private String lat;             //纬度

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
