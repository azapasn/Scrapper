package lt.zapasnikas.carscraper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CarParam {
    @Id
    @GeneratedValue
    @Column
    private long id;
    @JsonIgnore
    @OneToOne
    private Advertisement advertisement;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private String years;
    @Column
    private String engine;
    @Column
    private String fuelType;
    @Column
    private String color;
    @Column
    private String mileageKm;
    @Column
    private String driveTrain;
    @Column
    private String defects;
    @Column
    private String vinCode;
    @Column
    private String firstRegistrationCountry;
    @Column
    private String licensePlate;


    public CarParam() {

    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(String mileageKm) {
        this.mileageKm = mileageKm;
    }

    public String getDriveTrain() {
        return driveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        this.driveTrain = driveTrain;
    }

    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    public String getVinCode() {
        return vinCode;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public String getFirstRegistrationCountry() {
        return firstRegistrationCountry;
    }

    public void setFirstRegistrationCountry(String firstRegistrationCountry) {
        this.firstRegistrationCountry = firstRegistrationCountry;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
